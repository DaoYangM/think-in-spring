package top.daoyang.spring.dependency.injection;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import top.daoyang.spring.ioc.overview.domain.UserHolder;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition 来手动setter注入依赖}
 */
public class BeanDefinitionSetterInjection {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);

        beanDefinitionBuilder.addPropertyReference("user", "superUser");

        defaultListableBeanFactory.registerBeanDefinition("userHolder", beanDefinitionBuilder.getBeanDefinition());

        UserHolder userHolder = defaultListableBeanFactory.getBean("userHolder", UserHolder.class);

        System.out.println("userHolder = " + userHolder);
    }
}
