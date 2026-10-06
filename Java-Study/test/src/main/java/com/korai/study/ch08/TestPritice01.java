package com.korai.study.ch08;

import java.util.Objects;

public class TestPritice01 {
    public static void main(String[] args) {
        class Member{
            private String name;
            private int age;
            private String address;

            public Member(String name, int age, String address) {
                this.name = name;
                this.age = age;
                this.address = address;
            }

            @Override
            public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Member member = (Member) o;
                return age == member.age && Objects.equals(name, member.name) && Objects.equals(address, member.address);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, age, address);
            }
        }

        Member member1 = new Member("황지원", 32, "북구");
        Member member2 = new Member("황지원", 32, "북구");
        Member member3 = member1;

        boolean result1 = member1.equals(member2);
        boolean result2 = member1.equals(member3);

        System.out.println(result1);
        System.out.println(result2);

        System.out.println(member1 == member2);
        System.out.println(member1 == member3);

        System.out.println(member1.hashCode() == member2.hashCode());
        System.out.println(member1.hashCode());
        System.out.println(Objects.hash("황지원", 32, "북구"));
        System.out.println(Objects.hash("황지원"));
        System.out.println(Objects.hash(32));
        System.out.println(Objects.hash("북구"));
        System.out.println(Objects.hash("황지원", 32));
    }
}
