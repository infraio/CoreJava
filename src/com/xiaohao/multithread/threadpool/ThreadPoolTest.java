package com.xiaohao.multithread.threadpool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String directory = "/home/xiaohao/github/CoreJava/src";
		System.out.println("Enter keyword (e.g. final):");
		String keyword = in.nextLine();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
		Future<Integer> result = pool.submit(counter);

		try {
			System.out.println(result.get() + " matching files!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
		System.out.println("largest pool size is " + largestPoolSize);
	}
}

class MatchCounter implements Callable<Integer> {

	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;
	
	public MatchCounter(File directory, String keyword, ExecutorService pool) {
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}
	
	@Override
	public Integer call() throws Exception {
		count = 0;
		
		try {
			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();
			
			for (File f : files) {
				if (f.isDirectory()) {
					MatchCounter counter = new MatchCounter(f, keyword, pool);
					Future<Integer> result = pool.submit(counter);
					results.add(result);
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
