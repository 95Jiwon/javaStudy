package com.korai.study.ch05.practice;

import java.util.Scanner;

public class ScannerMain02 {
    // 이름 next
    // 주소 next
    // 연락처 nextLine
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        String address = sc.next(); // next에서는 띄어쓰기와 \n(줄바꿈)을 무시하는데
        sc.nextLine();
        String number = sc.nextLine(); //nextLine에서는 띄어쓰기와 줄바꿈을 포함하여 처리하여 마지막 줄에는 줄바꿈이 들어가게 된다. 이것을 해결할려면?


    System.out.println("이름을 입력 하세요 : " + name);
    System.out.println("주소를 입력 하세요 : " + address);
    System.out.println("연락처를 입력 하세요 : " + number);

    sc.close();}
}
