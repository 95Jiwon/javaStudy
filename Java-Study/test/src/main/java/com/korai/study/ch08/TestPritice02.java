package com.korai.study.ch08;

import java.util.Objects;

public class TestPritice02 {
    public static void main(String[] args) {
        class Book{
            private String book;
            private int page;

            public Book(String book, int page) {
                this.book = book;
                this.page = page;
            }

            @Override
            public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Book book1 = (Book) o;
                return page == book1.page && Objects.equals(book, book1.book);
            }

            @Override
            public int hashCode() {
                return Objects.hash(book, page);
            }
        }

        Book book1 = new Book("자바의 정석(기초)", 324);
        Book book2 = new Book("자바의 정석(기초)", 324);
        Book book3 = new Book("자바의 정석(심화)", 361);

        System.out.println("book1과 book2는 내용이 같은가?" + book1.equals(book2));
        System.out.println("book1과 book3는 내용이 같은가?" + book1.equals(book3));

    }
}
