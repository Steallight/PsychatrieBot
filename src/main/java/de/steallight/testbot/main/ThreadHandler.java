package de.steallight.testbot.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadHandler {
    private static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void startExecute(final Runnable runnable) {
        ThreadHandler.getExecutor().execute(runnable);
    }

    public static void removeExecute(final Runnable runnable) {
        ThreadHandler.getExecutor().remove(runnable);
    }


    public static ThreadPoolExecutor getExecutor() {
        return ThreadHandler.executor;
    }
}
