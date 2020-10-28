package top.daoyang.spring.bean.alias;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 别名使用
 */
public class AliasDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/bean-alias.xml");

        System.out.println(applicationContext.getBean("aliasUser"));
    }
}
