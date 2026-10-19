package main.java.com.korai.study.ch12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

//Set에 대해서 공부 시작!
public class CollectionMain07 {
    public static void main(String[] args) {
        Set<Integer> nums = new TreeSet<>(); //Index = 순서대로 저장. Set = 같은 항목에서 중복되지 않게 선발.
        //예) 수학에서 1명, 국어에서 1명 처럼 Keyword 느낌 대부분은 String을 사용한다.
//        System.out.println(Objects.hash("ㄱ")); //한글 문자는 3byte씩 차지한다.
//        System.out.println(Objects.hash("ㄴ")); //hash값을 입력하면 데이터 정렬이 일어난다.
//        System.out.println(Objects.hash("ㄷ")); // hash를 통해 그 값이 같다 틀리다를 판별 할 수 있다.
//        System.out.println(Objects.hash("a")); //영어는 1byte씩 증가.
//        System.out.println(Objects.hash("b"));
//        System.out.println(Objects.hash("c"));

        nums.add(10);
        nums.add(20);
        nums.add(30);
        nums.add(40);
        nums.add(50);
        nums.add(60);
        nums.add(70);
        nums.add(80);
        nums.add(80);
        nums.add(80);
        nums.add(80);
        System.out.println(nums);
        //해싱 기법에 의한 데이터 정렬이 이루어져 index 순서대로 나오지 않는다.
        // TreeSet을 이용하면 인덱스 순서대로 나온다.
        // TreeSet은 기본값으로 오름차순 정렬이 되어있다.
        // 인터페이스의 특성을 그대로 가져와서 중복된 값은 저장되지 않는다.
        // 중복없이 사용되는 데이터를 사용하겠다. 싶을 땐 TreeSet을 사용한다.
        // 반면, HashSet은 키 값이 지정되어 있기 때문에 TreeSet보다 훨씬 빠르다.
        // 데이터를 많이 '입력' 해야 하는 경우에는 정렬이 좋지 않고
        // 데이터를 찾고 수정해야 하는 탐색의 경우에는 HashSet이 더 용이하다.

        System.out.println(nums.contains(80)); //80이라는 값이 있니? 있다면 true 없다면 false를 반환
        System.out.println(nums.containsAll(List.of(30,50,70))); // 벤다이어그램, 집합 연산 List에 해당하는 값들이 있니?

        List<String> names = List.of(
                "황지원",
                "황지투",
                "황지원",
                "황지투",
                "황지원"
        );

        Set<String> nameSet = new HashSet<>();
        nameSet.addAll(names);
        System.out.println(nameSet);

        @Data
        @AllArgsConstructor
        class Student {
            String name;
            String address;
        }

        List<Student> students = List.of(
                new Student("황지원", "부산"),
                new Student("황지원", "서울"),
                new Student("황지투", "부산"),
                new Student("황지투", "수도권"),
                new Student("황지삼", "수도권")
        );

        Set<String> addressSet = new HashSet<>();

        //for문을 돌려서 주소를 add해라.

        for(int i = 0; i < students.size(); i++){
            addressSet.add(students.get(i).getAddress());
        }
        System.out.println(addressSet);
        System.out.println(students.stream().map(s -> s.getAddress()).collect(Collectors.toSet()));

        for(String addr : addressSet){
            System.out.println(addr);
        }

        Iterator<String> addr = addressSet.iterator(); //생각외로 잘 안쓰이는 문법
        while (addr.hasNext()) {
            System.out.println(addr.next());
        }
    }
}
