package top.daoyang.spring.dependency.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Spring 依赖查找和依赖注册的共同来源有BeanDefinition, singletonBean
 * 但是依赖注入比依赖查找的来源多一个地方，就是resolvableDependency
 * <p> 外部化的配置也可以作为依赖注入的来源
 */
class DependencySourceTest {

    private AnnotationConfigApplicationContext annotationConfigApplicationContext;

    @Test
    void differenceDependencySourceWithDependencyLookAndDependencyInjection() {

        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ComponentClass.class);
        ComponentClass componentClass = annotationConfigApplicationContext.getBean(ComponentClass.class);

        Assertions.assertNotNull(componentClass.beanFactory);
        Assertions.assertNotNull(componentClass.resourceLoader);
        Assertions.assertNotNull(componentClass.applicationEventPublisher);
        Assertions.assertNotNull(componentClass.applicationContext);

        Assertions.assertEquals(componentClass.resourceLoader, componentClass.applicationContext);
        Assertions.assertEquals(componentClass.applicationEventPublisher, componentClass.applicationContext);
        Assertions.assertEquals(componentClass.applicationContext, annotationConfigApplicationContext);

        // 依赖查找为null
        Assertions.assertNull(annotationConfigApplicationContext.getBeanProvider(BeanFactory.class).getIfAvailable());
    }

    @Component
    private static class ComponentClass {

        // 依赖注入
        @Autowired
        private BeanFactory beanFactory;

        @Autowired
        private ResourceLoader resourceLoader;

        @Autowired
        private ApplicationEventPublisher applicationEventPublisher;

        @Autowired
        private ApplicationContext applicationContext;
    }

    @Test
    void propertiesSourceWithAtValueAnnotationTest() {
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ConfigurationClass.class);
        ConfigurationClass configurationClass = annotationConfigApplicationContext.getBean(ConfigurationClass.class);

        Assertions.assertEquals("appName", configurationClass.appName);
    }

    @Configuration
    @PropertySource("classpath:/META-INF/app.properties")
    public static class ConfigurationClass {

        @Value("${app.name}")
        private String appName;
    }
}
