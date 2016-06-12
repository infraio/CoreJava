package com.xiaohao.set;

import java.util.BitSet;

public class Sieve {
	
	public static void main(String[] args) {
		int n = 100000000;
		long startTime = System.currentTimeMillis();
		BitSet b = new BitSet(n + 1);
		int count = 0;
		int i;
		for (i = 2; i <= n; i++) {
			b.set(i);
		}
		
		i = 2;
		while ((i * i) <= n) {
			if (b.get(i)) {
				count++;
				int k = 2 * i;
				while (k <= n) {
					b.clear(k);
					k += i;
				}
			}
			i++;
		}
		
		while (i <= n) {
			if (b.get(i)) {
				count++;
			}
			i++;
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Found " + count + " primes from 0 to " + n);
		System.out.println((endTime - startTime) + " ms");
	}
}
