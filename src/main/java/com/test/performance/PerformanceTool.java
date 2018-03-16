package com.test.performance;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.test.performance.execute.AbstractExecutor;
import com.test.performance.result.ResultCollector;
import com.test.performance.stress.AbstractStress;
import com.test.performance.stress.StressWithThreadNumberControl;
import com.test.performance.stress.StressWithTpsControl;

public class PerformanceTool {

	@Parameter(required = true, names = { "--implement", "-i" })
	private String implementClass;

	@Parameter(required = true, names = { "--collect", "-c" })
	private String collectClass;

	@Parameter(names = { "--threadNumber", "-t" })
	private int threadNumber = 1;

	@Parameter(names = { "--duration", "-d" })
	private int durationInSeconds = 10;

	@Parameter(names = { "--tps", "-r" })
	private int tps = -1;

	public static void main(String... argv) throws Exception {
		PerformanceTool performancePool = new PerformanceTool();
		JCommander.newBuilder().addObject(performancePool).build().parse(argv);
		performancePool.run();
	}

	public void run() {
		AbstractExecutor abstractExecutor = PerformanceUtil.getClassInstace(implementClass);
		ResultCollector resultCollector = PerformanceUtil.getClassInstace(collectClass);

		boolean isPrepareSuccess = prepareCondition(abstractExecutor);

		if (!isPrepareSuccess) {
			System.out.println("prepare failed. won't execute stress");
			return;
		}

		doStress(abstractExecutor, resultCollector);
	}

	private boolean prepareCondition(AbstractExecutor abstractExecutor) {
		System.out.println("####prepare start####");
		boolean isPrepareSuccess = abstractExecutor.prepare();
		System.out.println("####prepare complete####");
		return isPrepareSuccess;
	}

	private void doStress(AbstractExecutor abstractExecutor, ResultCollector resultCollector) {
		System.out.println("####stress start####");
		AbstractStress abstractStress = getStress(abstractExecutor, resultCollector);
		abstractStress.stressWithProgreeReport();
		System.out.println("####stree complete####");
	}

	private AbstractStress getStress(AbstractExecutor abstractExecutor, ResultCollector resultCollector) {
		int durationInMills = durationInSeconds * 1000;
		return tps != -1 ? new StressWithTpsControl(abstractExecutor, resultCollector, durationInMills, tps)
				: new StressWithThreadNumberControl(abstractExecutor, resultCollector, durationInMills, threadNumber);
	}

}