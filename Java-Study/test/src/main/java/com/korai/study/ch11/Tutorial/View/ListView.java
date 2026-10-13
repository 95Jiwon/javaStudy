package com.korai.study.ch11.Tutorial.View;

import java.util.List;

class ListView implements com.korai.study.ch11.View {
    public static com.korai.study.ch11.ListView instance;
    private ListView() {

    }

    public static com.korai.study.ch11.ListView getInstance() {
        while(instance == null){
            instance = new com.korai.study.ch11.ListView();
        }
        return instance;
    }

    @Override
    public void show() {
        System.out.println("[ LIST ]");
        List<com.korai.study.ch11.Todo> todos = com.korai.study.ch11.TodoRepository.getInstance().findAll();
        if (todos.size() == 0) {
            System.out.println("등록된 TODO가 없습니다.");
            com.korai.study.ch11.TodoMain.pathIndex = 0;
            return;
        }
        for (com.korai.study.ch11.Todo todo : todos) {
            System.out.println(todo);
        }
        com.korai.study.ch11.TodoMain.pathIndex = 0;
    }
}
