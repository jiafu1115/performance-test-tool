package com.test.performance.result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PerformanceResultCollector {
	
	protected ExecutorService threadPool =  new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            3, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            r -> new Thread(r, "recordResult"));
	
	private CollectMethod collectResultable;
  
	public PerformanceResultCollector(CollectMethod collectResultable) {
		this.collectResultable = collectResultable;
	}
 
	public void record(PerformanceResult resultRecord) {
		threadPool.submit(new Runnable() {
			
			@Override
			public void run() {
				try{
					collectResultable.collect(resultRecord);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

}
