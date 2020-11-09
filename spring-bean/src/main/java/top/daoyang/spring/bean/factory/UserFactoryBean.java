package top.daoyang.spring.bean.factory;

import org.springframework.beans.factory.FactoryBean;
import top.daoyang.spring.ioc.overview.domain.User;

/**
 * 使用{@link org.springframework.beans.factory.FactoryBean} 来实例化User
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
