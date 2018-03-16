package com.test.performance;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.test.performance.result.CollectMethod;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.stress.AbstractStress;
import com.test.performance.stress.StressWithThreadNumberControl;
import com.test.performance.stress.StressWithTpsControl;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class PerformanceTool {

	@Parameter(required = true, names = { "--test", "-t" },  description = "test case class, such as com.test.performance.demo.DemoTestCaseImpl")
	private String testCaseClass;

	@Parameter(required = true, names = { "--record", "-r" },  description = "record test result class, such as com.test.performance.demo.DemoCollectMethodImpl")
	private String collectResultClass;

	@Parameter(names = { "-thread" })
	private int threadNumber = 1;

	@Parameter(names = { "-duration"})
	private int durationInSeconds = 10;
	 
	@Parameter(names = { "-tps"})
	private int tps = -1;
	
	@DynamicParameter(names = "-d", description = "dynamic parameters")
	private Map<String, String> params = new HashMap<>();
	
	@Parameter(names = "--help", help = true)
    private boolean help = false;

	public static void main(String... argv) throws Exception {
		PerformanceTool performancePool = new PerformanceTool();
		JCommander build = JCommander.newBuilder().addObject(performancePool).build();
		build.setProgramName("performance test tool");
		build.parse(argv);
		if(performancePool.help){
			build.usage();
			return;
		}
		performancePool.run();
	}

	public void run() {
		printInfoAndPrepare();
  		
		AbstractTestCaseExecutor abstractExecutor = PerformanceUtil.getClassInstace(testCaseClass);
		CollectMethod classInstace = PerformanceUtil.getClassInstace(collectResultClass);
		PerformanceResultCollector resultCollector = new PerformanceResultCollector(classInstace);
 
		boolean isPrepareSuccess = prepareCondition(abstractExecutor);

		if (!isPrepareSuccess) {
			System.out.println("prepare failed. won't execute stress");
			return;
		}

		doStress(abstractExecutor, resultCollector);
	}

	private void printInfoAndPrepare() {
		System.out.println("####performance tool data####");
		System.out.println(this);
 
		for (Entry<String, String> entry : this.params.entrySet()) {
			System.setProperty(entry.getKey(), entry.getValue());
		}
	}

	private boolean prepareCondition(AbstractTestCaseExecutor abstractExecutor) {
		System.out.println("####prepare start####");
		boolean isPrepareSuccess = abstractExecutor.prepareEnvironment();
		System.out.println("####prepare complete####");
		return isPrepareSuccess;
	}

	private void doStress(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector) {
		System.out.println("####stress start####");
		AbstractStress abstractStress = getStress(abstractExecutor, resultCollector);
		abstractStress.stressWithProgreeReport();
		System.out.println("####stree complete####");
	}

	private AbstractStress getStress(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector) {
		int durationInMills = durationInSeconds * 1000;
		return tps != -1 ? new StressWithTpsControl(abstractExecutor, resultCollector, durationInMills, tps)
				: new StressWithThreadNumberControl(abstractExecutor, resultCollector, durationInMills, threadNumber);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceTool Data\n[\ntestCaseClass=");
		builder.append(testCaseClass);
		builder.append("\ncollectResultClass=");
		builder.append(collectResultClass);
		if(tps == -1){
			builder.append("\nthreadNumber=");
			builder.append(threadNumber);
		}
 		builder.append("\ndurationInSeconds=");
		builder.append(durationInSeconds);
		if(tps != -1){
			builder.append("\ntps=");
			builder.append(tps);
		}
 		builder.append("\nparams=");
		builder.append(params);
 		builder.append("\n]");
		return builder.toString();
	}
	
	

}