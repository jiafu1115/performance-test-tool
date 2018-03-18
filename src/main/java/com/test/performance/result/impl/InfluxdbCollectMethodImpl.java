package com.test.performance.result.impl;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import com.test.performance.common.RunInfo;
import com.test.performance.result.CollectMethod;
import com.test.performance.result.PerformanceResult;

public class InfluxdbCollectMethodImpl implements CollectMethod {
	
	private InfluxDB influxDB;
	
	public InfluxdbCollectMethodImpl() {
		String url = System.getProperty("influxdbUrl");
		String userName = System.getProperty("influxdbUsername");
		String password = System.getProperty("influxdbPassword");
		this.influxDB = InfluxDBFactory.connect(url, userName, password);
		this.influxDB.disableBatch();
	}

	@Override
	public void collect(RunInfo runInfo, PerformanceResult result) {
		if(!result.isSuccess()){
			System.out.println("fail request: " + result);
		}
		
		Point point = Point.measurement(runInfo.getTestName()).
							time(result.getStartTime(), TimeUnit.MILLISECONDS).
							tag("runId", runInfo.getRunId()).
							addField("trackingId", result.getTrackingId()).
							addField("isSuccess", result.isSuccess()).
						    addField("duration", result.getConsumeTimeInMillis()).
							addField("message", result.getMessage()).
							build();
		influxDB.write(point);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean prepareEnvironment(RunInfo runInfo) {
		String program = runInfo.getProgram();
		try{
			if(this.influxDB.databaseExists(program)){
				return true;
			}
			
			try{
				this.influxDB.createDatabase(runInfo.getProgram());
				return true;
			}catch(Exception e){
				return false;
			}
		}finally{
			this.influxDB.setDatabase(program);
		}
 	}

}
