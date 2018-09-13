# spring-boot-restful-code-maker

自己在用spring boot写web应用时，觉得创建controller啊、mapper、domain啊之类这些工作太繁琐，比写业务还麻烦，数量一多了还容易眼花建错，参考github上的
开源项目，自己也准备写一个根据数据库表名和表内容自动生成代码的工具，用GUI写界面。目前功能勉强算实现了，不过还没有界面，而且还有很多东西没有做比如异常
处理等等，有空的时候再写下。因为现在是最初的很粗糙的版本，所以暂时不写使用方法。哈哈。反正也没人看没人用。

---------------------------------------------------------------------------------------------------

更新了操作界面，现在就可以使用了，不过暂时只支持mysql以后有时间在去适配别的数据库。

# 使用介绍
1、克隆项目至本地，找到如图1的程序入口，运行。（P.S. 我是想打个包来着，但是总是不成功，下次有时间再试试）运行成功会有如下图2所示界面跳出。
![图片1](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/1.jpg)
![图片2](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/2.jpg)

2、输入数据库地址:（ip/port）、账号、密码，点击connect。会有如下图3所示，出现改数据库中表的列表
![图片3](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/3.jpg)

3、选择需要生成代码的表名（表名格式如下图所示，生成的代码类名为大驼峰，对象名为小驼峰），输入项目名（e.g. com.xxx.xxx），作者名称，输出地址,点击makecode，会有日志打印（控制台我重定位到界面中了，select键暂时没用，嘿嘿）
![图片4](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/4.jpg)

4、生成代码如下图所示
![图片5](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/5.png)
![图片6](https://github.com/JmIsMe/spring-boot-restful-code-maker/blob/master/img/6.png)


P.S. 我集成了Swagger，因为觉得挺帅的。该工具只是生成代码
