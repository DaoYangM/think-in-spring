package top.daoyang.spring.bean.Instantiation;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.daoyang.spring.bean.dependency.UserController;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * bean实例化
 */
@Configuration
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("META-INF/bean-instantiation.xml");

        User staticFactoryUser = applicationContext.getBean("staticFactoryUser", User.class);

        // true scope singleton
        System.out.println(staticFactoryUser == applicationContext.getBean("staticFactoryUser", User.class));

        // false scope prototype
        User instanceFactoryUser = applicationContext.getBean("instanceFactoryUser", User.class);
        System.out.println(instanceFactoryUser == applicationContext.getBean("instanceFactoryUser", User.class));

        // false
        User userFactoryBean = applicationContext.getBean("userFactoryBean", User.class);
        System.out.println(userFactoryBean == staticFactoryUser);

        // autowireCapableBeanFactory
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserController userController = (UserController) autowireCapableBeanFactory.createBean(UserController.class, 1, true);

        System.out.println(userController.getUser());

    }

}
