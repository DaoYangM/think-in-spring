package top.daoyang.spring.ioc.overview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.id = 10L;
        user.name = "createByStaticFactory";
        return user;
    }

    public User create() {
        User user = new User();
        user.id = 11L;
        user.name = "createByInstantFactory";
        return user;
    }

    public void init() {
        System.out.println("init-method");
    }
}
