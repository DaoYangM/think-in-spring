package top.daoyang.spring.ioc.overview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHolder implements InitializingBean, DisposableBean, SmartInitializingSingleton {

    private Integer id;

    private User user;

    private User user2;

    @PostConstruct
    public void postConstruct() {
        id = 1;
        System.out.println("PostConstruct: id = " + id);
    }

    @PreDestroy
    public void preDestroy() {
        id = -10;
        System.out.println("PreDestroy: id = " + id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        id = 2;
        System.out.println("AfterPropertiesSet: id = " + id);
    }

    public void initMethod() {
        id = 3;
        System.out.println("InitMethod: id = " + id);
    }

    public void destroyMethod() {
        id = -12;
        System.out.println("DestroyMethod: id = " + id);
    }


    @Override
    public void afterSingletonsInstantiated() {
        id = 4;
        System.out.println("AfterSingletonsInstantiated: " + id);
    }

    @Override
    public void destroy() throws Exception {
        id = -11;
        System.out.println("Destroy: id = " + id);
    }
}
