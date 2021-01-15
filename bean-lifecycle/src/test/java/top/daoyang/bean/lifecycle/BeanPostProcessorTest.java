package top.daoyang.bean.lifecycle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import top.daoyang.spring.ioc.overview.domain.UserHolder;

/**
 * {@link org.springframework.beans.factory.config.BeanPostProcessor} 接口实现
 * 在自定义的initMethod方法调用之前处理
 */
class BeanPostProcessorTest {

    @Test
    void testMyBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");

        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        beanFactory.preInstantiateSingletons();

        Assertions.assertEquals(4, userHolder.getId());
    }

    /**
     * 判断bean是否是{@link UserHolder}类型，如果是则设置id为1000
     */
    static class MyBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof UserHolder) {
                UserHolder userHolder = (UserHolder) bean;
                userHolder.setId(1000);
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof UserHolder) {
                UserHolder userHolder = (UserHolder) bean;
                userHolder.setId(1001);
            }

            return bean;
        }
    }

}
