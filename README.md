# cloud-service-consumer
构建ribbon 负载均衡

1.pom中加入ribbon依赖
```
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
        </dependency>
```
2. 加入@LoadBalanced注解
  @Bean //定义负载均衡方式，这里使用自带的随机方式，也可以自定义负载均衡方式
    public IRule getRule() {
        return new RandomRule();
    }

    @Bean
    @LoadBalanced //加入注解，启用负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
