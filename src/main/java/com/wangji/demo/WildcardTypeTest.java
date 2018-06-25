package com.wangji.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.List;

/**
 * WildcardType represents a wildcard type expression, such as ?, ? extends Number, or ? super Integer.
 * 通配符表达式，或泛型表达式，它虽然是Type的一个子接口，但并不是Java类型中的一种，表示的仅仅是类似 ? extends T、? super K这样的通配符表达式。
 * ？---通配符表达式，表示通配符泛型，但是WildcardType并不属于Java-Type中的一钟；例如：List<? extends Number> 和 List<? super Integer>；
 * 1、Type[] getUpperBounds();  //获得泛型表达式上界（上限） 获取泛型变量的上边界（extends）
 * 2、Type[] getLowerBounds(); //获得泛型表达式下界（下限） 获取泛型变量的下边界（super）
 *
 * @author: wangji
 * @date: 2018/06/25 19:47
 */
@Slf4j
public class WildcardTypeTest {
    /**
     * 1、 a: 获取ParameterizedType:? extends java.lang.Number
     * 2、上界：class java.lang.Number
     */
    private List<? extends Number> a;

    /**
     * b: 获取ParameterizedType:? super java.lang.String
     *  上届：class java.lang.Object
     *  下届：class java.lang.String
     */
    private List<? super String> b;

    /**
     * c: 获取ParameterizedType:class java.lang.String
     */
    private List<String> c;

    /**
     * aClass: 获取ParameterizedType:?
     * 上届：class java.lang.Object
     */
    private Class<?> aClass;

    private String wangji;

    /**
     * 多种数据进行混合
     */
    public static void testWildcardType() {
        Field f = null;
        try {
            Field[] fields = WildcardTypeTest.class.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                if (f.getName().equals("log")) {
                    continue;
                }
                log.info("begin ******当前field:" + f.getName() + " *************************");
                if (f.getGenericType() instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) f.getGenericType();
                    for (Type type : parameterizedType.getActualTypeArguments()) {
                        log.info(f.getName() + ": 获取ParameterizedType:" + type);
                        if (type instanceof WildcardType) {
                            printWildcardType((WildcardType) type);
                        }
                    }
                } else if (f.getGenericType() instanceof GenericArrayType) {
                    GenericArrayType genericArrayType = (GenericArrayType) f.getGenericType();
                    log.info("GenericArrayType type :" + genericArrayType);
                    Type genericComponentType = genericArrayType.getGenericComponentType();
                    if (genericComponentType instanceof WildcardType) {
                        printWildcardType((WildcardType) genericComponentType);
                    }
                } else if (f.getGenericType() instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) f.getGenericType();
                    log.info("typeVariable:" + typeVariable);

                } else {
                    log.info("type :" + f.getGenericType());
                    if (f.getGenericType() instanceof WildcardType) {
                        printWildcardType((WildcardType) f.getGenericType());
                    }
                }
                log.info("end ******当前field:" + f.getName() + " *************************");
            }
        } catch (Exception e) {
            log.error("error", e);
        }
    }

    private static void printWildcardType(WildcardType wildcardType) {
        for (Type type : wildcardType.getUpperBounds()) {
            log.info("上界：" + type);
        }
        for (Type type : wildcardType.getLowerBounds()) {
            log.info("下界：" + type);
        }
    }

    public static void main(String[] args) {
        testWildcardType();
    }
}

//2018-06-25 20:13:52,294  INFO [WildcardTypeTest.java:40] : begin ******当前field:a *************************
//2018-06-25 20:13:52,297  INFO [WildcardTypeTest.java:44] : a: 获取ParameterizedType:? extends java.lang.Number
//2018-06-25 20:13:52,299  INFO [WildcardTypeTest.java:75] : 上界：class java.lang.Number
//2018-06-25 20:13:52,299  INFO [WildcardTypeTest.java:66] : end ******当前field:a *************************
//        
//2018-06-25 20:13:52,300  INFO [WildcardTypeTest.java:40] : begin ******当前field:b *************************
//2018-06-25 20:13:52,300  INFO [WildcardTypeTest.java:44] : b: 获取ParameterizedType:? super java.lang.String
//2018-06-25 20:13:52,300  INFO [WildcardTypeTest.java:75] : 上界：class java.lang.Object
//2018-06-25 20:13:52,300  INFO [WildcardTypeTest.java:78] : 下界：class java.lang.String
//2018-06-25 20:13:52,300  INFO [WildcardTypeTest.java:66] : end ******当前field:b *************************
//        
//2018-06-25 20:13:52,300  INFO [WildcardTypeTest.java:40] : begin ******当前field:c *************************
//2018-06-25 20:13:52,300  INFO [WildcardTypeTest.java:44] : c: 获取ParameterizedType:class java.lang.String
//2018-06-25 20:13:52,301  INFO [WildcardTypeTest.java:66] : end ******当前field:c *************************
//        
//2018-06-25 20:13:52,301  INFO [WildcardTypeTest.java:40] : begin ******当前field:aClass *************************
//2018-06-25 20:13:52,301  INFO [WildcardTypeTest.java:44] : aClass: 获取ParameterizedType:?
//2018-06-25 20:13:52,301  INFO [WildcardTypeTest.java:75] : 上界：class java.lang.Object
//2018-06-25 20:13:52,301  INFO [WildcardTypeTest.java:66] : end ******当前field:aClass *************************
//        
//2018-06-25 20:13:52,302  INFO [WildcardTypeTest.java:40] : begin ******当前field:wangji *************************
//2018-06-25 20:13:52,302  INFO [WildcardTypeTest.java:61] : type :class java.lang.String
//2018-06-25 20:13:52,302  INFO [WildcardTypeTest.java:66] : end ******当前field:wangji *************************
