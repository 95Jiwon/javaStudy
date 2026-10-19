package main.java.com.korai.study.ch12;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CollectionMain08 {
    public static void main(String[] args) {
        Map<String, String> productMap = new LinkedHashMap<>(); // String에 String을 자주 쓴다.
        productMap.put("productName", "상품1"); //Map은 add가 아니라 put을 이용한다.
        productMap.put("price", "20000");
        productMap.put("createdAt", "2026-10-19");
        System.out.println(productMap);
        System.out.println(productMap.get("price"));

        Map<String, Object> student = new LinkedHashMap<>(); //키 값을 이용하여 정렬
        student.put("name", "황지원");
        student.put("age", 32);
        student.put("gender", true);
        System.out.println(student);
        if((boolean) student.get("gender")) {
            System.out.println("남성");
        }else {
            System.out.println("여성");
        }
    }
}
