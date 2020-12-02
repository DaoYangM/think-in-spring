package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import top.daoyang.spring.ioc.overview.domain.SuperUser;

/**
 * 对何时进行BeanDefinition merge进行跟进
 */
public class MergedBeanDefinitionTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    public void initBeanFactory() {
        applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");
    }

    /**
     * {@link AbstractBeanFactory#getMergedBeanDefinition(java.lang.String, org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.factory.config.BeanDefinition)}
     */
    @Test
    public void testMergedBeanDefinition() {
        applicationContext.register(ComponentClass.class);
        applicationContext.refresh();

        ComponentClass componentClass = applicationContext.getBean(ComponentClass.class);
        Assertions.assertNotNull(componentClass.superUser);
    }

    @Component
    public static class ComponentClass {
        @Autowired
        private SuperUser superUser;
    }
}
