package core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 15:31 2018/9/6
 */
public class ThreadPool {

    private static ThreadPool threadPool;

    private ThreadPoolExecutor executor;

    private ThreadPool(){}

    public static ThreadPool getThreadPool(){
        if (threadPool == null){
            threadPool = new ThreadPool();
            threadPool.init();
        }
        return threadPool;
    }

    private void init(){
        executor = new ThreadPoolExecutor(20, 50, 200, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(50));

    }

    public void execute(Runnable task){
        executor.execute(task);
    }

    public void shutdown(){
        executor.shutdown();
    }
}
