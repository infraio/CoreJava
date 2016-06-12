package com.xiaohao.multithread.unsynch;

public class TransferRunnable implements Runnable {
	
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	public TransferRunnable(Bank bank, int from, double max) {
		this.bank = bank;
		this.fromAccount = from;
		this.maxAmount = max;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				int toAccount = (int) (bank.size() * Math.random());
				double amount = maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
