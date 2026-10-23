package main.java.com.korai.study.ch14;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Stream01 {
    public static void main(String[] args) {
        // 반복작업을 하나의 라이브러리로 구조화 해둔 것
        Stream<Integer> numStream = Stream.of(10, 20, 30, 40, 50);
        System.out.println(numStream);
        System.out.println(numStream.toList()); //Map은 mapping되어지는 값을 치환 해주는 것.
        //toList()를 만나면 스트림은 값을 꺼내어 썼기 때문에 스트림에는 값이 없다.
//        System.out.println(numStream.map(num -> num * 10).toList());

        Integer[] numArray = {10, 20, 30, 40, 50};

        System.out.println(Arrays.stream(numArray).map(num -> {if (num % 4 == 0) { return num * 10;} return num;}).toList()); //Stream 아니면 Map을 사용할 수 없다.

        Integer[] newNumArray = new Integer[5];
        for(int i = 0; i < numArray.length; i++){
            newNumArray[i] = numArray[i] * 10;
        }
        numArray = newNumArray;
        System.out.println(Arrays.toString(numArray));

        Function<Integer, Integer> fx = (num) -> num * 10; //fx = (num) -> num * 10 주소값. / 인스턴스 = 생성된 객체 / class + implements + override + new한게 해당 코드
        print(fx);

        print(num -> num * 20);

        List<String> names = List.of("황지원", "황지투", "황지삼");
        Stream<String> namesStream = names.stream();                        // 생성
        Stream<String> namesStream2 = namesStream.map(n -> n + "님");       // 중간연산
        Stream<String> namesStream3 = namesStream2.map(n -> "이름: " + n);  // 중간연산
        Stream<String> namesStream4 = namesStream3.filter(name -> !name.contains("원")); //contains = 포함하고 있으면
                                                                            //Stream으로 리턴되는 것들은 전부 중간연산이며 Set으로 리턴되는 것은 최종연산이다.
        List<String> newNames = namesStream4.toList();                     // 최종연산
        System.out.println(newNames);

        System.out.println(names.stream()
                .map(n -> n + "님")
                .map(n -> "이름: "  + n)
                .filter(name -> !name.contains("투"))
                .toList()
        );
    }

    static void print(Function<Integer, Integer> a) {
        System.out.println(a.apply(5));
    }
}
