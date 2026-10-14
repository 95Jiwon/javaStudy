package main.java.com.korai.study.ch04;

import lombok.*;

@Data // get, set, eq, hc, toStr
public class Author {
    private String name;
    private String birthday;

    // @NoArgsConstructor
    public Author() {

    }

    // @AllArgsContructor
    public Author(String name, String brithday){
        this.name = name;
        this.birthday = brithday;
    }

    @Data
    @RequiredArgsConstructor // final을 붙이게 만든다, Publisher를 생성할 때는 무조건 name이 있어야 한다.
    @AllArgsConstructor
    class Publisher {
        private final String name;
        private final String address;
        private String address2;
    }
}


class AuthorMain {
    public static void main(String[] args) {
        Author a = new Author();
        Author.Publisher p = a.new Publisher("한빛미디어", "부산", "북구");
        // final이 있는 함수는 무조건 호출(RequiaredArgsConstructor),
        // 모든 함수를 불러오면(AllArgsConstructor)입니다.
        // 아무 매개변수도 없다면 NoArgsConstructor이다.
        class Animal {

        }

        class Dog extends Animal {

        }

        Dog d = new Dog();
        System.out.println(d instanceof Dog);
        System.out.println(d instanceof Animal);
        System.out.println(d.getClass() == Dog.class);
    }
}


