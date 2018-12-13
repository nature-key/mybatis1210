mybatis原理

1.获取sqlsessionFactory
      1.创建一个sqlsessionfactorybuild
      2.build(inputstream)
      3.xmlconfigbuilder ,创建解析器
      4.解析每一个标签保存在configuration中
      5.解析mapper.xml
          mapper.xml.解析的标签都放到了configurstion中，将正删改查封装一个mapperedstatment,代表一个增删改查
      6.返回一个configuration
      7.buid（configuration）
      8.new DefaultSqlsessionFactory
      9.DefaultSqlsessionFactory 保存了全局配置的configuration
      
       mapperStatements保持着一个id对应的mapperstatement
       mapperregister 保存着一个mapperPROXY 对应的 接口
      把所有的文件解析报讯在configuration返回包含的configuration 的 DefaultSqlsessionFactory 
2.获取sqlsession
      1.opensqlsession
      2。调用opensessionfactoryformtdatasource();
      3.获取一些信息，常见事物tx
      4.new Execuror()
      5.根据Execurtor配置，创建BatchExecutor ReuseExecutor SimpleExecutor
      6.如果创建二级缓存，创建CachingExecutor
      7. Executor executor = (Executor)this.interceptorChain.pluginAll(executor);，返回executor
      8.创建DefaultSqlsession,包含configuration和Executor
      9.返回DefaultSqlsession
      返回sqlsesion的实现类DefaultSqlsession，他里面包含configuration和Executor
    

3.获取mapper 代理mapper

   1.getmapper()
   2.this.configuration.getMapper(type, this
   3.configcontion的mapperRegistry。getmapper
   4.创建对应的MapperProxyFactory
   5.MapperProxyFactory newInstance一个mapperProxy
   6.创建mapperProxy的一个代理对象
   7.发布会代理对象

4.执行增删改查

   1.代理对象
   2.获取defaultSQLsession
   3.再到Executor
   4.staementHandler
   5. 里面的paramerHander :处理sql预编译设置参数
      resultHander:处理结果集
   6.里面都是基于TypeHander
   底层实现stament preparestatement   

总结
  1.根据配置文件，初始化congifurtion
  2,创建一个defaultsqlsession对象
     包含了configuration 以及Executor (根据全局变量对应Executor)
  3.Defaultsqlsession。getmapper获取maper接口对应mapperfactory
  4.mapperFactory里面包含Defaultsqlsesion
  5.执行增删改查
   1. 调用Defaultsqlsession的增删改查
   2.创建一个StatementHander独享
    同时创建paramterHandler和ResultSEThandler
    3.调用statementHandeer,预编译参数以及设置参数
    paramentHander给sql设置参数
    4.调用stamentHander 的增删改查方法
    5，resultSEThander封装结果
  每一个对想都有一个插件  
  实现插件
  
  1.实现接口Interceptor
  2.注解@Intercepts({
              @Signature(type = StatementHandler.class,method ="parameterize" ,args = Statement.class )
      })
      
  3.全局变量配置
  <plugins>
          <plugin interceptor="com.jiepi.dao.MyFirstPlugin">
              <property name="username" value="root"></property>
              <property name="password" value="123456"></property>
          </plugin>
      </plugins>    