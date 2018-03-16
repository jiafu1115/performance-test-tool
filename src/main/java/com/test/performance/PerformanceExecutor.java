package com.test.performance;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class PerformanceExecutor {

	@Parameter(required = true, names = { "--implement", "-i" })
	private String implementClass;

	@Parameter(names = { "--threadNumber", "-t" })
	private int threadNumber = 1;

	@Parameter(names = { "--duration", "-d" })
	private int durationInSeconds = 10;
  
	public static void main(String... argv) throws Exception {
		PerformanceExecutor performanceExecutor = new PerformanceExecutor();
		JCommander.newBuilder().addObject(performanceExecutor).build().parse(argv);
		performanceExecutor.run();
	}

	public void run() throws Exception {
		Class<?> implementClazz = Class.forName(implementClass);
		String ip = PerformanceUtil.getLocalIp();
		final Object implementInstance = implementClazz.newInstance();
		final Method stressMethod = implementClazz.getMethod("execute", String.class);

		long startTime = System.currentTimeMillis();
		final long endTimeInMillis = startTime + durationInSeconds * 1000;
		
		AtomicLong totalRequests = new AtomicLong();

		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < threadNumber; i++) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						long currentTimeMillis = System.currentTimeMillis();
						if (currentTimeMillis >= endTimeInMillis) {
							break;
						}
						try {
							String trackingID = String.format("%s_%d_%d_%d", ip, Thread.currentThread().getId(), currentTimeMillis, totalRequests.incrementAndGet());
							stressMethod.invoke(implementInstance, trackingID);
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
 

}