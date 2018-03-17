package com.test.performance.testcase;


import com.test.performance.result.PerformanceResult;

public abstract class AbstractTestCaseExecutor implements TestCaseExecutor{
   
	@Override
	public PerformanceResult execute(String runId, String trackingId) {
		long startTime = System.currentTimeMillis();
		
		TestCaseResult result = new TestCaseResult();
		try{
			result = run(trackingId);
		}catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		
		long endTime = System.currentTimeMillis();
 		long comsumeTime = endTime - startTime;
 		return new PerformanceResult(runId, trackingId, result.isSuccess(), result.getMessage(), startTime, comsumeTime);
  	}
	
	protected abstract TestCaseResult run(String trackingId);
	
	public boolean prepareEnvironment(){
		return true;
	}


	

}
