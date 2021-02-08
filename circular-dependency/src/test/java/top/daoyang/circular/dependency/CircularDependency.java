package top.daoyang.circular.dependency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.daoyang.circular.dependency.domain.ReferenceA;

/**
 * Spring 循环依赖测试
 */
public class CircularDependency {

    @Test
    public void testCircularDependency() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("top/daoyang/circular/dependency");

        applicationContext.refresh();

        ReferenceA referenceA = applicationContext.getBean(ReferenceA.class);

        Assertions.assertNotNull(referenceA.getReferenceB());

        applicationContext.close();
    }
}
