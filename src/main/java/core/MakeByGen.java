package core;


import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 11:10 2018/9/7
 */
public class MakeByGen implements ThreadInterface {

    private Context context;

    public MakeByGen(Context context){
        this.context = context;
    }

    public void invoke() {
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();
            MyBatisGenerator generator = new MyBatisGenerator(config, new DefaultShellCallback(true), new ArrayList<String>());
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }
    }
}
