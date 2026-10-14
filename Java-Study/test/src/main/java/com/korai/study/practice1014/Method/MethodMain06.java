package main.java.com.korai.study.practice1014.Method;
// Counter 클래스를 만드세요. Increment()는 증가, reset()은 초기화, getCount()는 현재 값을 돌려준다.
// 카운터 두 개가 서로 영향을 주지않게 생성
public class MethodMain06 {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        c1.Increment(); c1.Increment(); c1.Increment();
        c2.Increment(); c2.reset();

        System.out.println(c1.getCount());
        System.out.println(c2.getCount());
    }
}

class Counter {
    private int count = 0;
    public void Increment(){
        count++;
    }

    public void reset(){
        count = 0;
    }

    public int getCount(){
        return count;
    }
}
