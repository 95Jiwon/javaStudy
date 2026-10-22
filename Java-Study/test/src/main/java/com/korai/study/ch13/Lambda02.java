package main.java.com.korai.study.ch13;

public class Lambda02 {
    public static void main(String[] args) {
        Calculator01 calculator1 = () -> { //명령이 둘이면 중괄호 필수
            System.out.println("명령 하나");
            System.out.println("명령 둘");
        };

        Calculator01 calculator01_1 = () -> System.out.println("명령하나"); //명령이 하나면 중괄호 생략 가능

        Calculator02 calculator02 = () -> {
            System.out.println("명령 하나");
            System.out.println("명령 둘");
            return 0;
        };

        Calculator02 calculator02_1 = () -> 0; // 리턴만 존재하면 중괄호 생략하고 리턴 값만 적는다.

        Calculator03 calculator03 = (int num) -> {
            return 0;
        };

        //자료형 생략 가능
        Calculator03 calculator03_1 = (num) -> {
            return 0;
        };

        //매개변수가 하나면 괄호 생략 가능
        Calculator03 calculator03_2 = num -> {
            return 0;
        };

        Calculator03 calculator03_3 = num -> num * 10; // 자료형과 리턴 값은 바꿀 수 있다.

        Calculator03 calculator03_4 = a -> a * 10; //매개변수 명을 바꿀 수 있다.

        Calculator04 calculator04 = (int num1, int num2) -> {
            return num1 + num2;
        };

        Calculator04 calculator04_1 = (num1, num2) -> { //매개변수에 자료형을 생략 할 수 있다.
            return num1 + num2;
        };

        Calculator04 calculator04_2 = (data1, data2) -> data1 + data2; // 매개변수 명을 변경 할 수 있고 리턴을 생략 할 수 있다.
        //단, 리턴 값은 -> 뒤에 붙여줄 것!
    }
}

@FunctionalInterface
interface Calculator01 {
    void calc();
}

@FunctionalInterface
interface Calculator02 {
    int calc();
}

@FunctionalInterface
interface Calculator03 {
    int calc(int num);
}

@FunctionalInterface
interface Calculator04 {
    int calc(int num1, int num2);
}