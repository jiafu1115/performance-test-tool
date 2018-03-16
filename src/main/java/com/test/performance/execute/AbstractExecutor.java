package com.test.performance.execute;

import com.test.performance.result.PerformanceResult;

public abstract class AbstractExecutor implements Executor{
 
	@Override
	public PerformanceResult execute(String trackingId) {
		long startTime = System.currentTimeMillis();
		
		ExecuteResult result = new ExecuteResult();
		try{
			result = run(trackingId);
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		
		long endTime = System.currentTimeMillis();
 		long comsumeTime = endTime - startTime;
 		return new PerformanceResult(startTime, trackingId, comsumeTime, result);
  	}
	
	protected abstract ExecuteResult run(String trackingId);

}
