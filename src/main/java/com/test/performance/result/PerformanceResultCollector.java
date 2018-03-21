package com.test.performance.result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.test.performance.common.ExecuteInfo;
import com.test.performance.common.RunInfo;

public class PerformanceResultCollector {
	
	protected ExecutorService threadPool =  new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            3, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            r -> new Thread(r, "recordResult"));
	
	private RunInfo runInfo;
	private CollectMethod collectResultable;
   
 
	public boolean prepare() {
		System.out.println("####prepare result collector start####");
		boolean isPrepareSuccess = collectResultable.prepareEnvironment(runInfo);
		System.out.println("####prepare result collector complete####");
		return isPrepareSuccess;
	}
	
	public PerformanceResultCollector(RunInfo runInfo, CollectMethod collectResultable) {
		this.runInfo = runInfo;		
		this.collectResultable = collectResultable;
	}
 
	public void record(ExecuteInfo executeInfo, PerformanceResult performanceResult) {
		threadPool.submit(new Runnable() {
			
			@Override
			public void run() {
				try{
					collectResultable.collect(runInfo, executeInfo, performanceResult);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

}
