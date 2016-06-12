package com.xiaohao.multithread.blockingQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	public static final int FILE_QUEUE_SIZE = 10;
	public static final int SEARCH_THREADS = 50;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String directory = "/home/xiaohao/github/CoreJava/src";
		System.out.println("Enter keyword (e.g. final):");
		String keyword = in.nextLine();
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
		
		FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumerator).start();
		for (int i = 0; i < SEARCH_THREADS; i++) {
			new Thread(new SearchTask(queue, keyword)).start();
		}
	}

}

class FileEnumerationTask implements Runnable {

	public static final File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;
	
	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}
	
	@Override
	public void run() {
		try {
			enumerate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void enumerate(File directory) throws InterruptedException {
		File[] files = directory.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				enumerate(f);
			} else if (f.getName().endsWith("java")){
				queue.put(f);
			}
		}
	}
	
}

class SearchTask implements Runnable {

	private BlockingQueue<File> queue;
	private String keyword;
	
	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}
	
	@Override
	public void run() {
		try {
			boolean done = false;
			while (!done) {
				File file = queue.take();
				if (file == FileEnumerationTask.DUMMY) {
					queue.put(file);
					done = true;
				} else {
					search(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void search(File file) throws IOException {
		try (Scanner in = new Scanner(file)){
			int lineNumber = 0;
			while (in.hasNextLine()) {
				lineNumber++;
				String line = in.nextLine();
				if (line.contains(keyword)) {
					System.out.printf("%s found %s:%d: %s\n",
							Thread.currentThread(), file.getPath(), lineNumber, line);
				}
			}
		}
	}
}
