package com.korai.study.practice;

import java.util.Scanner;

public class gptTest02 {
    public static void main(String[] args) {
        //구구단 출력하기
        //정수를 입력받아 해당 정수의 구구단을 구하는 반복문
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        for (int i = 1; i <= 9; i++) {
            int result = a * i;
            System.out.println(a + " x " + i + " = " + result);
        }

        sc.close();
    }
}
