package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.List;
import java.util.Map;

class AutoWiringXmlSetterInjectionTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    @Bean
    public User userTest() {
        return new User(11L, "junit5");
    }

    @BeforeEach
    public void setUpApplicationContext() {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");
        applicationContext.register(AutoWiringXmlSetterInjectionTest.class);
        applicationContext.refresh();
    }

    @AfterEach
    public void closeApplicationContext() {
        applicationContext.close();
    }

    @Test
    public void test1() {
        System.out.println(applicationContext.getBean(User.class, "junit5"));
    }

    @Test
    public void test2() {
        System.out.println(applicationContext.getBean(User.class, "junit5"));
    }

    @Test
    public void test3() {
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        System.out.println("beansOfType = " + beansOfType);
    }

    @Autowired
    public List<User> allUsers;

    @Qualifier
    @Bean
    public User qualifierUser() {
        return new User(12L, "qualifierUser");
    }

    @Qualifier
    @Bean
    public User qualifierUser2() {
        return new User(13L, "qualifierUser2");
    }

    @Test
    public void getAllUsers() {
        System.out.println(applicationContext.getBean(getClass()).allUsers);
    }
}