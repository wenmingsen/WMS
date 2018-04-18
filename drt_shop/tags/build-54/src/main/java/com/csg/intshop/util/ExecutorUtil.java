package com.csg.intshop.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description: 线程执行工具类
 * Company: Syni
 * @version 1.0
 * @author 杨彬俊
 * @since 2017-11-01
 */
public class ExecutorUtil {

    public static Map<String, Future> futureMap = new HashMap<String, Future>();
    private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    /**
     * 线程执行方法
     * @param jobId
     * @param runnable
     * @param timeInterval 执行间隔，单位：秒
     */
    public static synchronized void scheduleRunnable(String jobId, Runnable runnable, int timeInterval) {
        if(service != null) {
            Future future = service.schedule(runnable, timeInterval, TimeUnit.SECONDS);
            futureMap.put(jobId, future);
        }
    }

    public static synchronized void cancelRunnable(String jobId) {
        if(futureMap != null) {
            Future future = futureMap.get(jobId);
            if(future != null) {
                future.cancel(true);
            }
            futureMap.remove(jobId);
        }
    }

    public static synchronized void destroy() {
        if(service != null) {
            if(futureMap != null && futureMap.size() > 0) {
                for (Future future : futureMap.values()) {
                    if(!future.isDone()) {
                        future.cancel(true);
                    }
                }
                futureMap.clear();
                futureMap = null;
            }
            if(!service.isShutdown()) {
                service.shutdownNow();
            }
            service = null;
        }
    }
}
