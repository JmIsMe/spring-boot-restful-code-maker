package core;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import org.mybatis.generator.config.Context;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


import engine.Config;
import engine.ConfigEngine;
import bean.TemplateBean;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 10:53 2018/9/7
 */
public class ThreadFactory {

    private static Configuration cfg ;
    private TaskQueue queue = TaskQueue.getTaskQueue();
    private static ThreadFactory threadFactory;
    private ThreadFactory(){
    }
    public static ThreadFactory getThreadFactory(){
        if (threadFactory == null){
            threadFactory = new ThreadFactory();
        }
        return threadFactory;
    }
    static {
        cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        try {
            cfg.setDirectoryForTemplateLoading(new File(Config.TEMPLATE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
    }

    public void creatTask(List<Context> contexts, List<TemplateBean> templateBeans){
        List<MakeThread> ret = new LinkedList<MakeThread>();
        for (Context c : contexts){
            ThreadInterface task = new MakeByGen(c);
            MakeThread thread = new MakeThread(task);
            ret.add(thread);
        }
        for (TemplateBean b : templateBeans){
            ThreadInterface task = new MakeByTemplate(b,cfg);
            MakeThread thread = new MakeThread(task);
            ret.add(thread);
        }
        queue.addAllTask(ret);
    }

}
