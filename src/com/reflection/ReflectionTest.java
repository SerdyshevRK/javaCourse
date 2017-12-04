package com.reflection;

public class ReflectionTest {
    int intVal = 1;
    double doubleVal = 2;
    InnerTestClass inner = new InnerTestClass();

    class InnerTestClass {
        boolean isBool = true;
        String str = "Some string";
    }
}
