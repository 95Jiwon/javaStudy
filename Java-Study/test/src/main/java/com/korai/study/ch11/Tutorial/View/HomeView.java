package com.korai.study.ch11.Tutorial.View;

import com.korai.study.ch11.Tutorial.TodoMain.TodoMain;

import java.util.Locale;
import java.util.Scanner;

//HomeView
public class HomeView implements View {
    private static HomeView Instance;

    private HomeView() {

    }

    public static HomeView getInstance() {
        while (Instance == null) {
            Instance = new HomeView();
        }
        return Instance;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        System.out.println("[ HOME ] ");
        System.out.println("R. TODO 등록하기 ");
        System.out.println("L. TODO 조회하기");
        System.out.println("Q. 프로그램 종료");
        System.out.print(">>> ");
        cmd = scanner.nextLine().trim();
        cmd = String.valueOf(cmd.charAt(0));
        System.out.println(cmd.matches("[a-zA-Z]"));
        boolean isAlphaBet = cmd.matches("[a-zA-Z]");
        if(!isAlphaBet){
            System.out.println("영문자만 입력 가능합니다.");
            return;
        }
        cmd = cmd.toUpperCase();
        if("Q".equals(cmd)){
            TodoMain.running = false;
            return;
        }else if("R".equals(cmd)){
            TodoMain.pathIndex = 1;
        }else if("L".equals(cmd)){
            TodoMain.pathIndex = 2;
        }else {
            System.out.println("다시 입력하세요.");
        }
    }
}
