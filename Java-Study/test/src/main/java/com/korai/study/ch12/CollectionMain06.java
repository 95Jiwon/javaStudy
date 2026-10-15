package main.java.com.korai.study.ch12;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CollectionMain06 {
    public static void main(String[] args) {
        A1.run();
        A2.run();
        A3.run();
        A4.run();
    }
}

class A1 {
    static void run() {
        ArrayList<String> strList = new ArrayList<>();
        //값 추가
        strList.add("데이터1");
        // 값 여러 개 한 번에 추가
        strList.addAll(List.of("데이터2", "데이터3", "데이터4"));
        strList.addFirst("데이터5");
        strList.addLast("데이터6");
        strList.add(2, "데이터7");

        System.out.println(strList);
    }
}

class A2 {
    static void run() {
        ArrayList<String> strList = new ArrayList<>();
        strList.addAll(List.of("데이터1,", "데이터2", "데이터3", "데이터4"));
        boolean isRemoved= strList.remove("데이터2");
        String removedObject = strList.remove(2);
        strList.addAll(List.of("데이터5,", "데이터6", "데이터7", "데이터8"));
        strList.removeAll(List.of("데이터6", "데이터7"));
        System.out.println(strList );
//        strList.removeIf(d -> d.contains("3") || d.contains("5"));
        strList.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("3") || s.contains("5");
            }
        });
//        strList.removeIf(stringPredicate);

        System.out.println(strList);
    }
}

class A3 {
    static void run() {
        List<String> strList = List.of("데이터1", "데이터2", "데이터3");
        String data = strList.get(2);
        System.out.println(data);
    }
}

class A4 {
    static void run() {
        List<String> strList = List.of("데이터1", "데이터2", "데이터3");
        boolean isContained =  strList.contains("데이터5");
        System.out.println(isContained);
        boolean isContained2 = strList.get(1).contains("2");
        System.out.println(isContained2);
    }
}