package top.daoyang.spring.dependency.injection;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * Bean 的字段注入
 * <p><b>不能注入static字段</b>
 *
 */
public class BeanFieldInjection {

    // 字段手动注入，不能注入static字段
    @Autowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanFieldInjection.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");

        applicationContext.refresh();
        ObjectProvider<BeanFieldInjection> beanProvider = applicationContext.getBeanProvider(BeanFieldInjection.class);

        BeanFieldInjection beanFieldInjection = beanProvider.getIfAvailable();

        assert beanFieldInjection != null;
        System.out.println("user = " + beanFieldInjection.user);
    }
}
