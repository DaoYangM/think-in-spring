package top.daoyang.spring.ioc.overview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.daoyang.spring.ioc.overview.annotation.Super;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Super
public class SuperUser extends User {
    private String title;
}
