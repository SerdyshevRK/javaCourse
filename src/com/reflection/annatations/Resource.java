package com.reflection.annatations;

import com.reflection.dependency.DummyClass;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Resource {
    boolean singleton() default true;
    Class<?> value() default DummyClass.class;
}
