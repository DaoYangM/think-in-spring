package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.stereotype.Component;
import top.daoyang.spring.ioc.overview.domain.SuperUser;

/**
 * {@link org.springframework.beans.factory.BeanFactory#getBean(String)} 不会autowired
 */
public class BeanFactoryGetBeanNotAutowiredTest {

    private DefaultListableBeanFactory beanFactory;

    @BeforeEach
    public void initBeanFactory() {
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");
    }

    @Test
    public void getGetBean() {
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        annotatedBeanDefinitionReader.register(ComponentClass.class);

        ComponentClass componentClass = beanFactory.getBean(ComponentClass.class);
        Assertions.assertNotNull(componentClass.superUser);
    }

    @Component
    public static class ComponentClass {
        @Autowired
        private SuperUser superUser;
    }
}
