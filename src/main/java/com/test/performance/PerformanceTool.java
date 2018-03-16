package com.test.performance;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.test.performance.result.CollectMethod;
import com.test.performance.result.Result;
import com.test.performance.result.ResultCollector;

public class PerformanceTool {

	@Parameter(required = true, names = { "--implement", "-i" })
	private String implementClass;
	
	@Parameter(required = true, names = { "--collect", "-c" })
	private String collectClass;

	@Parameter(names = { "--threadNumber", "-t" })
	private int threadNumber = 1;

	@Parameter(names = { "--duration", "-d" })
	private int durationInSeconds = 10;
  
	public static void main(String... argv) throws Exception {
		PerformanceTool performancePool = new PerformanceTool();
		JCommander.newBuilder().addObject(performancePool).build().parse(argv);
		performancePool.run();
	}

	public void run() throws Exception {
		
 		AbstractExecutor abstractExecutor = getExecutor();
		ResultCollector resultCollector = getResultCollector();

		String ip = PerformanceUtil.getLocalIp();
		long startTime = System.currentTimeMillis();
		final long expectedEndTimeInMillis = startTime + durationInSeconds * 1000;
		AtomicLong totalRequests = new AtomicLong();

		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < threadNumber; i++) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						long currentTimeMillis = System.currentTimeMillis();
						if (currentTimeMillis >= expectedEndTimeInMillis) {
							break;
						}
						try {
							String trackingID = String.format("%s_%d_%d_%d", ip, Thread.currentThread().getId(), currentTimeMillis, totalRequests.incrementAndGet());
							Result result = abstractExecutor.execute(trackingID);
							resultCollector.record(result);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			});
			
			threads.add(thread);
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join();
		}
		
		long actualDurationInSeconds = (System.currentTimeMillis()- startTime)/1000;
		System.out.println(String.format("send [%d] requests in [%d], average tps is [%d]", totalRequests.get(), actualDurationInSeconds, totalRequests.get()/actualDurationInSeconds));

	}

	private AbstractExecutor getExecutor()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (AbstractExecutor) Class.forName(implementClass).newInstance();
	}

	private ResultCollector getResultCollector()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return new ResultCollector((CollectMethod) Class.forName(collectClass).newInstance());
	}
 

}