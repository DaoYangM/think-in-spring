package top.daoyang.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;
import top.daoyang.spring.ioc.overview.domain.SuperUser;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * {@link org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor}
 * 可以在Bean实例化之前替换将要创建的目标Bean
 */
@Slf4j
@Configuration
class BeanInstantiationAwareTest {

    @Bean
    public User user() {
        return new User(21L, "cb");
    }

    @Autowired
    public Environment environment;

    @Test
    void replaceBean() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        ClassPathResource classPathResource = new ClassPathResource("META-INF/dependency-lookup.xml");
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);

        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        annotatedBeanDefinitionReader.register(BeanInstantiationAwareTest.class);

        beanFactory.addBeanPostProcessor(new PlainUserInstantiationAwareBeanPostProcessor());

        ObjectProvider<SuperUser> superUserObjectProvider = beanFactory.getBeanProvider(SuperUser.class);

        Assertions.assertNotNull(superUserObjectProvider.getIfUnique());

        Assertions.assertEquals("1234", beanFactory.getBean("superUser2", SuperUser.class).getTitle());

        log.error(String.valueOf(superUserObjectProvider.getIfUnique()));

    }

    @Test
    void testDoesInstantiationWareWorksOnBeanMethod() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.addBeanFactoryPostProcessor(
                beanFactory -> beanFactory.addBeanPostProcessor(new PlainUserInstantiationAwareBeanPostProcessor())
        );

        applicationContext.register(getClass());

        applicationContext.refresh();

        User user = applicationContext.getBean(User.class);
        Assertions.assertEquals(21L, user.getId());

        BeanInstantiationAwareTest beanInstantiationAwareTest = applicationContext.getBean(BeanInstantiationAwareTest.class);

        Assertions.assertNull(beanInstantiationAwareTest.environment);

        log.info(user.toString());
    }

    static class PlainUserInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "superUser") && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }

            // return null，将使用容器默认的初始化流程
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "superUser2")) {
                MutablePropertyValues propertyValues = new MutablePropertyValues(pvs);

                propertyValues.removePropertyValue("title");
                propertyValues.add("title", "1234");

                return propertyValues;
            }
            return null;
        }
    }


}
