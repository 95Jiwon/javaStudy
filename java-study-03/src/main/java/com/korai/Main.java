package com.korai;

import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        //1번 문제: 짝수와 홀수 판별하기 (if 문)
        //학습 목표: 산술 연산자(%)와 조건문(if-else) 활용하기
        //
        //문제 설명:
        //정수형 변수 number를 하나 선언하고 임의의 숫자를 넣습니다. 이 숫자가 짝수인지 홀수인지 판별하여 콘솔에 출력하는 프로그램을 작성하세요.
        //
        //출력 예시 (숫자가 10일 때):
        //
        //Plaintext
        //  10은(는) 짝수입니다.

        int number = 10;

        if(number % 2 == 0){
            System.out.println("짝수입니다.");
        } else if (number % 2 != 0) {
            System.out.println("홀수입니다");
        }

        //2번 문제: 합격 여부 판별하기 (비교 연산자와 논리 연산자)
        //학습 목표: 비교 연산자(>=)와 논리 연산자(&&) 활용하기
        //
        //문제 설명:
        //필기 점수(writtenScore)와 실기 점수(practicalScore)를 저장할 변수 두 개를 만듭니다.
        // 두 점수가 모두 80점 이상이어야 합격입니다.
        // 조건에 맞으면 "합격", 아니면 "불합격"을 출력하는 프로그램을 작성하세요.
        //
        //힌트: 논리 연산자 &&(AND)를 사용해 보세요.

        int writtenScore = 81;
        int practicalScore = 90;

        if(writtenScore >= 80 && practicalScore >= 80){
            System.out.println("합격입니다.");
        }
        else {
            System.out.println("불합격입니다.");
        }

        //3번 문제: 로그인 성공 여부 (문자열 비교)
        //학습 목표: 문자열(String)의 비교 방법(equals) 이해하기
        //
        //문제 설명:
        //시스템에 미리 등록된 아이디는 "admin", 비밀번호는 "1234"입니다. 사용자가 입력한 아이디(inputId)와 비밀번호(inputPw)가 모두 일치하면 "로그인 성공", 하나라도 틀리면 "로그인 실패"를 출력하는 프로그램을 작성하세요.
        //
        //주의점: 자바에서 문자열을 비교할 때는 == 대신 equals() 메서드를 사용해야 합니다.
        //
        //예시: inputId.equals("admin")

        String inputId = "admin";
        String inputPass = "1234";

        Scanner sc = new Scanner(System.in);
        System.out.println("ID를 입력하세요.");
        String userId = sc.next();
        System.out.println("PassWord를 입력하세요.");
        String userPass = sc.next();

        if(userId.equals(inputId) && userPass.equals(inputPass)) {
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패");
        }
    }
}