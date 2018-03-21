package com.test.performance.testcase;


import com.test.performance.result.PerformanceResult;

public abstract class AbstractTestCaseExecutor implements TestCaseExecutor{
   
	@Override
	public PerformanceResult execute(String trackingId) {
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
 		return new PerformanceResult(trackingId, result.isSuccess(), result.getMessage(), startTime, comsumeTime);
  	}
	
	protected abstract TestCaseResult run(String trackingId);
	
 	public boolean prepare(){
		System.out.println("[Prepare][Test Case] start");
 		boolean result = prepareEnvironment();
		System.out.println("[Prepare][Test Case] complete with result: " + result);
		
		return result;
 	}
 	
 	public void destroy(){
		System.out.println("[Cleanup][Test Case] start");
 		destoryEnvironment();
		System.out.println("[Cleanup][Test Case] complete");
  	}
	
	protected boolean prepareEnvironment(){
		return true;
	}
	
	protected void destoryEnvironment(){
		// no implement
	}

	public void beforeTest(){
		// no implement
	}
	
	public void afterTest(){
	   // no implement
	}

}
