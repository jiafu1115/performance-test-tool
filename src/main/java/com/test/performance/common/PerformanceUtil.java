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
	public static <T> T getClass(String classStr) {
		try {
			return (T) Class.forName(classStr);
		} catch (ClassNotFoundException e) {
 			throw new PerformanceToolException(e.getMessage(), e);
		}
	}
    
	public static <T> T getClassInstace(String classStr) {
			Class<T> clazz = getClass(classStr);
			return getClassInstace(clazz);
	}
	
	public static <T> T getClassInstace(Class<T> classStr) {
		try {
			return classStr.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
 			throw new PerformanceToolException(e.getMessage(), e);
		}
	}
}
