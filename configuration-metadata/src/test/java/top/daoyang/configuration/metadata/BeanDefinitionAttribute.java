package top.daoyang.configuration.metadata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import top.daoyang.spring.ioc.overview.domain.User;

public class BeanDefinitionAttribute {

    @Test
    public void beanDefinitionAttribute() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        beanDefinitionBuilder.addPropertyValue("name", "deyangye");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        beanDefinition.setAttribute("attributeName", "attributeValue");

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("user", beanDefinition);

        User user = defaultListableBeanFactory.getBean("user", User.class);
        Assertions.assertEquals("deyangye", user.getName());


    }
}
