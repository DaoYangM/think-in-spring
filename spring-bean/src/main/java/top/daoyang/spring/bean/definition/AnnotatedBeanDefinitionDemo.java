package top.daoyang.spring.bean.definition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * Annotation beanDefinition
 */

@Import(AnnotatedBeanDefinitionDemo.Config.class)
public class AnnotatedBeanDefinitionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // registry with annotation
        applicationContext.register(AnnotatedBeanDefinitionDemo.class);
        applicationContext.refresh();

        // registry with beanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 10L)
                .addPropertyValue("name", "beanDefinitionUser");
        applicationContext.registerBeanDefinition("userBeanDefinition", beanDefinitionBuilder.getBeanDefinition());


        // registry with BeanDefinitionReaderUtils
        BeanDefinitionReaderUtils
                .registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), applicationContext);

        Map<String, User> bean = applicationContext.getBeansOfType(User.class);
        System.out.println(bean);
    }

    @Configuration
    public static class Config {
        @Bean
        public User user() {
            User user = new User();
            user.setId(3L);
            user.setName("annotatedUserBean");
            return user;
        }
    }
}
