package com.test.performance.stress;

import com.test.performance.common.StressInfo;
import com.test.performance.progress.ShowProgressable;
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
	
	public AbstractStress getStress(Class<AbstractTestCaseExecutor> abstractTestCaseExecutor, PerformanceResultCollector resultCollector, ShowProgressable showProgressable, StressInfo stressInfo) {
		int durationInMills = stressInfo.getDurationInSeconds() * 1000;
		int threadNumber = stressInfo.getThreadNumber();
		int tps = stressInfo.getTps();
		
		if(tps != -1 && threadNumber == -1){
			return new StressWithTpsControl(abstractTestCaseExecutor, resultCollector, showProgressable, durationInMills, tps);
		}
		
		if(tps != -1 && threadNumber != -1){
			return new StressWithTpsAndThreadNumberControl(abstractTestCaseExecutor, resultCollector, showProgressable, durationInMills, threadNumber, tps);
		}
		
		if(tps == -1 && threadNumber != -1){
			return new StressWithThreadNumberControl(abstractTestCaseExecutor, resultCollector, showProgressable, durationInMills, threadNumber);
		}
		
		return new StressWithTpsAndThreadNumberControl(abstractTestCaseExecutor, resultCollector, showProgressable, durationInMills, 1, 1);
	}

}
