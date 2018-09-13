package engine;

import org.mybatis.generator.config.Context;

import java.util.ArrayList;
import java.util.List;

import bean.NameUtil;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 15:39 2018/9/7
 */
public class Config {

    public static final String TEMPLATE_PATH = System.getProperty("user.dir")+"/src/main/java/template";
    //public static final String TEMPLATE_PATH = System.getProperty("user.dir")+"/classes/template";
    private static Config config;

    private String localPath = System.getProperty("user.dir");
    private String jdbcUrl = "";
    private String jdbcUserName = "";
    private String jdbcPassword = "";
    private List<NameUtil> nameBeans;

    public final String MAPPER_TEMPLATE_PATH = "dao.MapperCommon";
    public final String MAPPER_PATH = "mapper";
    public final String CONTROLLER_PATH = "controller";
    public final String SERVICE_PATH = "service";
    public final String DOMAIN_PATH = "domain";
    public final String SERVICE_IMPL_PATH = "service/serviceImpl";
    public final String DAO_PATH = "dao";
    public final String JDBC_DIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private String author = "";
    private String projectName = "";

    private Config(){}
    public static Config getConfig(){
        if (config == null){
            config = new Config();
        }
        return config;
    }

    public void setNameBeans(List<String> tableNames){
        nameBeans = new ArrayList<NameUtil>();
        for (String n : tableNames){
            nameBeans.add(new NameUtil(n));
        }
    }

    /**
     * 设置author
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 设置项目地址
     * @param projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 设置输出地址
     * @param localPath
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    /**
     * 设置数据库地址
     * @param jdbcUrl
     */
    public  void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    /**
     * 设置数据库账号名称
     * @param jdbcUserName
     */
    public  void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    /**
     * 设置数据库密码
     * @param jdbcPassword
     */
    public  void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }


    public static String getTemplatePath() {
        return TEMPLATE_PATH;
    }

    public String getLocalPath() {
        return localPath;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public List<NameUtil> getNameBeans() {
        return nameBeans;
    }

    public String getAuthor() {
        return author;
    }

    public String getProjectName() {
        return projectName;
    }


}
