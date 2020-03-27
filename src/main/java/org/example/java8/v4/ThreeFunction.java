package org.example.java8.v4;

/**
 * @Author wangp
 * @Date 2020/3/26
 * @Version 1.0
 */
@FunctionalInterface
public interface ThreeFunction<T,U,K,R> {
    R apply(T t,U u,K k);
}
