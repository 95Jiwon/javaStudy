package com.korai;

class Animal {
    void 먹다(){

    }
}

class Dog extends Animal{
    void 뛴다(){
        //dog라는 클래스는 Animal을 상속 받아 먹다에 뛴다가 추가된 것이다.
    }
}

public class Main2 {
    public static void main(String[] args) {
        // 숫자
        char tt = 'a';
        int ttt = tt;

        double a = 10.12;
        int b = (int) a;
        char c = (char) b; // char에 문자로 변환하는 기능을 부여해라 -> (char) b;

        Animal a2 = new Animal(); // new Animal() = Animal이라는 자료를 새로 만들겠다.
        a2.먹다(); // Animal을 불러왔을땐 Dog의 뛴다가 없지만
        Dog a1 = new Dog();
        a1.뛴다(); // Dog를 불러왔을땐 뚠다가 있다.
        //업캐스팅 -> 기존의 함수에서 상위 함수(추가 기능X)로 변환한다.
        //다운캐스팅 -> 기존의 함수에서 하위 함수(추가 기능O)로 변환한다.

        Animal c2 = new Animal();
        Dog d2 = (dog) c2; // 원래 존재 했던 것에서 변경되는건 안된다.

        //동적 메모리 할당 = 사용 메모리를 고정 시켜버리면  새로운 데이터를 받아 들일 수 없어
        // 작업에 차이가 생긴다. * 계속해서 추가 할 수 있는 장점이 있다.
    }
}
