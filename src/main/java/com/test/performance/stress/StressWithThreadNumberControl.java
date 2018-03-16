package com.test.performance.stress;

import java.util.ArrayList;
import com.test.performance.execute.AbstractExecutor;
import com.test.performance.result.ResultCollector;

public class StressWithThreadNumberControl extends AbstractStress implements Runnable {

	private long threadNumber;

	public StressWithThreadNumberControl(AbstractExecutor abstractExecutor, ResultCollector resultCollector,
			long duration, long threadNumber) {
		super(abstractExecutor, resultCollector, duration);
		this.threadNumber = threadNumber;
	}

	@Override
	public void stress() {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < threadNumber; i++) {
			Thread thread = new Thread(this, "stress");
			threads.add(thread);
			thread.start();
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			long currentTimeMillis = System.currentTimeMillis();
			if (currentTimeMillis >= expectedEndTimeInMillis) {
				break;
			}
			executeTestCase();
		}
	}

}
