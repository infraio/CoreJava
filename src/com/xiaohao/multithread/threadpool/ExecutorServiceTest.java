package com.xiaohao.multithread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

  public static final int TIMEOUT = 1000;
  public static final int POOL_SIZE = 4;
  public static final int TASK_COUNT = 10;
  
  public static void main(String[] args) throws Exception {
    ExecutorService pool = Executors.newScheduledThreadPool(POOL_SIZE);
    List<SleepTask> tasks = new ArrayList<>();
    for (int i = 0; i < TASK_COUNT; i++) {
      tasks.add(new SleepTask(TIMEOUT / 2));
    }
    List<Future<Integer>> futures = pool.invokeAll(tasks, TIMEOUT, TimeUnit.MILLISECONDS);
    for (Future<Integer> future : futures) {
      try {
        Integer result = future.get();
        System.out.println("result : " + result);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    pool.shutdownNow();
  }
}

class SleepTask implements Callable<Integer> {

  private int sleepTime;
  
  public SleepTask(int sleepTime) {
    this.sleepTime = sleepTime;
  }
  
  @Override
  public Integer call() throws Exception {
    Thread.sleep(sleepTime);
    return sleepTime;
  }
}
