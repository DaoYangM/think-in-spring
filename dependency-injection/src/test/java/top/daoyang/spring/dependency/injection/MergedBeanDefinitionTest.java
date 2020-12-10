package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import top.daoyang.spring.ioc.overview.domain.SuperUser;

import javax.inject.Inject;

/**
 * 对何时进行BeanDefinition merge进行跟进
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MergedBeanDefinitionTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeAll
    void initBeanFactory() {
        applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");

        applicationContext.register(ComponentClass.class);
        applicationContext.refresh();

    }

    /**
     * {@link AbstractBeanFactory#getMergedBeanDefinition(java.lang.String, org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.factory.config.BeanDefinition)}
     */
    @Test
    void testMergedBeanDefinition() {
        ComponentClass componentClass = applicationContext.getBean(ComponentClass.class);
        Assertions.assertNotNull(componentClass.superUser);
    }

    @Test
    void testInjectAnnotation() {
        ComponentClass componentClass = applicationContext.getBean(ComponentClass.class);
        Assertions.assertNotNull(componentClass.superUser2);
    }

    @Component
    static class ComponentClass {
        @Autowired
        private SuperUser superUser;

        @Inject
        private SuperUser superUser2;
    }
}
