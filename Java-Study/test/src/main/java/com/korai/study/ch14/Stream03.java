package main.java.com.korai.study.ch14;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Stream03 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(5, 2, 10, 30, 22, 33, 50);
//        System.out.println(nums.stream().filter(n -> n % 5 == 0).toList()); //5의 배수를 담은 List를 출력하시오.
//        System.out.println(nums.stream().map(n -> n % 5 == 0 ? n * 1000 : n).toList());

        System.out.println(new CustomStream<>(nums).filter(n -> n % 5 == 0).toList());
        System.out.println(new CustomStream<>(nums).map(n -> n % 5 == 0 ? n * 1000 : n).toList());
    }
}

@Data
@AllArgsConstructor
class CustomStream<T> {
    private List<T> list;

    public CustomStream<T> map(Function<T, T> fx) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            newList.add(fx.apply(t));
        }
        this.list = newList;
        return this; //this를 쓰는 이유는 자기 주소의 값을 반환해서 계속해서 참조 걸 수 있다.
    }

    public CustomStream<T> filter(Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t))
                newList.add(t);
        }
        this.list = newList;
        return this;
    }

    public List<T> toList() {
        return list;
    }
}