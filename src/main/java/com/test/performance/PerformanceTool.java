package com.test.performance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.test.performance.progress.ShowProgressImpl;
import com.test.performance.progress.ShowProgressable;
import com.test.performance.result.CollectMethod;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.stress.AbstractStress;
import com.test.performance.stress.StressFactory;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class PerformanceTool {

	@Parameter(required = true, names = { "--test", "-t" },  description = "test case class, such as com.test.performance.demo.DemoTestCaseImpl")
	private String testCaseClass;

	@Parameter(names = { "--record", "-r" },  description = "record test result class, such as com.test.performance.result.DefaultCollectMethodImpl")
	private String collectResultClass = "com.test.performance.result.DefaultCollectMethodImpl";
	
	@Parameter(names = { "-program" })
	private String program;
	
	@Parameter(names = { "-runid" },  description = "run id for this test, default is date")
	private String runId = new Date().toString();
	
	@Parameter(names = { "-duration"}, description = "keep how much time in second for test")
	private int durationInSeconds = 10;
	
	@Parameter(names = { "-reportinterval"}, description = "interval in seconds to report progress")
	private int reportProgressIntervalInSeconds = 5;

	@Parameter(names = { "-thread" })
	private int threadNumber = -1;
	 
	@Parameter(names = { "-tps"})
	private int tps = -1;
	
	@DynamicParameter(names = "-D", description = "dynamic parameters")
	private Map<String, String> params = new HashMap<>();
	
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
  		
		AbstractTestCaseExecutor testCaseExecutor = PerformanceUtil.getClassInstace(testCaseClass);
		CollectMethod collectMethod = PerformanceUtil.getClassInstace(collectResultClass);
		PerformanceResultCollector resultCollector = new PerformanceResultCollector(this.program, this.runId, collectMethod);
 
		boolean isPrepareSuccess = prepareCondition(testCaseExecutor);

		if (!isPrepareSuccess) {
			System.out.println("prepare failed. won't execute stress");
			return;
		}
		
		ShowProgressImpl showProgressImpl = new ShowProgressImpl(reportProgressIntervalInSeconds);
		doStress(testCaseExecutor, resultCollector, showProgressImpl);
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

	private void doStress(AbstractTestCaseExecutor abstractExecutor, PerformanceResultCollector resultCollector, ShowProgressable showProgressable) {
		System.out.println("####stress start####");
		AbstractStress stress = StressFactory.getInstance().getStress(abstractExecutor, resultCollector, showProgressable, program, runId, durationInSeconds, threadNumber, tps);
		System.out.println("####" + stress + "####");
		stress.stressWithProgreeReport();
		System.out.println("####strees complete####");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceTool Data\n[\nprogram=");
		builder.append(program);
 		builder.append("\nrunId=");
		builder.append(runId);
 		builder.append("\ndurationInSeconds=");
		builder.append(durationInSeconds);
		builder.append("\nthreadNumber=");
		builder.append(threadNumber);
		builder.append("\ntps=");
		builder.append(tps);
 		builder.append("\nparams=");
		builder.append(params);
		builder.append("\nreportProgressIntervalInSeconds=");
		builder.append(reportProgressIntervalInSeconds);
		builder.append("\nntestCaseClass=");
		builder.append(testCaseClass);
		builder.append("\ncollectResultClass=");
		builder.append(collectResultClass);
 		builder.append("\n]");
		return builder.toString();
	}
	
}