package engine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import bean.NameUtil;
import bean.TemplateBean;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 15:32 2018/9/7
 */
class TemplateFactory {

    static Config config = Config.getConfig();
    private static String filePath;

    public static List<TemplateBean> getTemplate(){
        filePath =config.getLocalPath()+"/"+ projectName2FlieName(config.getProjectName())+"/";
        System.out.println(filePath);
        List<TemplateBean> l = new ArrayList<TemplateBean>();
        l.addAll(getCSTemplate());
        l.addAll(getOtherTemplate());
        return l;
    }

    private static String projectName2FlieName(String str){
        return str.replace(".","/");
    }


    private static List<TemplateBean> getCSTemplate(){

        List<TemplateBean> l = new ArrayList<TemplateBean>();

        for (NameUtil tableName : config.getNameBeans()) {
            TemplateBean cBean = new TemplateBean();

            Map<String, String> data = new HashMap<String, String>();
            data.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            data.put("author", config.getAuthor());
            data.put("projectPath", config.getProjectName());
            data.put("domainUpperName", tableName.getDomainName());
            data.put("domainLowerName", tableName.getLowerDomainName());

            cBean.setMap(data);
            cBean.setFilePath(filePath+config.CONTROLLER_PATH+"/"+tableName.getDomainName()+"Controller.java");
            cBean.setTemplateName("Controller.ftl");
            l.add(cBean);

            TemplateBean sBean = cBean.clone();
            sBean.setFilePath(filePath+config.SERVICE_PATH+"/"+tableName.getDomainName()+"Service.java");
            sBean.setTemplateName("Service.ftl");
            l.add(sBean);

            TemplateBean sImplBean = cBean.clone();
            sImplBean.setFilePath(filePath+config.SERVICE_IMPL_PATH+"/"+tableName.getDomainName()+"ServiceImpl.java");
            sImplBean.setTemplateName("ServiceImplTemplate.ftl");
            l.add(sImplBean);

        }
        return l;
    }


    private static List<TemplateBean> getOtherTemplate(){

        List<TemplateBean> l = new ArrayList<TemplateBean>();
        l.add(getTemplate(filePath+config.DOMAIN_PATH+"/Result.java","Result.ftl"));
        l.add(getTemplate(filePath+"SwaggerConfig.java","SwaggerConfig.ftl"));
        l.add(getTemplate(filePath+config.MAPPER_PATH+"/MapperCommon.java","MapperCommon.ftl"));
        l.add(getTemplate(filePath+config.SERVICE_PATH+"/ServiceCommon.java","ServiceCommon.ftl"));
        l.add(getTemplate(filePath+config.SERVICE_IMPL_PATH+"/ServiceCommonImpl.java","ServiceCommonImpl.ftl"));

        return l;
    }

    private static TemplateBean getTemplate(String filePath, String templateName){
        TemplateBean bean = new TemplateBean();
        Map<String, String> data = new HashMap<String, String>();
        data.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        data.put("author", config.getAuthor());
        data.put("projectPath", config.getProjectName());
        bean.setMap(data);
        bean.setFilePath(filePath);
        bean.setTemplateName(templateName);

        return bean;

    }
}
