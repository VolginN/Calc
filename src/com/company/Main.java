package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str= in.nextLine();
        Calculator calc = new Calculator();
        System.out.println(calc.calculate(str));
    }
}