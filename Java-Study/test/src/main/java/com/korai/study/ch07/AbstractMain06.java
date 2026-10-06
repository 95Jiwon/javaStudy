package com.korai.study.ch07;

import java.util.List;

public class AbstractMain06 {
    public static void main(String[] args) {
        List<RemoteControl> remoteControls = List.of(
                new TvRemoteControl() ,
                new MonitorRemoteControl(),
                new TvRemoteControl(),
                new MonitorRemoteControl()
        );

        for (int i = 0; i < remoteControls.size(); i++){
            RemoteControl r = remoteControls.get(i);
            r.powerOn(); //이렇게 할 경우 인덱스가 필요한데 아래 구문에서는 인덱스가 필요하지 않게 된다.
        }

        for (RemoteControl r : remoteControls){ //foreach문
            r.powerOn();
        }
    }
}

interface Sensor {
    void send();
    void on();
    void off();

    default void send2() {

    }
}

abstract class RemoteControl {
    // 리모컨
    String modelName;

    void showModelName() {
        System.out.println(modelName);
    }
    abstract void powerOn(); //추상화 개념 자체만 정의

}

class TvRemoteControl extends RemoteControl{

    @Override
    void powerOn(){
        System.out.println("TV회로에 맞게 전원 공급");
    }


}

class MonitorRemoteControl extends RemoteControl {
    @Override
    void powerOn() {
        System.out.println("모니터 회로에 맞게 전원 공급");
    }
}
