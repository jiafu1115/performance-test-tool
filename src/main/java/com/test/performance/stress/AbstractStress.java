package com.test.performance.stress;


import java.util.concurrent.atomic.AtomicLong;

import com.test.performance.common.ExecuteInfo;
import com.test.performance.common.PerformanceUtil;
import com.test.performance.progress.ShowProgressable;
import com.test.performance.result.PerformanceResult;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public abstract class AbstractStress {
	
 
 	protected long startTime = System.currentTimeMillis();
	protected long expectedEndTimeInMillis;
	protected long duration;
	
	protected AtomicLong totalRequests = new AtomicLong();
	protected String runIp = PerformanceUtil.getLocalIp();
	
	protected Class<AbstractTestCaseExecutor> abstractExecutorClazz;
	protected PerformanceResultCollector resultCollector;
	protected ShowProgressable showProgressable;
 
  
	public AbstractStress(Class<AbstractTestCaseExecutor> abstractExecutor,
	PerformanceResultCollector resultCollector, ShowProgressable showProgressable, long durationInMills) {
		this.abstractExecutorClazz = abstractExecutor;
		this.resultCollector = resultCollector;
		this.showProgressable = showProgressable;
		this.duration = durationInMills;
		this.expectedEndTimeInMillis = startTime + durationInMills;
	}
	
	public void stressWithProgreeReport(){
		AbstractTestCaseExecutor testCase = PerformanceUtil.getClassInstace(this.abstractExecutorClazz);
		boolean isSuccess = testCase.prepareEnvironment();
		if(!isSuccess){
			System.err.println("fail to prepare environment");
			return;
		}
		
		this.showProgressable.start();
		try{
			this.stress();
		}finally{
			try{
				testCase.destoryEnvironment();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.showProgressable.stop();
	}
	
	public abstract void stress();
	
	protected void executeTestCase() {
		String trackingID = createTrackingID();
		AbstractTestCaseExecutor testCase = PerformanceUtil.getClassInstace(this.abstractExecutorClazz);
	
		try {
		    testCase.beforeTest();
			PerformanceResult result = testCase.execute(trackingID);
			showProgressable.record(result.isSuccess(), result.getDuration());
			resultCollector.record(getExecuteInfo(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				testCase.afterTest();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private ExecuteInfo getExecuteInfo() {
		long threadId = Thread.currentThread().getId();
		ExecuteInfo executeInfo = new ExecuteInfo(runIp, threadId);
		return executeInfo;
	}

	private String createTrackingID() {
		return String.format("%s_%d_%d_%d", runIp, Thread.currentThread().getId(), System.currentTimeMillis(), totalRequests.incrementAndGet());
	}
	
 
}
