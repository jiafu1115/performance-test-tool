package com.test.performance.progress;

public interface ShowProgressable {

	void start();
	
	void stop();

	void record(boolean isSuccess, long duration);
 
}