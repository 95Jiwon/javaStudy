package main.java.com.korai.study.testCoding;

/*
1. 간단한 '가계부' 프로그램 (월급/용돈 관리)
사용자가 가진 초기 금액에서 지출이나 수입을 기록하고, 잔액을 계산해 주는 프로그램입니다.

핵심 학습 개념: 변수, 조건문(if), 반복문(while/for), 배열 (지출 내역이나 금액을 여러 개 저장)

기능 구현 아이디어:

초기 자산(월급 또는 용돈)을 입력받습니다.

메뉴 선택: 1. 지출 기록하기, 2. 내역 전체보기, 3. 잔액 확인, 4. 종료

지출 기록을 선택하면 금액과 내용(예: "점심값 9,000원")을 입력받아 배열에 저장합니다.

'전역 내역 보기'를 누르면 배열에 저장된 모든 지출 기록을 차례대로 출력해 줍니다.
 */
public class budgetBookMain {
    public static void main(String[] args) {
        int money = 50000;
        System.out.println("[ 1. 지출 기록하기, 2. 내역 전체보기, 3. 잔액 확인, 4. 종료 ]");
    }
}

class budgetBook {
    int money = 0;
    String content = "";
}
