package com.korai.study.ch05.practice;

import java.util.Scanner;

public class ScannerMain01 {
    public static void main(String[] args) {
        new Scanner(System.in).nextLine();
        String a = new Scanner(System.in).nextLine();
        Scanner scanner = new Scanner(System.in);
        String b = scanner.nextLine();
        //new라고 하면 Scanner라고 하는 클래스를 생성자를 호출 하겠다.
        //객체라고 하는 것은 2가지를 가질 수 있다. 첫 번째는 데이터, 두 번째는 기능을 가질 수 있다.
        //따라 위 코드는 Scanner 클래스 안의 입력받는 nextLine을 활용하여
        //한 번의 입력을 받는 프로그램을 결과를 도출한다.
        //프로그램은 . . .을 이용해서 가장 마지막에 있는 글의 리턴을 사용한다.

        String age = "33";
        int age2 = Integer.parseInt(age); //parseInt를 사용하게 되면 String age "33" 즉,
        // 문자열 33을 정수형 33으로 변환하는걸 얘기하게 된다.



    }
}
