package top.daoyang.spring.dependency.injection;

import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

public class DependencyInjectionDependencyInjectionDifferenceSource {

    AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    public void initApplicationContext() {
        applicationContext = new AnnotationConfigApplicationContext(ComponentClass.class);
    }

    @AfterEach
    public void closeApplicationContext() {
        applicationContext.close();
    }

    /**
     * 依赖查找不能查找到部分bean
     */
    @Test
    public void dependencyLookCantFindSomeBeans() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(BeanFactory.class));
    }

    @Test
    public void dependencyInjectionBeanFactory() {
        ComponentClass componentClass = applicationContext.getBean(ComponentClass.class);

        Assertions.assertNotNull(componentClass.beanFactory);
    }

    @Component
    @Getter
    public static class ComponentClass {
        @Autowired
        private BeanFactory beanFactory;
    }
}
