package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} test
 */
public class ObjectProviderInjectionTest {

    @Test
    public void getObjectProviderBean() {
        new AnnotationConfigApplicationContext(ConfigurationClass.class);
    }

    @Configuration
    public static class ConfigurationClass {

        @Bean
        public User user() {
            return new User(1L, "1");
        }


        @Autowired
        public void userObjectProvider(ObjectProvider<User> userObjectProvider) {
            Assertions.assertNotNull(userObjectProvider);
        }
    }

}
