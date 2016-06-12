package com.xiaohao.multithread.synch2;

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
				String result = bank.transfer(fromAccount, toAccount, amount) ?
						"Transfer success!" : "Transfer fail!";
				System.out.println(result);
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
