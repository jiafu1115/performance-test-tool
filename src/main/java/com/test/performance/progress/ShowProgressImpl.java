package com.test.performance.progress;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


public class ShowProgressImpl implements ShowProgressable {
	
	private static final String REPORT_FORMAT = "[Report] send total [%s] requests(fail [%s] requests) with [%.1f]TPS  (average response time [%.1f]mills in last [%d]s) comsume [%s]seconds";

	private ScheduledExecutorService scheduledExecutorService;
	
 	private long startTime = System.currentTimeMillis();
	private int reportProgressIntervalInSeconds;
 
 	private AtomicLong totalRequests = new AtomicLong();
 	private AtomicLong failRequests = new AtomicLong();
	
 	private AtomicLong totalRequestsInReportInterval = new AtomicLong();
 	private AtomicLong totalRequestsComsumeTimeInReportInterval = new AtomicLong();
 	
	private class ReportProgress implements Runnable{

		@Override
		public void run() {
			synchronized (ShowProgressImpl.class) {
				long duration = System.currentTimeMillis() - startTime;
				System.out.println(String.format(REPORT_FORMAT, totalRequests.get(), failRequests.get(), totalRequests.get() * 1000d/duration, getAverageResponseTimeInReportInterval(), reportProgressIntervalInSeconds, duration/1000));
				resetIntervalData();
			}
		}

		private void resetIntervalData() {
			totalRequestsInReportInterval.set(0);
			totalRequestsComsumeTimeInReportInterval.set(0);
		}
		
		private double getAverageResponseTimeInReportInterval(){
			long totalRequestsInReportIntervalValue = totalRequestsInReportInterval.get();
			if(totalRequestsInReportIntervalValue == 0){
				return -1;
			}
			return Double.valueOf(totalRequestsComsumeTimeInReportInterval.get())/totalRequestsInReportIntervalValue;
			
		}
	}
 	
	public ShowProgressImpl(int reportProgressIntervalInSeconds) {
		this.reportProgressIntervalInSeconds = reportProgressIntervalInSeconds;
		this.scheduledExecutorService =  Executors.newScheduledThreadPool(1, r -> new Thread(r, "reportProgress"));
	}
 
	@Override
	public void record(boolean isSuccess, long duration){
		synchronized (ShowProgressImpl.class) {
			totalRequests.incrementAndGet();
			totalRequestsInReportInterval.incrementAndGet();
			totalRequestsComsumeTimeInReportInterval.getAndAdd(duration);
			if(!isSuccess){
				failRequests.incrementAndGet();
			}
		}

  	}
 
	@Override
	public void start(){
		ReportProgress reportProgress = new ReportProgress();
		scheduledExecutorService.scheduleWithFixedDelay(reportProgress, reportProgressIntervalInSeconds, reportProgressIntervalInSeconds, TimeUnit.SECONDS);
	}
	
	@Override
	public void stop(){
		scheduledExecutorService.shutdownNow();
		ReportProgress reportProgress = new ReportProgress();
		reportProgress.run();
	}
}
