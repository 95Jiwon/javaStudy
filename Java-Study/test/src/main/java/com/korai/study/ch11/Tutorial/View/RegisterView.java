package main.java.com.korai.study.ch11.Tutorial.View;

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

    }

}
