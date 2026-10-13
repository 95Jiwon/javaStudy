package main.java.com.korai.study.ch11.Tutorial.Repository;



import main.java.com.korai.study.ch11.Tutorial.Entity.Todo;
import main.java.com.korai.study.ch11.Tutorial.Repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements Repository<Todo> {
    private static TodoRepository instance;
    private int lastId;
    private List<Todo> todos;

    private TodoRepository() {
        lastId = 1;
        todos = new ArrayList<>();
    }

    public static TodoRepository getInstance() {
        if (instance == null) {
            instance = new TodoRepository();
        }
        return instance;
    }

    @Override
    public void insert(Todo todo) {
        todo.setId(lastId++);
        todos.add(todo);
    }

    @Override
    public Todo update(Todo todo) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == todo.getId()) {
                todos.set(i, todo);
                break;
            }
        }
        return null;
    }

    @Override
    public Todo delete(int id) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == id) {
                return todos.remove(i);
            }
        }
        return null;
    }

    @Override
    public Todo findById(int id) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == id) {
                return todos.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Todo> findAll() {
//        List<Todo> copyTodos = new ArrayList<>();
//        copyTodos.addAll(todos);
//        return copyTodos;
        return List.copyOf(todos);
    }
}