import java.util.ArrayList;

import core.MakeCore;
import engine.Config;
import java.util.List;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 14:57 2018/9/5
 */
public class Main {

    public static void main(String[] args){
        MakeCore core = MakeCore.getMakeCore();
        Config config = Config.getConfig();

        config.setAuthor("作者");
        List<String> a = new ArrayList<String>();
        a.add("base_info");
        a.add("client_info");
        config.setNameBeans(a);
        config.setJdbcPassword("123#789");
        config.setJdbcUrl("jdbc:mysql://192.168.1.210:3306/fanjiamicedb?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true");
        config.setLocalPath("D:\\test\\sss");
        config.setProjectName("com.xxx.xxx");
        config.setJdbcUserName("root");


        core.makeCode();
    }
}
