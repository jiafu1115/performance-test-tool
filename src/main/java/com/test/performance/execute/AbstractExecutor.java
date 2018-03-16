package com.test.performance.execute;

import com.test.performance.result.Result;

public abstract class AbstractExecutor implements Executor{
 
	@Override
	public Result execute(String trackingId) {
		long startTime = System.currentTimeMillis();
		
		boolean result = true;
		try{
			result = run(trackingId);
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
 		long comsumeTime = endTime - startTime;
 		return new Result(startTime, trackingId, result, comsumeTime);
  	}
	
	abstract boolean run(String trackingId);

}
