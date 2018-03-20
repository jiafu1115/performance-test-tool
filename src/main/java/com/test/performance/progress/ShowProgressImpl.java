package com.test.performance.progress;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


public class ShowProgressImpl implements ShowProgressable {
	
	private static final String REPORT_FORMAT = "[Report] send total [%s] requests(fail [%s] requests) with [%.1f]TPS  (average response time [%.1f]mills in report interval) comsume [%s]seconds";

	private ScheduledExecutorService scheduledExecutorService;
	private int reportProgressIntervalInSeconds;
 	protected long startTime = System.currentTimeMillis();

	protected AtomicLong totalRequests = new AtomicLong();
	protected AtomicLong failRequests = new AtomicLong();
	
	protected AtomicLong totalRequestsInReportInterval = new AtomicLong();
	protected AtomicLong totalRequestsComsumeTimeInReportInterval = new AtomicLong();
 	
	public ShowProgressImpl(int reportProgressIntervalInSeconds) {
		this.reportProgressIntervalInSeconds = reportProgressIntervalInSeconds;
		this.scheduledExecutorService =  Executors.newScheduledThreadPool(1, r -> new Thread(r, "reportProgress"));
	}

	private class ReportProgress implements Runnable{

		@Override
		public void run() {
			synchronized (ShowProgressImpl.class) {
				long duration = System.currentTimeMillis() - startTime;
				System.out.println(String.format(REPORT_FORMAT, totalRequests.get(), failRequests.get(), totalRequests.get() * 1000d/duration, getAverageResponseTimeInReportInterval(), duration/1000));
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
	
	@Override
	public void record(boolean isSuccess, long duration){
		synchronized (ShowProgressImpl.class) {
			totalRequests.incrementAndGet();
			totalRequestsInReportInterval.incrementAndGet();
			totalRequestsComsumeTimeInReportInterval.getAndAdd(duration/1000/1000);
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
