package top.daoyang.databing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import top.daoyang.spring.ioc.overview.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link org.springframework.validation.DataBinder DataBinder} 测试类
 */
public class DataBindingTest {

    /**
     * 默认的属性情况，允许嵌套属性赋值，赋值未知属性不报错
     */
    @Test
    public void mainTest() {
        User user = new User();

        DataBinder dataBinder = new DataBinder(user);

        Map<String, Object> map = new HashMap<>();
        map.put("id", "12");
        map.put("name", "dataBinderName");
        map.put("address.state", "TX");
        map.put("none", "none");

        PropertyValues propertyValues = new MutablePropertyValues(map);

        dataBinder.bind(propertyValues);

        Assertions.assertEquals(12L, user.getId());
        Assertions.assertEquals("dataBinderName", user.getName());
        Assertions.assertNotNull(user.getAddress());
        Assertions.assertEquals("TX", user.getAddress().getState());

        BindingResult bindingResult = dataBinder.getBindingResult();
        bindingResult.getAllErrors().forEach(System.out::println);
    }

    /**
     * 不允许使用嵌套路径、也不报错
     */
    @Test
    public void notAllowNestPropTest() {
        User user = new User();

        DataBinder dataBinder = new DataBinder(user);
        // 不允许使用嵌套路径
        dataBinder.setAutoGrowNestedPaths(false);
        // 嵌套路径不存在时，不报错
        dataBinder.setIgnoreInvalidFields(true);

        Map<String, Object> map = new HashMap<>();
        map.put("address.state", "TX");

        PropertyValues propertyValues = new MutablePropertyValues(map);


        dataBinder.bind(propertyValues);

        Assertions.assertNull(user.getAddress());
    }

    /**
     * 测试必填字段，如果必填字段中存在为赋值的字段，则会产生{@link BindingResult} 对象
     */
    @Test
    public void testRequireFields() {
        User user = new User();

        DataBinder dataBinder = new DataBinder(user);
        // 不允许使用嵌套路径
        dataBinder.setRequiredFields("id", "name");

        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");

        PropertyValues propertyValues = new MutablePropertyValues(map);

        dataBinder.bind(propertyValues);

        BindingResult bindingResult = dataBinder.getBindingResult();
        Assertions.assertEquals(1, bindingResult.getAllErrors().size());

    }
}
