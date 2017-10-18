# Data_Server
数据 管理系统-综合管理系统
服务器端：使用Tomcat， Eclipse
数据库为：MySql数据库
使用Maven进行版本控制

2017年7月3日21:53  
测试，更换eclipse。

注意：使用该项目时候,首先按照普通方式从GitHub端git到源码。
如果是maven项目，先导入项目选择maven project，
然后，将项目转化为web项目，添加Dynamic web...（Data_server的web.xml生成在WebContent中，注意查看源码）
如果不是maven项目，则，git到源码直接转化web项目。

1、登录界面
![image](https://github.com/fengzy1990/pics/blob/master/3333.png)
2、主界面
![image](https://github.com/fengzy1990/pics/blob/master/dataserver-main.png)

2017年10月18日17:10:38
注意：在更改仓库地址时候，最好先从eclipse导出项目，然后删除项目中有关.git文件及文件夹。
      新的eclipse使用，首先创建一个SSH key加入到GitHub中的总的key中。
   可以保留.gitignore文件，来保持非必要的文件收到版本控制。同时避免了受到不同环境的影响。
   
2017年10月18日21:24:52
1、将项目更改成maven进行项目管理。
2、更改数据库为data_server,规范化管理数据库命名.

