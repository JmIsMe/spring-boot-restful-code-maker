package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import engine.ConfigEngine;
import bean.TemplateBean;

import org.mybatis.generator.config.Context;

import java.util.List;
/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 13:50 2018/9/6
 */
public class MakeCore {

    private static final Logger log = LoggerFactory.getLogger(MakeCore.class);

    private static MakeCore makeCore;

    private ConfigEngine configEngine = ConfigEngine.getConfigEngine();
    private TaskQueue queue = TaskQueue.getTaskQueue();
    private ThreadPool pool = ThreadPool.getThreadPool();
    private ThreadFactory threadFactory = ThreadFactory.getThreadFactory();

    private MakeCore(){}

    public static MakeCore getMakeCore(){
        if (makeCore == null){
            makeCore = new MakeCore();
        }
        return makeCore;
    }

    public void makeCode(){
        List<Context> contexts = configEngine.getContexts();
        List<TemplateBean> templateBeans = configEngine.getTemplateBeans();
        threadFactory.creatTask(contexts, templateBeans);
        while(!queue.isEmpty()) {
            pool.execute(queue.poll());
        }
    }

}
