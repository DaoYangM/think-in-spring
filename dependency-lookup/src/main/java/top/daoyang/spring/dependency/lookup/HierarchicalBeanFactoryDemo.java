package top.daoyang.spring.dependency.lookup;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.daoyang.spring.ioc.overview.domain.User;

public class HierarchicalBeanFactoryDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext parentClassPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("META-INF/dependency-lookup.xml");

        System.out.println(parentClassPathXmlApplicationContext.containsLocalBean("user"));
        AnnotationConfigApplicationContext childApplicationContext = new AnnotationConfigApplicationContext();
        childApplicationContext.register(HierarchicalBeanFactoryDemo.class);
        childApplicationContext.refresh();

        DefaultListableBeanFactory defaultListableBeanFactory = childApplicationContext.getDefaultListableBeanFactory();
        defaultListableBeanFactory.setParentBeanFactory(parentClassPathXmlApplicationContext);


        System.out.println(childApplicationContext.containsLocalBean("user"));
        System.out.println(childApplicationContext.containsBean("user"));
        System.out.println(childApplicationContext.getBean("user", User.class));
    }

    @Bean
    public String hello() {
        return "hello";
    }

//    @Bean
    public User user() {
        User user = new User();

        user.setId(10L);
        user.setName("child user");

        return user;
    }
}
