package com.test.performance.stress;


import java.util.concurrent.atomic.AtomicLong;

import com.test.performance.PerformanceUtil;
import com.test.performance.progress.ShowProgressable;
import com.test.performance.result.PerformanceResult;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public abstract class AbstractStress {
	
 
 	protected long startTime = System.currentTimeMillis();
	protected long expectedEndTimeInMillis;
	protected long duration;
	
	protected AtomicLong totalRequests = new AtomicLong();
	protected String ip = PerformanceUtil.getLocalIp();
	
	protected AbstractTestCaseExecutor abstractExecutor;
	protected PerformanceResultCollector resultCollector;
	protected ShowProgressable showProgressable;
 
  
	public AbstractStress(AbstractTestCaseExecutor abstractExecutor,
	PerformanceResultCollector resultCollector, ShowProgressable showProgressable, long durationInMills) {
		this.abstractExecutor = abstractExecutor;
		this.resultCollector = resultCollector;
		this.showProgressable = showProgressable;
		this.duration = durationInMills;
		this.expectedEndTimeInMillis = startTime + durationInMills;
	}
	
	public void stressWithProgreeReport(){
		this.showProgressable.start();
		this.stress();
		this.showProgressable.stop();
	}
	
	public abstract void stress();
	
	protected void executeTestCase() {
		try {
			String trackingID = createTrackingID();
			PerformanceResult result = abstractExecutor.execute(trackingID);
			showProgressable.record(result.isSuccess(), result.getConsumeTimeInMillis());
			resultCollector.record(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String createTrackingID() {
		return String.format("%s_%d_%d_%d", ip, Thread.currentThread().getId(), System.currentTimeMillis(), totalRequests.incrementAndGet());
	}
	
 
}
