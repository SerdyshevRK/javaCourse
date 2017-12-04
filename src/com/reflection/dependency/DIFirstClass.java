package com.reflection.dependency;

import com.reflection.annatations.Resource;

public class DIFirstClass {
    private String string;

    @Resource(DIConcreteClass.class)
    private DIInterfaceExample interfaceRefference;

    public DIInterfaceExample getInterfaceExample() {
        return interfaceRefference;
    }
}
