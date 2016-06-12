package com.xiaohao.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTest {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			numbers.add(i);
		}
		List<Integer> printNumbers = numbers.subList(0, 6);
		
		System.out.println(printNumbers);
		
		Collections.shuffle(numbers);
		printNumbers = numbers.subList(0, 6);
		
		System.out.println(printNumbers);
		
		Collections.sort(printNumbers);
		
		System.out.println(printNumbers);
	}

}
