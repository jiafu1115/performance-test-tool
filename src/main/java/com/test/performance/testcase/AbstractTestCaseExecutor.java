package com.test.performance.testcase;


import com.test.performance.result.PerformanceResult;

public abstract class AbstractTestCaseExecutor implements TestCaseExecutor{
   
	@Override
	public PerformanceResult execute(String trackingId) {
		long startTime = System.nanoTime();
		
		TestCaseResult result = new TestCaseResult();
		try{
			result = run(trackingId);
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		
		long endTime = System.nanoTime();
 		long comsumeTime = endTime - startTime;
 		return new PerformanceResult(trackingId, result.isSuccess(), result.getMessage(), startTime, comsumeTime);
  	}
	
	protected abstract TestCaseResult run(String trackingId);
	
	public boolean prepareEnvironment(){
		return true;
	}


	

}
