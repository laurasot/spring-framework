package com.laurasoto.springrest;

import com.laurasoto.springrest.Autowire.AreaCalculatorService;
import com.laurasoto.springrest.Profiles.EnvironmentService;
import com.laurasoto.springrest.Qualifiers.Animal;
import com.laurasoto.springrest.Qualifiers.Perro;
import com.laurasoto.springrest.aop.TargetObject;
import com.laurasoto.springrest.lifeCycle.ExplicitBean;
import com.laurasoto.springrest.lifeCycle.LifeCycleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@SpringBootApplication
public class SpringRestApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringRestApplication.class);
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ExplicitBean getBean(){
        return new ExplicitBean();
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

        Animal animal = context.getBean("pajaro",Animal.class);
        EnvironmentService environmentService = context.getBean(EnvironmentService.class);
        AreaCalculatorService areaCalculatorService = context.getBean(AreaCalculatorService.class);
        ExpressionParser parser= new SpelExpressionParser();
        Expression expression= parser.parseExpression("1 + 20");
        LifeCycleBean lifeCycleBean = context.getBean(LifeCycleBean.class);
        TargetObject targetObject = context.getBean(TargetObject.class);
        targetObject.hello("hola po, te acordai de mi");

        log.info("Nombre del animal {}", animal.getNombre(), animal.getEdad());
        log.info("Active Environment {}", environmentService.getEnvironment());
        log.info("Result {}", expression.getValue());
        log.info("el tipo de objeto que devuelve el SpringApplication.run es:{}", context.getClass());

    }

}
