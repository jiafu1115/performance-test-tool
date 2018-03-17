package com.test.performance.stress;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class StressWithTpsControl extends AbstractStress implements Runnable{
 
	private List<ScheduledExecutorService> scheduledExecutorServices = new ArrayList<ScheduledExecutorService>();
	private long tps;

	public StressWithTpsControl(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector, String runId,
			long durationInMills, long threadNumber, long tps) {
		super(abstractExecutor, resultCollector, runId, durationInMills, threadNumber);
		this.tps = tps;
		for (int i = 0; i < threadNumber; i++) {
			this.scheduledExecutorServices.add(Executors.newScheduledThreadPool(1));
		}
	}

	@Override
	public void stress() {
		startThreads();
		awaitThreadsComplete();
	}

	private void startThreads() {
		long period = 1000l * 1000 * 1000 / tps / this.threadNumber;
		System.out.println("period to execute for every thead: " + period + "ns");
		for(ScheduledExecutorService scheduledExecutorService : scheduledExecutorServices){
			scheduledExecutorService.scheduleAtFixedRate(this, 0, period, TimeUnit.NANOSECONDS);
		}
	}

	private void awaitThreadsComplete() {
		for(ScheduledExecutorService scheduledExecutorService : scheduledExecutorServices){
			try {
				scheduledExecutorService.awaitTermination(duration, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}

	@Override
	public void run() {
		if (System.currentTimeMillis() >= expectedEndTimeInMillis) {
			shutdownThreads();
			return;
		}
		executeTestCase();
	}

	private void shutdownThreads() {
		for(ScheduledExecutorService scheduledExecutorService : scheduledExecutorServices){
			if(!scheduledExecutorService.isShutdown()){
			  scheduledExecutorService.shutdown();
			}			
		}
	}

}
