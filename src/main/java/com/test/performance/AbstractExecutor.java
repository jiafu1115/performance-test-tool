package com.test.performance;

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
 		Result resultRecord = new Result(startTime, trackingId, result, comsumeTime);
 		
 		return resultRecord;
 	}
	
	abstract boolean run(String trackingId);

}
