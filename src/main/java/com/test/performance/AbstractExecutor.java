package com.test.performance;

public abstract class AbstractExecutor implements Executor{

	@Override
	public void execute(String trackingId) {
		boolean result = run(trackingId);
	}
	
	abstract boolean run(String trackingId);

}
