package main.java.com.korai.study.ch11.Tutorial.TodoMain;



import com.korai.study.ch11.Tutorial.View.View;
import main.java.com.korai.study.ch11.Tutorial.View.HomeView;
import main.java.com.korai.study.ch11.Tutorial.View.RegisterView;
import main.java.com.korai.study.ch11.Tutorial.View.ListView;

import java.util.ArrayList;
import java.util.List;

public class TodoMain {
        public static int pathIndex = 0;
        public static boolean running = true;

        public static void main(String[] args) {
            List<View> viewList = new ArrayList<>();
            viewList.add((View) HomeView.getInstance());
            viewList.add((View) RegisterView.getInstance());
            viewList.add((View) ListView.getInstance());
        }

}
