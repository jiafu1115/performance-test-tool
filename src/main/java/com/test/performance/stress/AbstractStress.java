package com.test.performance.stress;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.test.performance.PerformanceUtil;
import com.test.performance.execute.AbstractExecutor;
import com.test.performance.result.PerformanceResult;
import com.test.performance.result.ResultCollector;

public abstract class AbstractStress {
	
	private static final String REPORT_FORMAT = "send total requests [%s] with tps [%s]";
 
 	protected long startTime = System.currentTimeMillis();
	protected long expectedEndTimeInMillis;
	protected long duration;
	protected AtomicLong totalRequests = new AtomicLong();
	protected String ip = PerformanceUtil.getLocalIp();
	
	protected AbstractExecutor abstractExecutor;
	protected ResultCollector resultCollector;
	
	private ScheduledExecutorService scheduledExecutorService =  Executors.newScheduledThreadPool(1);
 	
  
	public AbstractStress(AbstractExecutor abstractExecutor,
	ResultCollector resultCollector, long durationInMills) {
		this.abstractExecutor = abstractExecutor;
		this.resultCollector = resultCollector;
		this.duration = durationInMills;
		this.expectedEndTimeInMillis = startTime + durationInMills;
	}
	
	public void stressWithProgreeReport(){
		scheduledExecutorService.scheduleWithFixedDelay(()-> System.out.println(String.format(REPORT_FORMAT, totalRequests.get(), totalRequests.get()/(System.currentTimeMillis() - startTime)/1000)), 10, 10, TimeUnit.SECONDS);
		this.stress();
		scheduledExecutorService.shutdown();
	}
	
	public abstract void stress();
	
	protected void executeTestCase() {
		try {
			String trackingID = getTrackingID();
			PerformanceResult result = abstractExecutor.execute(trackingID);
			resultCollector.record(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getTrackingID() {
		return String.format("%s_%d_%d_%d", ip, Thread.currentThread().getId(), System.currentTimeMillis(), totalRequests.incrementAndGet());
	}
  	
}
