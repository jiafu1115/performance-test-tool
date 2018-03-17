package com.test.performance.stress;

import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class StressFactory {
	
	private static final StressFactory INSTACE = new StressFactory();
	
	private StressFactory(){
		//no instance
	}
	
	public static StressFactory getInstance(){
		return INSTACE;
	}
	
	public AbstractStress getStress(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector, String runId, int durationInSeconds, int threadNumber, long tps) {
		int durationInMills = durationInSeconds * 1000;
		if(tps != -1 && threadNumber == -1){
			return new StressWithTpsControl(abstractExecutor, resultCollector, runId, durationInMills, tps);
		}
		
		if(tps != -1 && threadNumber != -1){
			return new StressWithTpsAndThreadNumberControl(abstractExecutor, resultCollector, runId, durationInMills, threadNumber, tps);
		}
		
		if(tps == -1 && threadNumber != -1){
			return new StressWithThreadNumberControl(abstractExecutor, resultCollector, runId, durationInMills, threadNumber);
		}
		
		return new StressWithTpsAndThreadNumberControl(abstractExecutor, resultCollector, runId, durationInMills, 1, 1);
	}

}
