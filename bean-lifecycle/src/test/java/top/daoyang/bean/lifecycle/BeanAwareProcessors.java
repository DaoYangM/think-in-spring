package top.daoyang.bean.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Bean 通用 {@link org.springframework.beans.factory.Aware } 处理
 * 发生在{@link org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessProperties(PropertyValues, Object, String)} 之后
 * @see org.springframework.beans.factory.BeanNameAware
 * @see org.springframework.beans.factory.BeanClassLoaderAware
 * @see org.springframework.beans.factory.BeanFactoryAware
 *
 * org.springframework.context.support.ApplicationContextAwareProcessor 处理如下回调
 * @see org.springframework.context.EnvironmentAware
 * @see org.springframework.context.EmbeddedValueResolverAware
 * @see org.springframework.context.ResourceLoaderAware
 * @see org.springframework.context.ApplicationEventPublisherAware
 * @see org.springframework.context.MessageSourceAware
 * @see org.springframework.context.ApplicationContextAware
 *
 *
 */
class BeanAwareProcessors {

    /**
     * 测试由BeanFactory处理的Aware回调
     */
    @Test
    void testBeanFactoryAware() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        annotatedBeanDefinitionReader.registerBean(BeanFactoryAwareReceiver.class);

        beanFactory.getBean(BeanFactoryAwareReceiver.class);
    }

    /**
     * 测试由ApplicationContext处理的Aware回调
     */
    @Test
    void testApplicationAware() {
        new AnnotationConfigApplicationContext(ApplicationAwareReceiver.class);
    }

    /**
     * BeanFactory处理产生的回调
     */
    @Component
    static class BeanFactoryAwareReceiver implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware {

        @Override
        public void setBeanClassLoader(ClassLoader classLoader) {
            System.out.println("classLoader = " + classLoader);
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            System.out.println("beanFactory = " + beanFactory);
        }

        @Override
        public void setBeanName(String name) {
            System.out.println("beanName = " + name);
        }
    }

    /**
     * 这个类单独实现的接口是由ApplicationContextAwareProcessor 处理产生的Aware回调
     * BeanFactory无法触发有关ApplicationContext的Aware回调
     */
    @Component
    static class ApplicationAwareReceiver extends BeanFactoryAwareReceiver implements EnvironmentAware, EmbeddedValueResolverAware,
            ResourceLoaderAware,
            ApplicationEventPublisherAware, MessageSourceAware, ApplicationContextAware {

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            System.out.println("applicationContext = " + applicationContext);
        }

        @Override
        public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
            System.out.println("applicationEventPublisher = " + applicationEventPublisher);
        }

        @Override
        public void setEmbeddedValueResolver(StringValueResolver resolver) {
            System.out.println("resolver = " + resolver);
        }

        @Override
        public void setEnvironment(Environment environment) {
            System.out.println("environment = " + environment);
        }

        @Override
        public void setMessageSource(MessageSource messageSource) {
            System.out.println("messageSource = " + messageSource);
        }

        @Override
        public void setResourceLoader(ResourceLoader resourceLoader) {
            System.out.println("resourceLoader = " + resourceLoader);
        }
    }
}
