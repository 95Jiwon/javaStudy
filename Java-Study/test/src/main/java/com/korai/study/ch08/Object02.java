package com.korai.study.ch08;

import java.util.Objects;

public class Object02 {
    public static void main(String[] args) {

        class Student {
            private String name;
            private int age;

            public Student(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public boolean equals(Object o) {
                if (o == null || this.getClass() != o.getClass()) return false;
                Student student = (Student) o;
//                return age == student.age && name.equals(student.name); // name.equlas에는 null이 없으므로 데이터상 안전하지 않을 수 있다.
                return age == student.age && Objects.equals(name, student.name);//뒤에 s가 붙으면 스태틱으로 만든 도구들이다.
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }

        Student student1 = new Student("황지원", 33);
        Student student2 = new Student("황지원", 33);
        Student student3 = student1;

        boolean result1 = student1.equals(student2);
        boolean result2 = student1.equals(student3);

        System.out.println(result1);
        System.out.println(result2);

        System.out.println(student1 == student2);
        System.out.println(student1 == student3);

        System.out.println(student1.hashCode() == student2.hashCode());
        System.out.println(student1.hashCode());
        System.out.println(Objects.hash("황지원", 33));
        System.out.println(Objects.hash( 33));
        System.out.println(Objects.hash( "황지원"));
    }
}