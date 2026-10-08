package com.korai.study.ch10.TODO.view;

import com.korai.study.ch10.TODO.entity.Todo;
import com.korai.study.ch10.TODO.router.RootRouter;
import com.korai.study.ch10.TODO.service.TodoService;

import java.util.List;
import java.util.Scanner;

public class TodoStatusView implements View {
    private final Scanner scanner;
    private final TodoService todoService;
    private int selectedTodoId;

    public TodoStatusView(TodoService todoService) {
        this.scanner = new Scanner(System.in);
        this.todoService = todoService;
    }

    @Override
    public void show() {
        System.out.println("[ TODO STATUS 변경 ]");
        showSelectList();
    }

    private void printTodoList() {
        if (todoService.getTodoList() == null) {
            System.out.println("등록된 할 일이 없습니다.");
            return;
        }
        for (Todo todo : todoService.getTodoList()) {
            System.out.println(todo);
        }
    }

    private void selectedTodo() {
        int todoId;
        printTodoList();
        System.out.print("todoId 선택 >>> ");
        todoId = scanner.nextInt();
        scanner.nextLine();
        List<Todo> todos = todoService.getTodoList();
        boolean foundStatus = false;
        for (Todo todo : todos) {
            if (todo.getId() == todoId) {
                foundStatus = true;
            }
        }

        if (!foundStatus) {
            System.out.println("선택하신 TODO ID의 정보가 존재하지 않습니다.");
            return;
        }

        selectedTodoId = todoId;
    }

    private void showSelectList() {
        String cmd;
        System.out.println("1: TODO 선택하기");
        System.out.println("2: 진행전으로 변경");
        System.out.println("3: 진행중으로 변경");
        System.out.println("4: 완료로 변경");
        System.out.println("b: 뒤로가기");
        System.out.print(">>> ");
        cmd = scanner.nextLine();
        if ("1".equals(cmd)) {

        } else if ("2".equals(cmd)) {

        } else if ("3".equals(cmd)) {

        } else if ("4".equals(cmd)) {

        } else if ("b".equals(cmd)) {
            RootRouter.setCurrent("todo-list");
        } else {
            System.out.println("다시 입력하세요.");
        }
    }
}