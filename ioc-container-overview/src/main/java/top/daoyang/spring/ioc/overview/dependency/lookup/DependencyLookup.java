package top.daoyang.spring.ioc.overview.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.daoyang.spring.ioc.overview.annotation.Super;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 依赖查找
 */
public class DependencyLookup {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup.xml");

        lookupByName(beanFactory);

//        lookupInLazy(beanFactory);

        lookupByType(beanFactory);

        lookupByAnnotation(beanFactory);
    }

    /**
     * 根据Annotation进行查找
     * @param beanFactory beanFactory
     */
    private static void lookupByAnnotation(BeanFactory beanFactory) {
        Map<String, Object> beansWithAnnotation =
                ((ApplicationContext) beanFactory).getBeansWithAnnotation(Super.class);

        System.out.println(beansWithAnnotation);
    }

    /**
     * 根据类型进行查找
     * @param beanFactory beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        Map<String, User> bean = ((ApplicationContext)beanFactory).getBeansOfType(User.class);
        System.out.println(bean);
    }

//    private static void lookupInLazy(BeanFactory beanFactory) {
//        ObjectFactory<User> objectFactory = beanFactory.getBean("objectFactory", ObjectFactory.class);
//        User object = objectFactory.getObject();
//    }

    /**
     * 根据bean name进行查找
     * @param beanFactory beanFactory
     */
    private static void lookupByName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }
}
