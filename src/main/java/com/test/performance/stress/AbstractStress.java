package com.test.performance.stress;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.test.performance.PerformanceUtil;
import com.test.performance.result.PerformanceResult;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public abstract class AbstractStress {
	
	private static final String REPORT_FORMAT = "[Report] send total requests [%s](fail requests: [%s]) with TPS [%s] comsume [%s]seconds";
 
 	protected long startTime = System.currentTimeMillis();
	protected long expectedEndTimeInMillis;
	protected long duration;
	protected String runId;
	protected AtomicLong totalRequests = new AtomicLong();
	protected AtomicLong failRequests = new AtomicLong();

	protected String ip = PerformanceUtil.getLocalIp();
	
	protected AbstractTestCaseExecutor abstractExecutor;
	protected PerformanceResultCollector resultCollector;
	
	private ScheduledExecutorService scheduledExecutorService =  Executors.newScheduledThreadPool(1, r -> new Thread(r, "reportProgress"));
 	
	private  class ReportProgress implements Runnable{

		@Override
		public void run() {
			long duration = System.currentTimeMillis() - startTime;
			System.out.println(String.format(REPORT_FORMAT, totalRequests.get(), failRequests.get(), totalRequests.get() * 1000/duration, duration/1000));
		}
		
	}
  
	public AbstractStress(AbstractTestCaseExecutor abstractExecutor,
	PerformanceResultCollector resultCollector, String runId, long durationInMills) {
		this.abstractExecutor = abstractExecutor;
		this.resultCollector = resultCollector;
		this.runId = runId;
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
			String trackingID = createTrackingID();
			PerformanceResult result = abstractExecutor.execute(runId, trackingID);
			if(!result.isSuccess()){
				failRequests.incrementAndGet();
			}
			resultCollector.record(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String createTrackingID() {
		return String.format("%s_%d_%d_%d", ip, Thread.currentThread().getId(), System.currentTimeMillis(), totalRequests.incrementAndGet());
	}
  	
}
