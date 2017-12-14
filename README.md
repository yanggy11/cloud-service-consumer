# cloud-service-consumer
构建ribbon 负载均衡

#1.pom中加入ribbon依赖
```
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
        </dependency>
```
#2. 加入@LoadBalanced注解
  @Bean //定义负载均衡方式，这里使用自带的随机方式，也可以自定义负载均衡方式
  ```
    public IRule getRule() {
        return new RandomRule();
    }

    @Bean
    @LoadBalanced //加入注解，启用负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
```

#3.引用配置中心

首先将配置文件application.yml更名为bootstrap.yml，原因是当启动配置中心的时候，会默认从bootstrap.yml文件中读取配置中心的属性，如果找不到，则会默认从localhost:8888所表示的配置中心中读取配置文件，会出现如下的错误：
```
2017-12-12 17:25:59.406  INFO 6384 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at: http://localhost:8888
2017-12-12 17:26:00.555  WARN 6384 --- [           main] c.c.c.ConfigServicePropertySourceLocator : Could not locate PropertySource: I/O error on GET request for "http://localhost:8888/cloud-config/test": Connection refused: connect; nested exception is java.net.ConnectException: Connection refused: connect
2017-12-12 17:26:00.560  INFO 6384 --- [           main] com.yanggy.ServiceUser                   : No active profile set, falling back to default profiles: default
```

引入maven依赖
```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
```
application.yml中添加配置中心配置属性，
```
spring:
  cloud:
      config:
        name: cloud-config # 要读取的配置文件application属性
        profile: ${config.profile:dd} # default config profile
        username: xxxxxx #github账户
        password: xxxxxx
        discovery:
          enabled: true
          service-id: cloud-config
```

#从配置中心加载数据库信息

定义接收从配置中心获取到的数据库属性类

```
@Data
@Component
@ConfigurationProperties(prefix = DataSourceProperties.PREFIX, ignoreUnknownFields = false)
public class DataSourceProperties {
    public final static String PREFIX="jdbc";

    private String type;
    private String driver;
    private String url;
    private String username;
    private String password;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;

    public DataSourceProperties() {
        super();
    }
}
```

定义datasource bean
```
@Configuration
@MapperScan(DataSourceConfiguration.MAPPER_PACKAGE_PATH)
@EnableConfigurationProperties(DataSourceProperties.class)
@EnableTransactionManagement
public class DataSourceConfiguration {
    private final static String MAPPER_LOCATIONS = "sql-mapper/*.xml";
    private final static String ENTITY_PACKAGE_PATH = "com.yanggy.model";
    protected final static String MAPPER_PACKAGE_PATH = "com.yanggy.mapper";
    @Autowired
    private  DataSourceProperties dataSourceProperties;
    private DruidDataSource datasource = null;

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        datasource = new DruidDataSource();
        datasource.setUrl(dataSourceProperties.getUrl());
        datasource.setDbType(dataSourceProperties.getType());
        datasource.setDriverClassName(dataSourceProperties.getDriver());
        datasource.setUsername(dataSourceProperties.getUsername());
        datasource.setPassword(dataSourceProperties.getPassword());
        return datasource;
    }

    @PreDestroy
    public void close() {
        if(datasource != null){
            datasource.close();
        }
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATIONS));
        sqlSessionFactoryBean.setTypeAliasesPackage(ENTITY_PACKAGE_PATH);
        return sqlSessionFactoryBean.getObject();
    }
            
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
```
通过以上步骤，数据库配置完毕。

