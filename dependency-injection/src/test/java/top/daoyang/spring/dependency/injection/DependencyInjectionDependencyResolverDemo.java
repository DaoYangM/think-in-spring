package top.daoyang.spring.dependency.injection;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * Spring处理依赖注入candidate
 * 主要的解决方法{@link org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveDependency(DependencyDescriptor, String)}
 */
public class DependencyInjectionDependencyResolverDemo {

    @Test
    public void resolverDependency() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ComponentClass.class);

        ComponentClass componentClass = applicationContext.getBean(ComponentClass.class);

        User user = componentClass.getUser();
        Assertions.assertNotNull(user);

        System.out.println("user = " + user);
    }

    @Import(ConfigurationClass.class)
    @Component
    @Getter
    public static class ComponentClass {
        @Autowired
        private User user;

    }

    @Configuration
    public static class ConfigurationClass {
        @Bean
        public User user() {
            return new User(22L, "candidateUser");
        }
    }
}
