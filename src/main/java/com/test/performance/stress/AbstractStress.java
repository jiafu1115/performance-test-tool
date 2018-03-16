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
	
	private static final String REPORT_FORMAT = "[Report] send total requests [%s] with TPS [%s] comsume [%s]seconds";
 
 	protected long startTime = System.currentTimeMillis();
	protected long expectedEndTimeInMillis;
	protected long duration;
	protected AtomicLong totalRequests = new AtomicLong();
	protected String ip = PerformanceUtil.getLocalIp();
	
	protected AbstractExecutor abstractExecutor;
	protected ResultCollector resultCollector;
	
	private ScheduledExecutorService scheduledExecutorService =  Executors.newScheduledThreadPool(1);
 	
	private  class ReportProgress implements Runnable{

		@Override
		public void run() {
			System.out.println(String.format(REPORT_FORMAT, totalRequests.get(), totalRequests.get() * 1000/(System.currentTimeMillis() - startTime), (System.currentTimeMillis() - startTime)/1000));
		}
		
	}
  
	public AbstractStress(AbstractExecutor abstractExecutor,
	ResultCollector resultCollector, long durationInMills) {
		this.abstractExecutor = abstractExecutor;
		this.resultCollector = resultCollector;
		this.duration = durationInMills;
		this.expectedEndTimeInMillis = startTime + durationInMills;
	}
	
	public void stressWithProgreeReport(){
		ReportProgress reportProgress = new ReportProgress();
		scheduledExecutorService.scheduleWithFixedDelay(reportProgress, 0, 1, TimeUnit.SECONDS);
		this.stress();
		reportProgress.run();
		scheduledExecutorService.shutdownNow();
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
