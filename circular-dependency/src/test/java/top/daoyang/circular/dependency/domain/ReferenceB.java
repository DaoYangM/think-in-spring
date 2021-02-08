package top.daoyang.circular.dependency.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReferenceB {

    private ReferenceA referenceA;

    public ReferenceA getReferenceA() {
        return referenceA;
    }

    /**
     * ReferenceB 注入 ReferenceA
     * @param referenceA referenceA
     */
    @Autowired
    public void setReferenceA(ReferenceA referenceA) {
        this.referenceA = referenceA;
    }
}
