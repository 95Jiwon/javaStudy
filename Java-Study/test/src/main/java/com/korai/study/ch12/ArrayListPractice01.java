package main.java.com.korai.study.ch12;

import java.util.*;

public class ArrayListPractice01 {
    public static void main(String[] args) {
        // 1. ArrayList를 사용하여 5명의 학생 점수를 저장하고, 평균과 최고 점수를 구하시오.
        // 2. ArrayList에서 중복 요소를 제거한 새 리스트를 만드시오.
        // 3. ArrayList에 1~20 정수를 저장한 뒤, 3의 배수만 역순으로 출력하시오.
        // 4. 두 리스트 병합 정렬

        StudentScore.run();
        Language.run();
        ReverseArray.run();
        Marge.run();
        Fruit.run();
    }
}

class StudentScore {
    /*
        1. 점수 : 85, 92, 78, 96, 88
        출력 :
        전체 점수 : [85, 92, 78, 96, 88]
        평균 : 87.8
        최고 점수 96
    */
    static void run(){
    ArrayList<Integer> stuList = new ArrayList<>();
        stuList.addAll(List.of(85, 92, 78, 96, 88));
        System.out.println("전체 점수 : " + stuList);

        double avg = 0;
        int max = 0;

        for(int i = 0; i < stuList.size(); i++){
                avg += stuList.get(i);
                System.out.println(stuList.get(i)); //평균을 구할 값을 구하는 식
                if(max < stuList.get(i)){
                    max = stuList.get(i); // 최고 점수를 구하는 식
                }
        }
        double result = avg / stuList.size();

        System.out.println("평균 : " + result);
        System.out.println("최고 점수 : " + max);
    }
}

class Language {
    static void run(){
        List<String> list = List.of("Java", "Python", "Java", "C++", "Python", "Go");
        List<String> newList = new ArrayList<>(new LinkedHashSet<>(list));

        System.out.println(list);
        System.out.println(newList);
    }
}

class ReverseArray {
    //출력: [18, 15, 12, 9, 6, 3]
    static void run() {
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            nums.add(i);
        }

        for(int i = nums.size() -1; i >= 0; i--){
            if(nums.get(i) % 3 != 0){
                nums.remove(i);
            }
        }

        Collections.reverse(nums);
        System.out.println(nums);
    }
}

class Marge {
    //listA : [5, 2, 8]
    //listB : [3, 9, 1]
    //결과 : [1, 2, 3, 5, 8, 9]
    static void run() {

        ArrayList<Integer> marge_a = new ArrayList<>();
        ArrayList<Integer> marge_b = new ArrayList<>();
        marge_a.addAll(List.of(5, 2, 8)); //marge_a에 5, 2, 8 대입
        marge_b.addAll(List.of(3, 9, 1)); //marge_b에 3, 9, 1 대입

        ArrayList<Integer> merged = new ArrayList<>(marge_a); //merged에 marge_a의 값을 생성하여 전부 추가한다.
        merged.addAll(marge_b); //Integer 가변형 배열 marge에 marge_b의 값을 전부 추가한다.
        // 이로 인해서 merged에는 marge_a의 값과 marge_b의 값이 전부 들어가게 된다.

        Collections.sort(merged); //Collections에서 수를 정렬하는 기능을 한다. sort() ()안에 정렬할 값을 넣어주면 자동정렬
        System.out.println(merged);
    }
}

class Fruit {
    //초기 : ["사과", "바나나", "포도", "딸기"]
    //"포도"를 "망고"로 변경
    //"체리"를 맨 앞에 추가
    //"딸기"를 삭제
    //최종: ["체리", "사과", "바나나", "망고"
    static void run() {
        List<String> fruit = new ArrayList<>(List.of("사과", "바나나", "포도", "딸기"));
        fruit.set(fruit.indexOf("포도"), "망고"); //set을 활용하면 순번을 지정해서 변경이 가능한데
        //순번이 아닌 문자열을 비교해서 바꾸는 식을 짜고 싶은 상황... 어떻게 하지?
        fruit.addFirst("체리");
        fruit.remove("딸기");
        System.out.println(fruit);
    }
}

