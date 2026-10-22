package main.java.com.korai.study.ch13;

//익명 클래스            -> 1회용 틀
//클래스의 이름이 없음.
public class AnonymousClass01 {
    //오브젝트 클래스를 상속받은, 이름없는 클래스를 정의와 동시에 생성
    Object obj = new Object() {};

    class A extends Object {} //Object를 상속받은 A 생성
    Object obj2 = new A();

    class B {} //이름없는 B 클래스 생성
    Object obj3 = new B();

    class C {}
    Object obj4 = new C() {}; // C클래스를 상속 받아서 정의한 익명의 클래스

    abstract class D extends C {}
    D obj5 = new D() {}; //

    interface E {}
    E obj6 = new E() {};
}
