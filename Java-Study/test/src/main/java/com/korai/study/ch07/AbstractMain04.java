package com.korai.study.ch07;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

// 부모 클래스 (또는 추상 클래스)
abstract class Animal3 {
    abstract void move();
}

// 강아지 클래스
class Dog3 extends Animal3 {
    @Override
    void move() { System.out.println("강아지가 네 발로 뜁니다."); }

    void bark() { System.out.println("멍멍!"); }
}

class Tiger3 extends Animal3 {
    @Override
    void move() { System.out.println("호랑이가 빠르게 달립니다."); }

    void hunt() { System.out.println("사냥을 합니다!"); }
}

public class AbstractMain04 {
    public static void main(String[] args) {
//        Dog3 dog = new Dog3();
//        Tiger3 tiger = new Tiger3();
//        Animal3 animal = new Animal3();
//        Animal3 animal1 = dog;
//        Animal3 animal2 = tiger;
//
//        animal1.move();
//
//        Dog3 animal1ToDog = (Dog3) animal1;
//        animal1ToDog.bark();
//        ((Dog3) animal1).bark();
//
////        Dog3 animal2ToDog = (Dog3) animal2;
////        animal2ToDog.bark();
////        ((Dog3) animal2).bark();
//    }
    ArrayList<Animal3> animal3s = new ArrayList<>();

//    animal3s.add(new Dog3());
//    animal3s.add(new Tiger3());
//    animal3s.add(new Dog3());

    //Instanceof - ~~의 생성된 객체니? 비교연산자

    for(int i = 0; i < animal3s.size(); i++){
        Animal3 animal = animal3s.get(i);

        animal3s.get(i).move();

        if(animal3s.get(i) instanceof Dog3){
            Dog3 d = (Dog3) animal3s.get(i);
            d.bark();
        } else if (animal3s.get(i) instanceof Tiger3){
            Tiger3 t = (Tiger3) animal3s.get(i);
            t.hunt();
        }
    }
}

class Animal3 {
    String name;

    void move() {
        System.out.println("움직인다");
    }
}

class Dog3 extends Animal3 {
    @Override // 어노테이션
    void move() {
        System.out.println("많이 움직인다");
    }
    void bark() {
        System.out.println("짖다");
    }
}

class Tiger3 extends Animal3 {
    String name;

    void hunt(){
        System.out.println("사냥하다");
    }
}
}


