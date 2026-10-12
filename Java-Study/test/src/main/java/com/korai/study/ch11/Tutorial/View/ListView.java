package com.korai.study.ch11.Tutorial.View;

public class ListView implements View {
    private static ListView Instance;

    private ListView(){

    }

    public static ListView getInstance(){
        while(Instance == null){
            Instance = new ListView();
        }
        return Instance;
    }

    @Override
    public void show() {

    }
}
