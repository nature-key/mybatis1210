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
3.获取mapper 代理mapper
4.执行增删改查


