package com.hejianlin.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Study {

    //可以包含基本属性，可以定义默认值
    String name () default "hejianlin";

    //这里没有定义默认值，表示在使用注解时需要传入，否则将报错
    String[] mores ();

}
