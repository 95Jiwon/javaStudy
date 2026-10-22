package main.java.com.korai.study.ch13;


//https://bangu4.tistory.com/215
//내장형 람다 인터페이스

import java.util.function.*;

public class Lambda03 {
    public static void main(String[] args) {
        Runnable runnable = () -> {};
        Supplier<Integer> supplier = () -> 100; //호출 될 때 마다 값을 돌려준다.
        Consumer<String> consumer = str -> {}; //Consumer는 매개변수가 존재, 값을 받기만 하는 문법 그래서 리턴이 없다.
        Function<Integer, String> function = num -> Integer.toString(num); //function 함수이기에 매개변수와 리턴이 둘 다 존재한다.
        Predicate<Integer> predicate = num -> num % 2 == 0; //boolean true, false를 구하는 조건식

        runnable.run();
        int r1 = supplier.get();
        consumer.accept("");
        String r2 = function.apply(10);
        boolean r3 = predicate.test(20);

        BiConsumer<String, Integer> consumer2 = (str, num) -> {}; //매개변수에 타입을 따로 받을 수 있다.
        BiFunction<Integer, Integer, String> function2 = (num1, num2) -> Integer.toString(num1 + num2); //
        BiPredicate<Integer, Integer> predicate2 = (num1, num2) -> num1 % num2 == 0; //
        predicate2.and((num1, num2) -> num1 > num2).test(10, 2);

    }
}
