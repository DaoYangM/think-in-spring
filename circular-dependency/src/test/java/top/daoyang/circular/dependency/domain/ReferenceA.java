package top.daoyang.circular.dependency.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReferenceA {

    private ReferenceB referenceB;

    public ReferenceB getReferenceB() {
        return referenceB;
    }

    /**
     * ReferenceA 注入 ReferenceB
     * @param referenceB referenceB
     */
    @Autowired
    public void setReferenceB(ReferenceB referenceB) {
        this.referenceB = referenceB;
    }
}
