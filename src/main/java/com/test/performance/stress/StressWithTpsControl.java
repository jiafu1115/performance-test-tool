package com.test.performance.stress;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.test.performance.execute.AbstractExecutor;
import com.test.performance.result.ResultCollector;

public class StressWithTpsControl extends AbstractStress implements Runnable{
 
	private ScheduledExecutorService scheduledExecutorService;
	private long tps;

	public StressWithTpsControl(AbstractExecutor abstractExecutor, ResultCollector resultCollector, long duration,
			long tps) {
		super(abstractExecutor, resultCollector, duration);
		this.tps = tps;
		this.scheduledExecutorService = Executors.newScheduledThreadPool(10);
	}

	@Override
	public void stress() {
		scheduledExecutorService.scheduleAtFixedRate(this, 0, 1000l * 1000 * 1000 / tps, TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis >= expectedEndTimeInMillis) {
			if(!this.scheduledExecutorService.isShutdown()){
				this.scheduledExecutorService.shutdown();
			}
			return;
		}
		executeTestCase();
	}

}
