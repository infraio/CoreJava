package com.xiaohao.multithread.bounce;

import java.awt.Component;

public class BallRunnable implements Runnable{
	private Ball ball;
	private BallComponent component;
	private int steps;
	private int delay;
	
	public BallRunnable(Ball ball, BallComponent component, int steps, int delay) {
		this.ball = ball;
		this.component = component;
		this.steps = steps;
		this.delay = delay;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < steps; i++) {
				ball.move(component.getBounds());
				component.paintCompenent(component.getGraphics());;
				Thread.sleep(delay);
			}
		} catch (InterruptedException e) {
			
		}
		
	}
}
