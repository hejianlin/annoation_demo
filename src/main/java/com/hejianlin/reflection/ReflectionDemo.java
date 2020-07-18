package com.hejianlin.reflection;

import com.hejianlin.annoation.Study;
import com.hejianlin.model.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {

        System.out.println("示例1： 通过反射获取class元信息");
        Person person = new Person();
        //通过对象反射得到元信息
        Class<? extends Person> classByObject = person.getClass();
        //通过类名反射得到元信息
        Class<?> classByName = Class.forName("com.hejianlin.model.Person");
        System.out.println("两种方式得到的元信息："+classByObject+","+classByName+";是否相同："+classByName.equals(classByObject));


        System.out.println("示例2： 通过反射获取类名、包名");
        String name = classByName.getName();
        String simpleName = classByName.getSimpleName();
        System.out.println("全限制类名："+name);
        System.out.println("类名："+simpleName);


        System.out.println("示例3： 获取类属性");
        Field[] declaredFields = classByName.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("类属性："+declaredField);
        }

        System.out.println("示例4：获取类属性具体值");
        person.setName("jianlin");
        person.setAge(18);
        for (Field declaredField : declaredFields) {
            //设置属性可见，从而可以得到一些私有属性的值
            declaredField.setAccessible(true);
            System.out.println("属性："+declaredField+",值："+declaredField.get(person));
        }

        System.out.println("示例5：反射实例化对象");
        Object object = classByName.newInstance();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            //判断属性名是否是name
            if(declaredField.getName().equals("name")){
                //设置name的属性值
                declaredField.set(object,"xiaohong");
            }
            if(declaredField.getName().equals("age")){
                //设置name的属性值
                declaredField.set(object,28);
            }
            System.out.println("属性："+declaredField+",值："+declaredField.get(object));
        }


        System.out.println("示例6：反射获取当前类的方法");
        Method[] methods = classByName.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName()+",");
        }
        System.out.println();
        Method getToMethod = classByName.getMethod("toString");
        Object value = getToMethod.invoke(object);
        System.out.println("toString方法返回值："+value);


        System.out.println("示例7： 获取注解");
        Study study = classByName.getAnnotation(Study.class);
        String[] mores = study.mores();
        String name1 = study.name();
        System.out.println("从注解获取的属性值："+name1+","+mores);




    }
}
