package com.test.performance.stress;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class StressWithTpsControl extends AbstractStress implements Runnable {

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private long tps;

	public StressWithTpsControl(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector,
			String runId, long durationInMills, long tps) {
		super(abstractExecutor, resultCollector, runId, durationInMills);
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
			e.printStackTrace();
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
			List<Runnable> uncompletedTasks = executorService.shutdownNow();
			System.out.println("uncomplete tasks: " + uncompletedTasks.size());
		}

	}

}
