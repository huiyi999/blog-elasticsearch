# Blog Full-text Search System

![image](https://github.com/huiyi999/blog-elasticsearch/blob/master/image/System%20demo.png)

> How to run:
>
> 1. Start the mysql+elk docker services: https://github.com/huiyi999/docker-mysql-elk
> 2. Start  **BlogEsApplication**
> 3. Open  http://localhost:8081/



## Technical Stack:

> 1. Vue(front-end)
> 2. SpringBoot(back-end)
> 3. MySQL(DB)
> 4. ElasticSearch + Logstash + Kibana (Search and Synchronize DB)

![image](https://github.com/huiyi999/blog-elasticsearch/blob/master/image/Project%20Structure.pdf)




## Dependencies

> ElasticSearch 7.17.1
>
> Logstash 7.17.1
>
> Kibana 7.17.1
>
> SpringBoot 2.7.0

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
        </dependency>
```

### Elasticsearch configuration

> 2 methods to write Elasticsearch configuration
>
> 1. yml file
>
> 2. Java Config
     >
     >    9300 port is used as transport port and 9200 port is known as http port.
>
> 9200：http request, for rest client
> 9300：tcp request，for component connection



## Project structure

> 1. entity
> 2. repository <==> dao
> 3. service
> 4. controller

![Screen Shot 2022-06-21 at 8.12.23 PM](/Users/chy/Library/Application Support/typora-user-images/Screen Shot 2022-06-21 at 8.12.23 PM.png)