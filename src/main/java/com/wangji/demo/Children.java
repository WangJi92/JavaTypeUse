package com.wangji.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 测试泛型(理解了之前的那几个数据信息，是不是非常好理解处理啦)
 *
 * @author: wangji
 * @date: 2018/06/25 20:34
 */
@Slf4j
public class Children extends Parent<String> implements IParent<Long> {

    public static void main(String[] args) {
        /**
         * 这里是获取父类中泛型，如果有多个也是一样的方式哈哈！获取到的泛型参数还可能是 通配符表达式，这里也是可以处理的，多个判断而已
         */
        Type genericSuperclassType = Children.class.getGenericSuperclass();
        if (genericSuperclassType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclassType).getActualTypeArguments();
            for (Type argumentType : actualTypeArguments) {
                log.info("父类ParameterizedType.getActualTypeArguments:" + argumentType);
            }
        }
        /**
         * 这里获取父接口中的泛型参数
         */
        Type[] genericInterfacesTypes = Children.class.getGenericInterfaces();
        for (Type interfaceType : genericInterfacesTypes) {
            if (interfaceType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) interfaceType).getActualTypeArguments();
                for (Type argumentType : actualTypeArguments) {
                    log.info("父接口ParameterizedType.getActualTypeArguments:" + argumentType);
                }
            }
        }

    }
}
//2018-06-25 20:45:09,302  INFO [Children.java:25] : 父类ParameterizedType.getActualTypeArguments:class java.lang.String
//2018-06-25 20:45:09,304  INFO [Children.java:36] : 父接口ParameterizedType.getActualTypeArguments:class java.lang.Long