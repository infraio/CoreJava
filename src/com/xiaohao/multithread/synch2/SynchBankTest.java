package com.xiaohao.multithread.synch2;

public class SynchBankTest {

	public static final int ACCOUNTS_NUM = 100;
	public static final double INITIAL_BALANCE = 1000;
	
	public static void main(String[] args) {
		Bank bank = new Bank(ACCOUNTS_NUM, INITIAL_BALANCE);
		for (int i = 0; i < ACCOUNTS_NUM; i++) {
			TransferRunnable tr = new TransferRunnable(bank, i, INITIAL_BALANCE);
			Thread t = new Thread(tr);
			t.start();
		}
	}

}
