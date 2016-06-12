package com.xiaohao.multithread.future;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String directory = "/home/xiaohao/github/CoreJava/src";
		System.out.println("Enter keyword (e.g. final):");
		String keyword = in.nextLine();
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<>(counter);
		Thread t = new Thread(task);
		t.start();
		
		try {
			System.out.println(task.get() + " matching files!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class MatchCounter implements Callable<Integer> {

	private File directory;
	private String keyword;
	private int count;
	
	public MatchCounter(File directory, String keyword) {
		this.directory = directory;
		this.keyword = keyword;
	}
	
	@Override
	public Integer call() throws Exception {
		count = 0;
		
		try {
			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();
			
			for (File f : files) {
				if (f.isDirectory()) {
					MatchCounter counter = new MatchCounter(f, keyword);
					FutureTask<Integer> task = new FutureTask<>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				} else if (f.getName().endsWith("java")) {
					if (search(f)) {
						count++;
					}
				}
			}
			
			for (Future<Integer> result : results) {
				count += result.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

	private boolean search(File file) {
		try {
			try (Scanner scanner = new Scanner(file)) {
				boolean found = false;
				while (!found && scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (line.contains(keyword)) {
						found = true;
					}
				}
				return found;
			}
		} catch (IOException e) {
			return false;
		}
	}
}
