package com.reflection.dependency;

import com.reflection.annatations.Resource;

public class DISecondClass {
    private int value;

    @Resource
    private DIFirstClass fClassInstance;

    public DIInterfaceExample getI() {
        return fClassInstance.getInterfaceExample();
    }
}
