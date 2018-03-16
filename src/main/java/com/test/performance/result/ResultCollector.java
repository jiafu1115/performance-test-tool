package com.test.performance.result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultCollector {
	
	protected ExecutorService threadPool = Executors.newCachedThreadPool(r -> new Thread(r, "recordResult"));
	
	private CollectMethod collectResultable;
  
	public ResultCollector(CollectMethod collectResultable) {
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
