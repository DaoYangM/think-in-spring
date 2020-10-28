package top.daoyang.spring.bean.definition;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition}
 * 构建示例
 */
public class BeanDefinitionDemo {

    public static void main(String[] args) {
        beanDefinitionBuilder();

        abstractBeanDefinition();
    }

    private static void beanDefinitionBuilder() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "beanDefinition");

        System.out.println(beanDefinitionBuilder.getBeanDefinition());
    }

    private static void abstractBeanDefinition() {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("id", 1L)
                .add("name", "beanDefinition");
        genericBeanDefinition.setPropertyValues(propertyValues);

        System.out.println(genericBeanDefinition);
    }
}