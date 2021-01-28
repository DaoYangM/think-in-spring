package top.daoyang.configuration.metadata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载Properties文件外部化配置
 * 属性配置优先级  jvm属性 -> 环境变量 -> 外部化配置
 */
@PropertySource(
        name = "prop",
        value = "classpath:/META-INF/user.properties"
)
public class PropertiesPropertySource {

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);

        return user;
    }

    @Test
    public void testPropertySource() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        MutablePropertySources mutablePropertySources = applicationContext.getEnvironment().getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put("user.name", "yedeyang");
        org.springframework.core.env.PropertySource<?> propertySource = new MapPropertySource("mapProp", map);
        mutablePropertySources.addFirst(propertySource);

        applicationContext.register(getClass());
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);

        System.out.println("user = " + user);

        Assertions.assertEquals(101, user.getId());

        applicationContext.close();
    }
}
