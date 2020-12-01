package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.daoyang.spring.ioc.overview.domain.User;

public class AliasAnnotationTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ConfigurationClass.class);

        User au = applicationContext.getBean("au2", User.class);

        Assertions.assertNotNull(au);
    }

    @Configuration
    public static class ConfigurationClass {

        @Bean({"au", "au2"})
        public User user() {
            return new User(22L, "aliasUser");
        }
    }
}
