package com.korai.study.ch07;

import java.util.ArrayList;
import java.util.List;

public class AbstractMain03 {
    public static void main(String[] args) {
        Dog2 dog = new Dog2();
        Tiger2 tiger = new Tiger2();
        Animal2 animal = new Animal2();
        Animal2 animal1 = dog;
        Animal2 animal2 = tiger;

        animal1.move();
        animal2.move();
        List<Animal2> animals = new ArrayList<>();
    }
}

class Animal2 {
    String name;

    void move() {
        System.out.println("움직인다");
    }
}

class Dog2 extends Animal2 {
    @Override // 어노테이션
    void move() {
        System.out.println("많이 움직인다");
    }
    void bark() {
        System.out.println("짖다");
    }
}

class Tiger2 extends Animal2 {
    String name;

    void hunt(){
        System.out.println("사냥하다");
    }
}


