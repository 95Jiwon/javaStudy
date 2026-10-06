package com.korai.study.ch10;

public class SingletonMain {
    public static void main(String[] args) {
    StudentService studentService = StudentService.getInstance();
    StudentService studentService2 = StudentService.getInstance();
    }
}

class StudentService {
    private static StudentService instance;
    private StudentService() {} //생성자를 감추어서 외부에서 접근을 하여야 생성이 가능하다.

        public static StudentService getInstance() {
        if(instance == null) {
            instance = new StudentService();
        }
            return instance;
        }

}