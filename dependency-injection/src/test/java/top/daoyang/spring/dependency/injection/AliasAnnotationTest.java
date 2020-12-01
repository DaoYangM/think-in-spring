package top.daoyang.spring.dependency.injection;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.List;

public class AliasAnnotationTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ComponentClass.class);

        ComponentClass componentClass = applicationContext.getBean(ComponentClass.class);

        Assertions.assertNotNull(componentClass.user);

        System.out.println("componentClass.users = " + componentClass.users);
    }

    @Configuration
    public static class ConfigurationClass {

        /**
         * {@link org.springframework.core.annotation.AnnotationAwareOrderComparator}
         * 对 {@link org.springframework.core.annotation.Order} 的排序做处理 order值小的在前
         *
         * @return an instance of user
         */
        @Bean
        @Primary
        @Order(2)
        public User user() {
            return new User(22L, "aliasUser");
        }

        @Bean
        @Order(1)
        public User user2() {
            return new User(23L, "user2");
        }
    }

    @Component
    @Getter
    @Import(ConfigurationClass.class)
    public static class ComponentClass {
        @Autowired
        private User user;

        @Autowired
        private List<User> users;
    }
}
