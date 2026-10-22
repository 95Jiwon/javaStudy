package main.java.com.korai.study.ch13;

import java.sql.SQLOutput;

// 람다
// 추상 메서드를 하나만 가지고 있는 인터페이스를 익명객체로 생성할 때
// 편리하게 생성할 수 있도록 만들어진 문법
public class Lambda01 {
    public static void main(String[] args) {
        KeyboardEvent k1 = new KeyboardEvent() {
            @Override
            public void keyUp() {
                System.out.println("키를 떼다.");
            }
            @Override
            public void keyDown() {
                System.out.println("키를 누르다.");
            }
        };

        k1.keyUp();
        k1.keyDown();

        Speaker speaker = () -> System.out.println("A사운드를 재생"); // 1. interface안의 추상 메서드가 딱 1개 있어야 한다.

        speaker.play();
    }


}

interface KeyboardEvent {
    void keyUp();
    void keyDown();
}

interface Speaker {
    void play();
}