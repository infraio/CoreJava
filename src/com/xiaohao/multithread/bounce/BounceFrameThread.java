package com.xiaohao.multithread.bounce;

import java.awt.Rectangle;

public class BounceFrameThread extends BounceFrame {
	
	public BounceFrameThread() {
		super();
		setTitle("BounceThread");
	}
	
	@Override
	protected void addBall() {
		Rectangle rect = comp.getBounds();
		Ball ball = new Ball(rect.getMinX(), rect.getMaxX(), rect.getMinY(), rect.getMaxY());
		comp.add(ball);
		Runnable r = new BallRunnable(ball, comp, STEPS, DELAY);
		Thread t = new Thread(r);
		t.start();
	}
}
