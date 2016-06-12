package com.xiaohao.set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTest {
	
	public static void main(String[] args) {
		List<Integer> a = new LinkedList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		
		List<Integer> b = new LinkedList<>();
		b.add(1);
		b.add(2);
		b.add(3);
		b.add(4);
		
		ListIterator<Integer> aIter = a.listIterator();
		Iterator<Integer> bIter = b.iterator();
		while (bIter.hasNext()) {
			if (aIter.hasNext()) {
				aIter.next();
			}
			aIter.add(bIter.next());
		}
		
		System.out.println(a);
		
		bIter = b.iterator();
		while (bIter.hasNext()) {
			bIter.next();
			if (bIter.hasNext()) {
				bIter.next();
				bIter.remove();
			}
		}
		
		System.out.println(b);
		
		a.removeAll(b);
		
		System.out.println(a);
	}
}
