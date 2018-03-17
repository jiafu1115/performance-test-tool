package com.test.performance;

import java.util.Date;
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

	@Parameter(names = { "--record", "-r" },  description = "record test result class, such as com.test.performance.result.DefaultCollectMethodImpl")
	private String collectResultClass = "com.test.performance.result.DefaultCollectMethodImpl";

	@Parameter(names = { "-thread" }, description = "")
	private int threadNumber = 1;

	@Parameter(names = { "-duration"}, description = "keep how much time in second for test")
	private int durationInSeconds = 10;
	 
	@Parameter(names = { "-tps"}, description = "if tps is not set, not limit tps. only loop to execute")
	private int tps = -1;
	
	@DynamicParameter(names = "-D", description = "dynamic parameters")
	private Map<String, String> params = new HashMap<>();
	
	@Parameter(names = { "-runid" },  description = "run id for this test")
	private String runid = new Date().toString();
	
	@Parameter(names = "-help", help = true)
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
		return tps != -1 ? new StressWithTpsControl(abstractExecutor, resultCollector, this.runid, durationInMills, this.threadNumber, this.tps)
				: new StressWithThreadNumberControl(abstractExecutor, resultCollector, this.runid, durationInMills, this.threadNumber);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceTool Data\n[\ntestCaseClass=");
		builder.append(testCaseClass);
		builder.append("\ncollectResultClass=");
		builder.append(collectResultClass);
 		builder.append("\nrunId=");
		builder.append(runid);
 		builder.append("\ndurationInSeconds=");
		builder.append(durationInSeconds);
		builder.append("\nthreadNumber=");
		builder.append(threadNumber);
		builder.append("\ntps=");
		builder.append(tps == -1 ? "unlimited": tps);
 		builder.append("\nparams=");
		builder.append(params);
 		builder.append("\n]");
		return builder.toString();
	}
	
}