package top.daoyang.spring.dependency.injection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import top.daoyang.spring.dependency.injection.annotation.SuperUser;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * {@link Qualifier} 自定义Qualifier注解使用
 */
class QualifiersAnnotationTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    @BeforeEach
    public void setUpApplicationContext() {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup.xml");
        applicationContext.register(QualifiersAnnotationTest.class);
        applicationContext.refresh();
    }

    @AfterEach
    public void closeApplicationContext() {
        applicationContext.close();
    }

    @Bean
    @Qualifier
    public User qualifierUser() {
        return new User(12L, "qualifierUser");
    }

    @Bean
    @SuperUser
    public User qualifierUser2() {
        return new User(13L, "qualifierUser2");
    }

    /**
     * 获取标注@Qualifier的Bean
     */
    @Qualifier
    @Autowired
    public List<User> qualifierUsers;

    @SuperUser
    @Autowired
    public List<User> qualifierSuperUsers;

    @Autowired
    public List<User> allUsers;

    @Test
    @DisplayName("get all qualifier users")
    public void getQualifierUsers() {
        QualifiersAnnotationTest qualifiersAnnotationTest = applicationContext.getBean(getClass());

        System.out.println("qualifiersAnnotationTest.qualifierUsers = " + qualifiersAnnotationTest.qualifierUsers);
    }

    @Test
    @DisplayName("get all qualifier super users")
    public void getQualifierSuperUsers() {
        QualifiersAnnotationTest qualifiersAnnotationTest = applicationContext.getBean(getClass());
        Assertions.assertEquals(qualifiersAnnotationTest.qualifierSuperUsers.size(), 1);
    }

    @Test
    @DisplayName("get all users")
    public void getAllUsers() {
        QualifiersAnnotationTest qualifiersAnnotationTest = applicationContext.getBean(getClass());
        System.out.println("qualifiersAnnotationTest.allUsers = " + qualifiersAnnotationTest.allUsers);

        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);

        System.out.println("beansOfType = " + beansOfType);
    }
}

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                res.add(Math.abs(index+1));
            nums[index] = -nums[index];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
    }
}
