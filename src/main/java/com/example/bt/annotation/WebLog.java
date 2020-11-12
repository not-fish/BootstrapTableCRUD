package com.example.bt.annotation;

import java.lang.annotation.*;

/**
 * @author Peko.Lai
 * 作用：controller层的打印日志注解
 * @Target (注解目标):方法
 * @Retention (生命周期):该注解会保留到class文件中，并被JVM读取
 * @Documented :该注解可被抽取到api文档
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {}
