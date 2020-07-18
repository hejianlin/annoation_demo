package com.hejianlin.model;

import com.hejianlin.annoation.Study;
import lombok.Data;

//由于name有默认值，所以这里可以不用传入，mores不传，则会报错
@Study(mores = {"liLei","liMei"})
@Data
public class Person {
    private String name;
    @Study(mores = {"liBai","liHei"},name = "liTie")
    private int age;
}
