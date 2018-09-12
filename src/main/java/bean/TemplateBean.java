package bean;

import java.util.Map;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 14:29 2018/9/7
 */
public class TemplateBean {

    private Map<String,String> map;
    private String filePath;
    private String templateName;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public TemplateBean clone(){
        TemplateBean ret = new TemplateBean();
        ret.setMap(map);
        ret.setTemplateName(templateName);
        ret.setFilePath(filePath);

        return ret;
    }

}
