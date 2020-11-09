package top.daoyang.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过{@link org.springframework.beans.factory.BeanFactory#getBeanProvider(Class)}
 * 返回{@link ObjectProvider} 类，可以间接、按需的获取Spring Bean
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ObjectProviderDemo.class);

        ObjectProvider<String> stringBeanProvider = applicationContext.getBeanProvider(String.class);

        for (String s : stringBeanProvider) {
            System.out.println(s);
        }

    }

    @Bean
    public String hello() {
        return "hello";
    }

    @Bean
    public String world() {
        return "world";
    }
}
