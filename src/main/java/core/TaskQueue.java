package core;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 15:37 2018/9/6
 */
public class TaskQueue{


    private static TaskQueue taskQueue;

    private static Queue<MakeThread> queue = new LinkedList<MakeThread>();

    private TaskQueue(){
    }

    public static TaskQueue getTaskQueue(){
        if (taskQueue == null){
            taskQueue = new TaskQueue();
        }
        return taskQueue;
    }

    public void addTask(MakeThread task){
        queue.add(task);
    }

    public void addAllTask(List tasks){
        queue.addAll(tasks);
    }


    public Boolean isEmpty(){
        return queue.peek() == null?true:false ;
    }

    public MakeThread poll(){
        return queue.poll();
    }
}
