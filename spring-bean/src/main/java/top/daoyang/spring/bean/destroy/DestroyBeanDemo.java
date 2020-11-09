package top.daoyang.spring.bean.destroy;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import top.daoyang.spring.bean.dependency.UserController;

/**
 * Bean 的销毁
 */
public class DestroyBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(DestroyBeanDemo.class);
        applicationContext.refresh();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        UserController userController = new UserController();

        // 注册单例bean
        beanFactory.registerSingleton("userControllerSingleton", userController);

        System.out.println(userController == applicationContext.getBean("userController"));

        applicationContext.close();
    }

    @Bean(destroyMethod = "destroyMethod")
    public UserController userController() {
        return new UserController();
    }
}
