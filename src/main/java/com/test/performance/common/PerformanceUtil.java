package com.test.performance.common;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class PerformanceUtil {

    private PerformanceUtil() {
        // no instance;
    }
    
    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
	@SuppressWarnings("unchecked")
	public static <T> T getClassInstace(String clazz) {
		try {
			return (T) Class.forName(clazz).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
 			throw new PerformanceToolException(e.getMessage(), e);
		}
	}
}
