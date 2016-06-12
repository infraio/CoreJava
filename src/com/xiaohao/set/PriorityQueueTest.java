package com.xiaohao.set;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {
		PriorityQueue<GregorianCalendar> pq = new PriorityQueue<>();
		pq.add(new GregorianCalendar(1906, Calendar.DECEMBER, 9));
		pq.add(new GregorianCalendar(1888, Calendar.DECEMBER, 10));
		pq.add(new GregorianCalendar(1999, Calendar.DECEMBER, 3));
		pq.add(new GregorianCalendar(1949, Calendar.OCTOBER, 1));
		
		System.out.println("Iterating over elements:");
		for (GregorianCalendar gc : pq) {
			System.out.println(gc);
		}
		System.out.println("Removing elements:");
		while (!pq.isEmpty()) {
			System.out.println(pq.remove());
		}
	}

}
