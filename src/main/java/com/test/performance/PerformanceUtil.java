package com.test.performance;

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
}
