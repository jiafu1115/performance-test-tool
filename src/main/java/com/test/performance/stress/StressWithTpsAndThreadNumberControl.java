package com.test.performance.stress;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class StressWithTpsAndThreadNumberControl extends AbstractStress implements Runnable{
 
	private List<ScheduledExecutorService> scheduledExecutorServices = new ArrayList<ScheduledExecutorService>();
	private double rate;
	protected long threadNumber;
 
	public StressWithTpsAndThreadNumberControl(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector,
			long durationInMills, long threadNumber, long tps) {
		super(abstractExecutor, resultCollector, durationInMills);
		this.threadNumber = threadNumber;

		for (int i = 0; i < threadNumber; i++) {
			this.scheduledExecutorServices.add(Executors.newScheduledThreadPool(1));
		}
		
		double tpsForSingleThread = Double.parseDouble(String.valueOf(tps)) / this.threadNumber;
		rate =  1000d * 1000 / tpsForSingleThread ;
		System.out.println(String.format("########tps to execute for every thead: [%.1f] ########", tpsForSingleThread));
		System.out.println(String.format("########rate to execute for every thead: [%.1f] Micros ########", rate));
	}

	@Override
	public void stress() {
		startThreads();
		awaitThreadsComplete();
	}

	private void startThreads() {
		for(ScheduledExecutorService scheduledExecutorService : scheduledExecutorServices) {
			scheduledExecutorService.scheduleAtFixedRate(this, 0, (int)rate, TimeUnit.MICROSECONDS);
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
