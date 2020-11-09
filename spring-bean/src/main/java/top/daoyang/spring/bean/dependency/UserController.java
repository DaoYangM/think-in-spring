package top.daoyang.spring.bean.dependency;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import top.daoyang.spring.ioc.overview.domain.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserController implements InitializingBean, DisposableBean {

    private User user;

    public User getUser() {
        return user;
    }

    @Autowired(required = false)
    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Post construct");
    }

    public void afterPropertiesSet() {
        System.out.println("after properties set");
    }

    public void initMethod() {
        System.out.println("init-method");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("disposable bean...");
    }

    public void destroyMethod() {
        System.out.println("destroyMethod...");
    }
}
