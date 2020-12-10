package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import top.daoyang.spring.dependency.injection.annotation.MyAutowired;
import top.daoyang.spring.dependency.injection.annotation.NewInject;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * 自定义类似{@link org.springframework.beans.factory.annotation.Autowired} 功能的注解
 */
@Configuration
class MyAnnotationTest {

    private AnnotationConfigApplicationContext applicationContext;
    private ComponentClass componentClass;

    @BeforeEach
    public void initApplicationContext() {
        applicationContext = new AnnotationConfigApplicationContext(ComponentClass.class);
        componentClass = applicationContext.getBean(ComponentClass.class);
    }

    @Test
    void testMyAnnotation() {

        Assertions.assertNotNull(componentClass.user);
    }

    @Test
    void testMyAutowiredAnnotationBeanPostProcessor() {
        Assertions.assertNotNull(componentClass.normalAutowiredUser);
        Assertions.assertNull(componentClass.newInjectUser);
    }

    @Configuration
    public static class ConfigClass {
        @Bean
        public User user() {
            return new User(12L, "myAutowiredUser");
        }

        @Bean
        public AutowiredAnnotationBeanPostProcessor myAutowiredAnnotationBeanPostProcessor() {
            AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor =
                    new AutowiredAnnotationBeanPostProcessor();

            autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(NewInject.class);
            return autowiredAnnotationBeanPostProcessor;
        }
    }

    @Component
    @Import(ConfigClass.class)
    public static class ComponentClass {
        @MyAutowired
        public User user;

        @Autowired
        public User normalAutowiredUser;

        @NewInject
        public User newInjectUser;
    }

    @AfterEach
    public void closeApplicationContext() {
        applicationContext.close();
    }
}
