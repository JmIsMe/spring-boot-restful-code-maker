package engine;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;

import java.util.ArrayList;
import java.util.List;

import bean.NameUtil;
import bean.TemplateBean;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 14:59 2018/9/5
 */
public class ConfigEngine{

    private static ConfigEngine configEngine;
    static Config config = Config.getConfig();
    private ConfigEngine(){}

    public static ConfigEngine getConfigEngine(){
        if (configEngine == null){
            configEngine = new ConfigEngine();
        }
        return configEngine;
    }


    /**
     * 设置mapper模板
     * @return
     */
    private PluginConfiguration getPluginConfiguration(){
        PluginConfiguration ret = new PluginConfiguration();
        ret.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        ret.addProperty("mappers", config.MAPPER_TEMPLATE_PATH);
        return ret;
    }



    /**
     * 设置生成的model地址，我这边是叫domain
     * @return
     */
    private JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration(){
        JavaModelGeneratorConfiguration ret = new JavaModelGeneratorConfiguration();
        ret.setTargetProject(config.getLocalPath());
        ret.setTargetPackage(config.getProjectName()+"."+config.DOMAIN_PATH);
        return ret;
    }

    /**
     * 设置生成的Mapper地址
     * @return
     */
    private SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration(){
        SqlMapGeneratorConfiguration ret = new SqlMapGeneratorConfiguration();
        ret.setTargetProject(config.getLocalPath());
        ret.setTargetPackage(config.getProjectName()+"."+config.MAPPER_PATH);
        return ret;
    }

    /**
     * 设置生成的DAO地址
     * @return
     */
    private JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration(){
        JavaClientGeneratorConfiguration ret = new JavaClientGeneratorConfiguration();
        ret.setTargetProject(config.getLocalPath());
        ret.setTargetPackage(config.getProjectName()+"."+config.DAO_PATH);
        ret.setConfigurationType("XMLMAPPER");
        return ret;
    }

    /**
     * 设置数据库连接配置
     * @return
     */
    private JDBCConnectionConfiguration getJdbcConnectionConfiguration(){
        JDBCConnectionConfiguration ret = new JDBCConnectionConfiguration();
        ret.setConnectionURL(config.getJdbcUrl());
        ret.setUserId(config.getJdbcUserName());
        ret.setPassword(config.getJdbcPassword());
        ret.setDriverClass(config.getDriver());
        return ret;
    }

    /**
     * 直接设置获取上下文
     * @return
     */
    public List<Context> getContexts(){
        List<Context> ret = new ArrayList<Context>();

        for (NameUtil tableName : config.getNameBeans()) {
            Context context = new Context(ModelType.FLAT);
            context.setId("CodeMaker");
            context.setTargetRuntime("MyBatis3Simple");
            context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
            context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
            context.setJavaModelGeneratorConfiguration(getJavaModelGeneratorConfiguration());
            context.setSqlMapGeneratorConfiguration(getSqlMapGeneratorConfiguration());
            context.setJavaClientGeneratorConfiguration(getJavaClientGeneratorConfiguration());
            context.setJdbcConnectionConfiguration(getJdbcConnectionConfiguration());
            context.addPluginConfiguration(getPluginConfiguration());

            TableConfiguration tableConfiguration = new TableConfiguration(context);
            tableConfiguration.setTableName(tableName.getTableName());
            tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
            context.addTableConfiguration(tableConfiguration);

            ret.add(context);
        }
        return ret;
    }

    public List<TemplateBean> getTemplateBeans(){
        return TemplateFactory.getTemplate();
    }


}
