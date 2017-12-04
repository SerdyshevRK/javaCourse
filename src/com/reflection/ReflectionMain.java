package com.reflection;

import com.reflection.dependency.DIContext;
import com.reflection.dependency.DIInterfaceExample;
import com.reflection.dependency.DISecondClass;
import com.reflection.exceptions.ClassBuildException;

public class ReflectionMain {
    public static void main(String[] args){
        DIContext context = new DIContext();
        try {
            DISecondClass sc = context.get("com.reflection.dependency.DISecondClass");
            String string = sc.getI().getValue();
            System.out.println("Some random value: " + string);

            DIInterfaceExample ie = context.get("com.reflection.dependency.DIConcreteClass");
            assert (ie == sc.getI());
        } catch (ClassBuildException e) {
            e.printStackTrace();
        }
    }
}
