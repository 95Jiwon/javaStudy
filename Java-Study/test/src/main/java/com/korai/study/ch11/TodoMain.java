package main.java.com.korai.study.ch11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class TodoMain {
    public static int pathIndex = 0;
    public static boolean running = true;

    public static void main(String[] args) {
        List<View> viewList = new ArrayList<>();
        viewList.add(HomeView.getInstance());
        viewList.add(RegisterView.getInstance());
        viewList.add(ListView.getInstance());

//        Scanner scanner = new Scanner(System.in);

        while(running) {
            viewList.get(pathIndex).show();
        }
    }
} // 데이터를 불러와 실행시킬 구역

// object(최상위) 클래스 < 공부
@Data
@NoArgsConstructor
@AllArgsConstructor
// 정보를 저장, 관리할 수 있도록 도와줄 Entity 클래스,
// 계속해서 생성이 되는 데이터는 Entity로 관리한다.
class Todo {
    private int id;
    private String content;
}

// 인터페이스는 기능을 추상화 시키기 위해서 사용한다.
// 기능을 추상화 할 때
// 인터페이스로 기능을 추상화 했으면 구현 객체는 무조건 존재할 것이다.
interface View {
    void show();
}

//클래스는 생성(메모리에 인스턴스를 실존하게 하는 것) 가능해야 한다.
class HomeView implements View{

    // 싱글톤 2번 해당 클래스 자료형의 인스턴스를 담아둘 static 변수를 정의(선언)한다.
    // static은 생성 할 때 딱 한 번만 호출된다.
    // 이 타이밍을 놓치면(메모리 공간이 없으면) null이 들어가게 된다.
    // private로 외부 접근 제한
    private static HomeView instance;

    // 싱글톤 1번 생성자를 private으로 정의한다.
    // private 접근제한자는 해당 클래스 내부에서만 접근 할 수 있다.
    // 생성자를 숨겼다는 것은 외부에서 생성을 제한하기 위함이다.
    private HomeView(){

    }

    // 싱글톤 3번 째 static 변수 instance가 null일 때 인스턴스를 생성
    // static getter를 통해서만 instance를 접근 할 수 있게 유도
    public static HomeView getInstance() {
        while (instance == null) {
            instance = new HomeView();
        }
        return instance;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        System.out.println("[ HOME ]");
        System.out.println("R. TODO 등록하기");
        System.out.println("L. TODO 목록조회");
        System.out.println("Q. 프로그램 종료");
        System.out.print(">>> ");
        cmd = scanner.nextLine().trim(); //trim() = 좌우 공백 없애기, charAt(n) = n번째 문자를 꺼내와서 쓴다.
        cmd = String.valueOf(cmd.charAt(0));
        System.out.println(cmd.matches("[a-zA-Z]"));
        boolean isAlphabet = cmd.matches("[a-zA-Z]");
        if (!isAlphabet) { //abc를 입력 했을 때
            System.out.println("영문자만 입력 가능합니다.");
            return;
        }
        cmd = cmd.toUpperCase();
        if("Q".equals(cmd)) {
            TodoMain.running = false;
            return;
        } else if ("R".equals((cmd))){
            TodoMain.pathIndex = 1;
        } else if ("L".equals((cmd))){
            TodoMain.pathIndex = 2;
        }   else{
            System.out.println("다시 입력하세요.");
        }
    }

}

class RegisterView implements View{
    private static RegisterView instance;

    private RegisterView() {

    }

    public static RegisterView getInstance() {
        while(instance == null){
            instance = new RegisterView();
        }
        return instance;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        String content;
        System.out.println("[ REGISTER ]");
        System.out.println("내용을 입력하세요. ");
        System.out.print(">>> ");
        content = scanner.nextLine().trim();
        Todo newTodo = new Todo();
        newTodo.setContent(content);
        TodoRepository.getInstance().insert(newTodo);
        System.out.println("TODO 등록 완료.");
        TodoMain.pathIndex = 0;
    }
}

class ListView implements View{
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

interface Repository<T> {
    void insert(T t);
    T update(T t);
    T delte(int id);
    T findById(int id);
    List<T> findAll();
}

class TodoRepository implements Repository<Todo> {
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
    public Todo delte(int id) {
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
