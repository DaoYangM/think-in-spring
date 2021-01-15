package top.daoyang.bean.lifecycle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import top.daoyang.spring.ioc.overview.domain.UserHolder;

public class BeanDestructionAwarePostProcessor {

    @Test
    public void testDestructionBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwarePostProcessor());

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);

        beanFactory.destroyBean("userHolder", userHolder);

        Assertions.assertEquals(-12, userHolder.getId());
    }

    static class MyDestructionAwarePostProcessor implements DestructionAwareBeanPostProcessor {

        @Override
        public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
            if (bean instanceof UserHolder) {
                UserHolder userHolder = (UserHolder) bean;
                userHolder.setId(-1);
            }
        }
    }
}
