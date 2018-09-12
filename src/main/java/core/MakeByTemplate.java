package core;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import bean.TemplateBean;


/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 11:10 2018/9/7
 */
public class MakeByTemplate implements ThreadInterface{

    private Configuration cfg ;
    private TemplateBean bean ;

    public MakeByTemplate(TemplateBean bean, Configuration cfg){
        this.bean = bean;
        this.cfg = cfg;
    }

    public void invoke() {
        File file = new File(bean.getFilePath());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            cfg.getTemplate(bean.getTemplateName()).process(bean.getMap(),
                    new FileWriter(file));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
