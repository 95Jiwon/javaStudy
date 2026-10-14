package main.java.com.korai.study.ch11.Tutorial.View;

import main.java.com.korai.study.ch11.Tutorial.Entity.Todo;
import main.java.com.korai.study.ch11.Tutorial.Repository.TodoRepository;
import main.java.com.korai.study.ch11.Tutorial.TodoMain.TodoMain;

import java.util.Scanner;

public class RegisterView implements View{
    private static RegisterView Instance;
    private RegisterView() {

    }

    public static RegisterView getInstance(){
        while(Instance == null) {
            Instance = new RegisterView();
        }
        return Instance;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        String content;
        System.out.println("[ REGISTER ]");
        System.out.println("내용을 입력하세요 ");
        System.out.print(">>> ");
        content = scanner.nextLine().trim();
        Todo newTodo = new Todo();
        newTodo.setContent(content);
        TodoRepository.getInstance().insert(newTodo);
        System.out.println("등록완료");
        TodoMain.pathIndex = 0;
    }

}
