package core;


/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 15:34 2018/9/6
 */
public class MakeThread implements Runnable{

    private ThreadInterface thread;

    public MakeThread(ThreadInterface thread){
        this.thread = thread;
    }

    public void run() {
        thread.invoke();
        System.err.println("xxxxxxxxxxxx");
    }

}
