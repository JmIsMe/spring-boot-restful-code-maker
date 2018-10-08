# spring-boot-restful-code-maker

# 项目介绍
基于MyBatis Generator、freemarker为模板引擎、swing为图形界面交互框架，指在快速而且方便自动根据数据库中表名称和表中字段名生成基于Spring-Boot 的REST_FUL风格的controller、mapper、domain、service、serviceImpl代码。让web开发人员可以更专注于业务的开发，节省大量时间和精力。

# 使用介绍
1、克隆项目至本地，找到如图1的程序入口，运行。（P.S. 我是想打个包来着，但是总是不成功，下次有时间再试试）运行成功会有如下图2所示界面跳出。
![图片1](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/1.jpg)
![图片2](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/2.jpg)

2、输入数据库地址:（ip/port）、账号、密码，点击connect。会有如下图3所示，出现改数据库中表的列表
![图片3](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/3.jpg)

3、选择需要生成代码的表名（表名格式如下图所示，表内属性名称也是为该格式即xxxx_xxx_xxxx这样，生成的代码类名为大驼峰，对象名为小驼峰），输入项目名（e.g. com.xxx.xxx），作者名称，选择输出地址,点击makecode，会有日志打印（文件地址select键已可用）

![图片7](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/7.jpg)
![图片4](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/4.jpg)

4、生成代码如下图所示

![图片5](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/5.png)
![图片6](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/6.png)


P.S. 我集成了Swagger，因为觉得挺帅的。还有就是该工具只是生成代码，不生成项目!!!!!.

# 更新备注

1、自己在用spring boot写web应用时，觉得创建controller啊、mapper、domain啊之类这些工作太繁琐，比写业务还麻烦，数量一多了还容易眼花建错，参考github上的一些开源项目，自己也准备写一个根据数据库表名和表内容自动生成代码的工具，用GUI写界面。目前功能勉强算实现了，不过还还有很多东西没有做比如异常处理其他数据库兼容处理等等，有空的时候再写下。哈哈，反正也没人看没人用。

2、更新了操作界面，现在就可以使用了，不过暂时只支持mysql以后有时间在去适配别的数据库。

3、更新了选择文件输出地址的资源管理器、简单地做了非空校验、增加了清楚日志按键。
![图片8](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/8.jpg)

4、更新可以支持oracle（没测试）、sql server（没测试）；密码非明文显示。
