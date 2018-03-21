package com.test.performance.stress;

import java.util.ArrayList;

import com.test.performance.progress.ShowProgressable;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class StressWithThreadNumberControl extends AbstractStress implements Runnable {

	protected long threadNumber;

	public StressWithThreadNumberControl(Class<AbstractTestCaseExecutor> abstractExecutor, PerformanceResultCollector resultCollector, ShowProgressable showProgressable, long durationInMills, long threadNumber) {
		super(abstractExecutor, resultCollector, showProgressable, durationInMills);
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
