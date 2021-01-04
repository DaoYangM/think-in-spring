package top.daoyang.bean.lifecycle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.stereotype.Component;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * {@link org.springframework.beans.factory.support.BeanDefinitionReader}
 * 实现类生成{@link org.springframework.beans.factory.config.BeanDefinition}
 */
class BeanDefinitionReaderTest {

    private final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    @Test
    void annotationBeanDefinitionReaderTest() {
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        annotatedBeanDefinitionReader.register(ComponentClass.class);

        ComponentClass component = beanFactory.getBean(ComponentClass.class);
        Assertions.assertNotNull(component);

        ObjectProvider<User> nullUser = beanFactory.getBeanProvider(User.class);
        Assertions.assertNull(nullUser.getIfAvailable());
    }

    @Component
    private static class ComponentClass {

        // 这个Bean不会被AnnotationBeanDefinitionReader组册成为Bean
        @Bean
        public User user() {
            return new User(1L, "geek");
        }
    }

    @Test
    void propertiesBeanDefinitionReaderTest() {
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        ClassPathResource classPathResource = new ClassPathResource("/META-INF/user-bean-definition.properties");
        EncodedResource encodedResource = new EncodedResource(classPathResource, "UTF-8");

        propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);

        ObjectProvider<User> userObjectProvider = beanFactory.getBeanProvider(User.class);

        Assertions.assertNotNull(userObjectProvider.getIfAvailable());

        System.out.println(userObjectProvider.getIfAvailable());
    }
}
