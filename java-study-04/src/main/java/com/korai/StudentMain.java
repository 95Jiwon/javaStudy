package com.korai;

public class StudentMain {

    public static void main(String[] args) {
        int num = 10;
        Student jun = new Student(); // 새로운 Student라고 하는 자료가 생성된다.
        Student jun2 = new Student();
        jun.name = "황지원"; // 선언을 한 변수명에 . 을 찍으면 참조를 한다.
        jun.age = 33;
        jun.gender = "man";
        jun.address = "부산";

        Student2 jun3 = new Student2();
        jun3.age = "23";
        Student2 jun4 = new Student2();
        jun4. age = 33;
        System.out.println((String)jun3.age + (Integer)jun4. age); //jun3과 jun4의
        // 함수가 오브젝트여서 어디든 사용 가능하지만 연산을 위해서는 다운캐스팅이 필요하다.

        Student3<String, String> jun5 = new Student3<>();
        jun5.age = "33";
        Student3<Integer, String> jun6 = new Student3<>();
        jun6.age = 33;
        System.out.println(jun5.age + jun6.age);

    }
}
