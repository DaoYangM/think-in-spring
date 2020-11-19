package top.daoyang.spring.ioc.overview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHolder {

    private User user;

    private User user2;
}
