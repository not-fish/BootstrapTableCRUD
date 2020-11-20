package com.example.bt;

/**
 * @author Peko
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        test02("jpg");
    }

    public static void test03(){

    }

    public static void test02(String ext) throws InterruptedException {
        if ("jpg,jpeg,bmp,png,tif,gif,tga,bmp,psd".toUpperCase().contains(ext.toUpperCase())) {
            System.out.println("是图片"+ext.toUpperCase());
        }else {
            System.out.println("不是图片");
        }
    }

}
