package main.java.com.korai.study.ch04;

public class Book {
    // 필드 - 1. 인스턴스 필드, 2. static 필드
    String name;
    static String author;
    final String name2="초기화1";
    static final String author2="초기화2";
    // 함수 - 1. 생산자, 인스턴스 메서드, static 메서드
    Book() {}
    Book(String name) {this.name = name;}
    String getName() {return name;}
    void print() {}
    static String getAuthor() {return author;}
    static void staticePrrint() {}
    // 내부 클래스 - 1. 인스턴스 클래스, static 클래스
    class Page {}
    static class Page2 {}

}

class BookMain {
    public static void main(String[] args) {
        Book b = new Book();
        newBook(10, b);
        Book.Page p1 = new Book().new Page();
        Book.Page2 p2 = new Book.Page2();
    }

    public static void newBook(int num, Book book){

    }
}