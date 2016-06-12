package com.xiaohao.multithread.synch2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private final double[] accounts;
	private static final int DELAY = 10;
	
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < n; i++) {
			accounts[i] = initialBalance;
		}
	}
	
	public synchronized boolean transfer(int from, int to, double amount) throws InterruptedException {
		if (from < 0 || from >= accounts.length) {
			System.out.println("Wrong from account : " + from);
		}
		if (to < 0 || to > accounts.length) {
			System.out.println("Wrong to account : " + to);
		}
		while (accounts[from] < amount) {
			wait();
		}
		System.out.println("Current thread is " + Thread.currentThread());
		accounts[from] -= amount;
		Thread.sleep(DELAY);
		accounts[to] += amount;
		System.out.printf(" %10.2f from %d to %d\n", amount, from, to);
		System.out.printf(" Bank total balance is %10.2f\n", getTotalBalance());
		notifyAll();
		return true;
	}
	
	public double getTotalBalance() {
		double sum = 0;
		
		for (double d : accounts) {
			sum += d;
		}
		
		return sum;
	}
	
	public int size() {
		return accounts.length;
	}
}
