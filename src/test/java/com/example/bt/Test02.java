package com.example.bt;

/**
 * @author Peko
 */
public class Test02 {
    public static void main(String[] args) {
        function1();
        function2(1);
        function3();
    }

    private static void function3() {
        System.out.println("this is function3");
    }

    private static void function2(int i) {
        if(i == 1){
            System.exit(0);
        }
        System.out.println("this is function2");
    }

    private static void function1() {
        System.out.println("this is function1");
    }
}
