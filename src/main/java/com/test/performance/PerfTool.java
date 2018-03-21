package com.test.performance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.test.performance.common.PerformanceUtil;
import com.test.performance.common.RunInfo;
import com.test.performance.common.StressInfo;
import com.test.performance.progress.ShowProgressImpl;
import com.test.performance.progress.ShowProgressable;
import com.test.performance.result.PerformanceResultCollector;
import com.test.performance.stress.AbstractStress;
import com.test.performance.stress.StressFactory;
import com.test.performance.testcase.AbstractTestCaseExecutor;

public class PerfTool {

	@Parameter(required = true, names = { "--test", "-t" },  description = "test case class, such as com.test.performance.demo.DemoTestCaseImpl")
	private String testCaseClass;

	@Parameter(names = { "--record", "-r" },  description = "record test result class, such as com.test.performance.result.impl.DefaultCollectMethodImpl")
	private String collectResultClass = "com.test.performance.result.impl.DefaultCollectMethodImpl";
	
	@Parameter(names = { "-program" })
	private String program;
	
	@Parameter(names = { "-testname" })
	private String testName;
	
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
		PerfTool performancePool = new PerfTool();
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
  		
		Class<AbstractTestCaseExecutor> testCaseExecutor = PerformanceUtil.getClass(testCaseClass);
		PerformanceResultCollector resultCollector = new PerformanceResultCollector(new RunInfo(program, testName, runId), PerformanceUtil.getClassInstace(collectResultClass));
		ShowProgressable showProgressImpl = new ShowProgressImpl(reportProgressIntervalInSeconds);
		
		doStress(testCaseExecutor, resultCollector, showProgressImpl);
	}

	private void printInfoAndPrepare() {
		System.out.println("####################################################################################");
		System.out.println(this);
		System.out.println("####################################################################################");
 
		for (Entry<String, String> entry : this.params.entrySet()) {
			System.setProperty(entry.getKey(), entry.getValue());
		}
	}
 
	private void doStress(Class<AbstractTestCaseExecutor> abstractExecutor, PerformanceResultCollector resultCollector, ShowProgressable showProgressable) {
		AbstractStress stress = StressFactory.getInstance().getStress(abstractExecutor, resultCollector, showProgressable, new StressInfo(durationInSeconds, threadNumber, tps));
		System.out.println("[Info] stress method: " + stress);
		stress.stressWithProgreeReport();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerformanceTool Data\n[\nprogram=");
		builder.append(program);
 		builder.append("\ntestName=");
		builder.append(testName);
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
		builder.append("\ntestCaseClass=");
		builder.append(testCaseClass);
		builder.append("\ncollectResultClass=");
		builder.append(collectResultClass);
 		builder.append("\n]");
		return builder.toString();
	}
	
}