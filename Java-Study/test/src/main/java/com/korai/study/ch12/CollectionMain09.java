package main.java.com.korai.study.ch12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class CollectionMain09 {
    public static void main(String[] args) {

        @Data
        @AllArgsConstructor
        class Student {
            private String name;
            private String address;
        }

        List<Map<String, Map<String, Map<String, Object>>>> data = List.of(
                Map.of("class1", Map.of(
                        "student1", Map.of(
                                "id", 1,
                                "object", new Student("황지원", "서울"),
                                "createAt", LocalDate.of(2026,1,1)
                                )
                        ,
                        "student2", Map.of(
                                "id", 2,
                                "object", new Student("황지투", "서울"),
                                "createAt", LocalDate.of(2026,1,2)
                                )
                        ,
                        "student3", Map.of(
                                "id", 3,
                                "object", new Student("황지삼", "서울"),
                                "createAt", LocalDate.of(2026,1,3)
                        )
                )),
                Map.of("class2", Map.of(
                        "student1", Map.of(
                                "id", 1,
                                "object", new Student("황지원", "서울"),
                                "createAt", LocalDate.of(2026,1,4)
                        ),
                        "student2", Map.of(
                                "id", 2,
                                "object", new Student("황지투", "서울"),
                                "createAt", LocalDate.of(2026,1,5)
                        ),
                        "student3", Map.of(
                                "id", 3,
                                "object", new Student("황지삼", "서울"),
                                "createAt", LocalDate.of(2026,1,6)
                        )
                ))
        );

        //class2에 들어있는 student1의 이름 출력
        System.out.println(data);
        System.out.println(((Student) data.get(0).get("class1").get("student1").get("object")).getName());
        for(int i = 0; i< data.size(); i++) {
            Set<String> keySet = data.get(i).keySet();

            for(String key : keySet) {
                Map<String, Map<String, Object>> studentMap = data.get(i).get(key);
                Set<String> studentKeySet = studentMap.keySet();

                for(String studentKey : studentMap.keySet()) {
                    Student s = (Student) studentMap.get("object");
                    System.out.println(s.getName());
                }
            }
        }

        for (int i = 0; i < data.size(); i++){
            Set<Map.Entry<String, Map<String, Map<String, Object>>>> entries = data.get(i).entrySet();
            for(Map.Entry<String, Map<String, Map<String, Object>>> entry : entries){
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }

        Map<String, Integer> ages = Map.of(
                "황지원", 32,
                "황지투", 33,
                "황지삼", 34
        );

        Set<Map.Entry<String, Integer>> entries = ages.entrySet();
        for(Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
