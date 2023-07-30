package com.rest.apidemorest.config;

import com.github.javafaker.Faker;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableWebSecurity
public class FakerBeanConfig{
    @Bean
    public Faker getFaker(){
        return new Faker();
    }

    @Bean
    TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
    /*@Bean //administrador de cache, se especifica que tipo de cache va a soportar, en este caso el cache de users
    public CacheManager getCache(RedissonClient redissonClient){ // administrador de caches
        Map<String, CacheConfig> config = new HashMap<>();
        config.put("users", new CacheConfig());
        return new RedissonSpringCacheManager(redissonClient);
    }

    @Bean(destroyMethod = "shutdown") // cliente de redis
    public RedissonClient redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379"); // va a utilizar un solo server
        return Redisson.create();
    }*/
    /*
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("API DEMO REST")
                .version("1.0")
                .license("Apache 2.0")
                .contact(new Contact("@raidentrance","http//:www.apidemo.com", "contacto@gmail.com" ))
                .build();
    }*/


}
