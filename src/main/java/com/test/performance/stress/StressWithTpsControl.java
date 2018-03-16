package com.test.performance.stress;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.test.performance.execute.AbstractExecutor;
import com.test.performance.result.ResultCollector;

public class StressWithTpsControl extends AbstractStress implements Runnable{
 
	private ScheduledExecutorService scheduledExecutorService;
	private long tps;

	public StressWithTpsControl(AbstractExecutor abstractExecutor, ResultCollector resultCollector, long durationInMills,
			long tps) {
		super(abstractExecutor, resultCollector, durationInMills);
		this.tps = tps;
		this.scheduledExecutorService = Executors.newScheduledThreadPool(50);
	}

	@Override
	public void stress() {
		scheduledExecutorService.scheduleAtFixedRate(this, 0, 1000l * 1000 * 1000 / tps, TimeUnit.NANOSECONDS);
		try {
			scheduledExecutorService.awaitTermination(duration, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if (System.currentTimeMillis() >= expectedEndTimeInMillis) {
			if(!this.scheduledExecutorService.isShutdown()){
				this.scheduledExecutorService.shutdown();
			}
			return;
		}
		executeTestCase();
	}

}
