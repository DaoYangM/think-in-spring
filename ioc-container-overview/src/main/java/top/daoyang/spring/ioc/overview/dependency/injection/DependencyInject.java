package top.daoyang.spring.ioc.overview.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import top.daoyang.spring.ioc.overview.annotation.Super;
import top.daoyang.spring.ioc.overview.domain.User;
import top.daoyang.spring.ioc.overview.repository.UserRepository;

import java.util.Map;

/**
 * 依赖注入
 */
public class DependencyInject {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection.xml");

//        UserRepository userRepository = beanFactory.getBean(UserRepository.class);

        // 依赖查找 找不到
//        System.out.println(beanFactory.getBean(BeanFactory.class));

//        System.out.println(beanFactory.getBean(Environment.class));
//        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory() == beanFactory);
    }
}
