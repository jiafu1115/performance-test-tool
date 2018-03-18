package com.test.performance.progress;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


public class ShowProgressImpl implements ShowProgressable {
	
	private static final String REPORT_FORMAT = "[Report] send total requests [%s] (fail requests: [%s]) with TPS [%.1f] (average response time: [%s]mills) comsume [%s]seconds";

	private ScheduledExecutorService scheduledExecutorService;
	private int reportProgressIntervalInSeconds;
 	protected long startTime = System.currentTimeMillis();

	protected AtomicLong totalRequests = new AtomicLong();
	protected AtomicLong failRequests = new AtomicLong();
	protected AtomicLong totalRequestsComsumeTime = new AtomicLong();
 	
	public ShowProgressImpl(int reportProgressIntervalInSeconds) {
		this.reportProgressIntervalInSeconds = reportProgressIntervalInSeconds;
		this.scheduledExecutorService =  Executors.newScheduledThreadPool(1, r -> new Thread(r, "reportProgress"));
	}

	private class ReportProgress implements Runnable{

		@Override
		public void run() {
			synchronized (ShowProgressImpl.class) {
				long duration = System.currentTimeMillis() - startTime;
				System.out.println(String.format(REPORT_FORMAT, totalRequests.get(), failRequests.get(), totalRequests.get() * 1000d/duration, totalRequestsComsumeTime.get()/totalRequests.get(), duration/1000));
			}
			}
	}
	
	@Override
	public void record(boolean isSuccess, long duration){
		synchronized (ShowProgressImpl.class) {
			totalRequests.incrementAndGet();
			if(!isSuccess){
				failRequests.incrementAndGet();
			}
		}

  	}
 
	@Override
	public void start(){
		ReportProgress reportProgress = new ReportProgress();
		scheduledExecutorService.scheduleWithFixedDelay(reportProgress, 0, reportProgressIntervalInSeconds, TimeUnit.SECONDS);
	}
	
	@Override
	public void stop(){
		scheduledExecutorService.shutdownNow();
	}
}
