package main.java.com.korai.study.ch11.Tutorial.View;

import main.java.com.korai.study.ch11.Tutorial.Entity.Todo;
import main.java.com.korai.study.ch11.Tutorial.Repository.TodoRepository;
import main.java.com.korai.study.ch11.Tutorial.TodoMain.TodoMain;

import java.util.List;

public class ListView implements View {
    public static ListView instance;
    private ListView() {

    }

    public static ListView getInstance() {
        while(instance == null){
            instance = new ListView();
        }
        return instance;
    }

    @Override
    public void show() {
        System.out.println("[ LIST ]");
        List<Todo> todos = TodoRepository.getInstance().findAll();
        if (todos.size() == 0) {
            System.out.println("등록된 TODO가 없습니다.");
            TodoMain.pathIndex = 0;
            return;
        }
        for (Todo todo : todos) {
            System.out.println(todo);
        }
        TodoMain.pathIndex = 0;
    }
}
