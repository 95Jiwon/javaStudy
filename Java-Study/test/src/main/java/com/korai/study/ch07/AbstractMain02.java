package com.korai.study.ch07;

public class AbstractMain02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Tiger tiger = new Tiger();
        Animal animal = new Animal();
        Animal animal1 = dog;
        Animal animal2 = tiger;


    }
}

class Animal {
    String name;

    void move() {
        System.out.println("움직인다");
    }
}

class Dog extends Animal {
    String name;

    void bark() {
        System.out.println("짖다");
    }
}

class Tiger extends Animal {
    String name;

    void hunt(){
        System.out.println("사냥하다");
    }
}


