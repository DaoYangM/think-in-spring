package top.daoyang.spring.ioc.overview.repository;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.List;

@Data
public class UserRepository {

    private List<User> users;

    private BeanFactory beanFactory;
}