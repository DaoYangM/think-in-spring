package top.daoyang.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import top.daoyang.spring.ioc.overview.domain.UserHolder;

/**
 * 在xml文件中使用autowire属性来自动的注入
 */
public class AutoWiringXmlSetterInjection {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        xmlBeanDefinitionReader.loadBeanDefinitions(
                "META-INF/autowiring-dependency-injection.xml",
                "META-INF/dependency-lookup.xml");

        UserHolder userHolder = defaultListableBeanFactory.getBean("userHolder", UserHolder.class);

        System.out.println("userHolder = " + userHolder);
    }
}
