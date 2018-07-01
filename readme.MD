#solr7.3学习总结

##背景
全文检索服务器使用lucene,领导说换成solr。

lucene为最流行的全文检索框架。（说白了就一个持久化框架）


solr将其封装，成为一个功能强大的应用服务器。大部分功能基于配置，易学难精（官方PDF文档1200页）。

特性有：
- 优雅的配置管理界面（各种功能可插拔）
- 界面工具：文档操作，分词分析，查询测试，性能监控等
- 性能良好功能较为完善的restful接口
- 支持分布式与集群
- 身份验证与授权
- 多语言客户端API支持（js java python ruby）
- Solr近实时搜索（NRT）等

查了几天资料，唯一一个靠谱的[帮助文档](https://www.w3cschool.cn/solr_doc/solr_doc-e9lg2js0.html)。
solr二次开发/扩展,似乎国内研究的人不多，看了好几遍中英文文档，终于在另一个官方插件文档中，找到了扩展思路。
[DataImportHandler](https://wiki.apache.org/solr/DataImportHandler)


##需求
- 建立索引

    代码字段翻译，自动维护索引（update delete）
    
- 查询数据

    根据参数返回定制的文档内容（field），不返回空字段等
    
    
##解决方案

###微服务（springboot）

 - 根据数据库内容，翻译数据后，调用restful新增接口
 
 - spring定时器，获取数据库改变的内容，更新索引
 
 - 调用restful查询接口，数据处理后返回

 - 优缺点：

    只依赖接口，不用关心实现。开发人员不用深入了解solr lucene的原理。
    
    Solr 绝大部分特性被屏蔽掉了。需要多部署一个服务器，会增加运维测试成本。  

###插件扩展（handler）

 - DataImportHandler扩展翻译功能，扩展自动任务功能
 
 - SolrRequestHandler增加新restful接口，实现查询功能
 
 - 优缺点：

    Solr 特性得以保留，维护成本低。
    
    完全依赖solr的扩展插件，特别是索引建立依赖DataImportHandler。需要了解solr lucene的原理。
    
    
    
 ###微服务+插件扩展
 
- 按需扩展查询接口
- 索引 增删改 维护的逻辑，放在微服务中  
- 微服务只负责维护索引，不与外部客户端交互  
- 优缺点：
  
    按照项目需求定制使用Solr特性。又不会完全依赖solr插件接口。
      
    需要了解solr lucene的原理。需要多部署一个简单服务，稍微增加运维测试成本。  
    
 ###总结：
 
 写插件过程中，看了很多solr结构文档，看了很多源代码，学习了一些程序架构，java高级特性。
 
 solr webapp下面居然没有业务代码，业务代码都放到了环境变量指定的目录。solr core 加载 卸载功能强大。
 
 原来难的并不是写代码、使用设计模式，而是把代码写到什么位置上。
 
 
 
    


