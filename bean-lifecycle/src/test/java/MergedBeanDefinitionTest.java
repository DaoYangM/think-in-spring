import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import top.daoyang.spring.ioc.overview.domain.SuperUser;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * merge beanDefinition过程会将
 * {@link org.springframework.beans.factory.support.GenericBeanDefinition} 转换为
 * {@link org.springframework.beans.factory.support.RootBeanDefinition}
 */
class MergedBeanDefinitionTest {

    @Test
    void mergeBeanDefinitionTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        ClassPathResource classPathResource = new ClassPathResource("META-INF/dependency-lookup.xml");
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);

        beanFactory.getBean("user", User.class);

        beanFactory.getBean("superUser", SuperUser.class);

    }
}
