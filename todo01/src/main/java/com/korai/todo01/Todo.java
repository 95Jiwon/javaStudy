package com.korai.todo01;

public class Todo {
    private String writer;
    private String date;
    private String content;

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "writer='" + writer + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
