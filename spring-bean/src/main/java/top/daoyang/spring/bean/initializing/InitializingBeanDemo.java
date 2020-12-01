package top.daoyang.spring.bean.initializing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import top.daoyang.spring.bean.dependency.UserController;
import top.daoyang.spring.ioc.overview.domain.User;

public class InitializingBeanDemo {

    public static void main(String[] args) {

        // postConstruct -> afterPropertiesSet -> initMethod
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InitializingBeanDemo.class);

        applicationContext.refresh();

    }

    // 需要的时候进行初始化
    @Bean(initMethod = "initMethod")
//    @Lazy(value = false)
    public UserController userController() {
        return new UserController();
    }

    @Bean
    public User user() {
        return new User();
    }
}
