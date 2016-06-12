package com.xiaohao.multithread.unsynch;

public class Bank {
	private final double[] accounts;
	private static final int DELAY = 10;
	
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < n; i++) {
			accounts[i] = initialBalance;
		}
	}
	
	public void transfer(int from, int to, double amount) throws InterruptedException {
		if (from < 0 || from >= accounts.length) {
			System.out.println("Wrong from account : " + from);
		}
		if (to < 0 || to > accounts.length) {
			System.out.println("Wrong to account : " + to);
		}
		if (accounts[from] < amount) {
			return;
		}
		System.out.println("Current thread is " + Thread.currentThread());
		accounts[from] -= amount;
		Thread.sleep(DELAY);
		accounts[to] += amount;
		System.out.printf(" %10.2f from %d to %d", amount, from, to);
		System.out.printf(" Bank total balance is %10.2f", getTotalBalance());
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
