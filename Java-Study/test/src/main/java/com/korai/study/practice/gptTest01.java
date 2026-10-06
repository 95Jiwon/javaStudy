package com.korai.study.practice;

import java.util.Scanner;

public class gptTest01 {
    public static void main(String[] args) {
        // 짝수/홀수 판별기
        // 정수형 변수 하나를 선언하고, 그 숫자가 짝수인지 홀수인지 판별하여 출력하는 프로그램
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        if( a % 2 == 0){
                    System.out.println("짝수입니다.");
                }else{
                    System.out.println("홀수 입니다.");
                }
    }
}
