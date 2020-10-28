package top.daoyang.spring.ioc.overview.ioccontainer;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * 使用XML Bean定义创建底层BeanFactory
 */
public class BeanFactoryXMLContainer {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        System.out.println(xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml"));

        System.out.println(beanFactory.getBeansOfType(User.class));

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanFactoryXMLContainer.class);
        System.out.println(applicationContext.getBean(User.class));
    }

    @Bean
    public User user () {
        return new User();
    }
}