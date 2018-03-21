package com.test.performance.stress;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.test.performance.progress.ShowProgressable;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class StressWithTpsControl extends AbstractStress implements Runnable {

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private long tps;

	public StressWithTpsControl(Class<AbstractTestCaseExecutor> abstractExecutor, PerformanceResultCollector resultCollector, ShowProgressable showProgressable
			, long durationInMills, long tps) {
		super(abstractExecutor, resultCollector, showProgressable, durationInMills);
		this.tps = tps;
	}

	@Override
	public void stress() {
		startThreads();
		awaitThreadsComplete();
	}

	private void startThreads() {
		long interval = 1000 * 1000 * 1000 / tps;
		while(System.currentTimeMillis() < this.expectedEndTimeInMillis){
			sleep(interval);
			executorService.submit(this);
		}
	}

	private void sleep(long interval) {
		try {
			TimeUnit.NANOSECONDS.sleep(interval);
		} catch (InterruptedException e) {
			//ignore
		}
	}

	private void awaitThreadsComplete() {
		try {
			executorService.awaitTermination(duration, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		if (!executorService.isShutdown()) {
			executorService.shutdown();
		}
	}
 	 

}
