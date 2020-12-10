package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import top.daoyang.spring.dependency.injection.annotation.NewInject;
import top.daoyang.spring.ioc.overview.domain.SuperUser;

class MyAnnotationBeanPostProcessorTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    public void initApplicationContext() {
        applicationContext = new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");

        applicationContext.register(getClass());
        applicationContext.refresh();
    }

    /**
     * 如果不是{@code static} 修饰的话在注册BeanPostProcessor {@link org.springframework.context.support.AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)}
     * 的时候会需要先初始化此类，
     * <p>
     * 在初始化此类的时候，由于beanPostProcessor还没注册上，所以相关注解是没法处理的
     *
     * @return 自定义AutowiredBeanPostProcessor
     */
    @Bean
    public static AutowiredAnnotationBeanPostProcessor myAutowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor =
                new AutowiredAnnotationBeanPostProcessor();

        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(NewInject.class);
        return autowiredAnnotationBeanPostProcessor;
    }

    @NewInject
    public SuperUser outUser;

    @Autowired
    public SuperUser outAutowiredUser;

    @Test
    void test() {
        MyAnnotationBeanPostProcessorTest bean = applicationContext.getBean(MyAnnotationBeanPostProcessorTest.class);

        Assertions.assertNotNull(bean.outUser);
        Assertions.assertNotNull(bean.outAutowiredUser);

        System.out.println("bean.outAutowiredUser = " + bean.outAutowiredUser);
    }

}
