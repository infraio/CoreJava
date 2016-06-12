package com.xiaohao.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		Set<String> words = new HashSet<>();
		long totalTime = 0;

		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String word = in.next();
			long startTime = System.currentTimeMillis();
			words.add(word);
			totalTime += System.currentTimeMillis() - startTime;
		}

		Iterator<String> iter = words.iterator();
		for (int i = 0; i < 20 && iter.hasNext(); i++) {
			System.out.println(iter.next());
		}
		System.out.println("......");
		System.out.println("Add " + words.size() + " distinct words take " 
				+ totalTime + " ms");
	}

}
