package com.korai.study.ch08;

public class Object01 {
    // 최상위 클래스 (Object)
    public static void main(String[] args) {
        System.out.println(Student.class.getName());
        System.out.println(new Student().getClass().getName());
        System.out.println(new Student() instanceof Student);
        System.out.println(new Student().getClass() == Student.class);
        System.out.println(new Student().hashCode());
        Student s = new Student();
        System.out.println(s.hashCode());
        System.out.println(Integer.toHexString(s.hashCode()));
        System.out.println(s.toString());
        String str1 = s.toString();
        Student s2 = s;
//        HighStudent hs1 = new HighStudent();
//        System.out.println(hs1);
    }
}

class Student extends Object {
    //main의 구문과 Student안의 내용을 봤을 때 extends Object가 생략되어 있다.
//}
//
//class HighStudent extends Student{
//    @Override
//    public String toString() {
//            return "내 마음대로 재정의 가능";
//    }
//}

    @Override
    public String toString() {
        return "객체가 가지고 있는 데이터를 문자열로 시각화 할 때 사용";
    }
}

class Teacher {
    private String name;
    private int age;
    private String adress;

    public Teacher(String name, int age, String adress) {
        this.name = name;
        this.age = age;
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", adress='" + adress + '\'' +
                '}';
    }
} //생성자와 toString 만들기 익혀두기!


