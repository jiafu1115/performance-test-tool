package com.test.performance.stress;

import java.util.ArrayList;

import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class StressWithThreadNumberControl extends AbstractStress implements Runnable {

	private long threadNumber;

	public StressWithThreadNumberControl(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector,
			long durationInMills, long threadNumber) {
		super(abstractExecutor, resultCollector, durationInMills);
		this.threadNumber = threadNumber;
	}

	@Override
	public void stress() {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		addAndStartThread(threads);
		waitThreadCompleteTasks(threads);
	}

	private void waitThreadCompleteTasks(ArrayList<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void addAndStartThread(ArrayList<Thread> threads) {
		for (int i = 0; i < threadNumber; i++) {
			Thread thread = new Thread(this, "stress");
			threads.add(thread);
			thread.start();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (System.currentTimeMillis() >= expectedEndTimeInMillis) {
				break;
			}
			executeTestCase();
		}
	}

}
