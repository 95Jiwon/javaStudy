package com.korai.study.ch11.Tutorial.TodoMain;

import com.korai.study.ch11.Tutorial.View.HomeView;
import com.korai.study.ch11.Tutorial.View.ListView;
import com.korai.study.ch11.Tutorial.View.RegisterView;
import com.korai.study.ch11.Tutorial.View.View;

import java.util.ArrayList;
import java.util.List;

public class TodoMain {
        public static int pathIndex = 0;
        public static boolean running = true;

        public static void main(String[] args) {
            List<View> viewList = new ArrayList<>();
            viewList.add(HomeView.getInstance());
            viewList.add(RegisterView.getInstance());
            viewList.add(ListView.getInstance());
        }

}
