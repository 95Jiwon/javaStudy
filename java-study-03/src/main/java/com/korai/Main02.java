package com.korai;

import java.util.ArrayList;
import java.util.List;

public class Main02 {
    public static void main(String[] args) {
        System.out.println(("가" + '나') == "가나");
        String c = new String("가나");
        Double d = 3.14; //언박싱 참조자료형 안에 든걸 꺼낸다.
        double d2 = d; //박싱 꺼낸 자료형을 원상태로 복구 시킨다.
        d= null;
//            d2 = null;
        List<Double> list = new ArrayList<>();

        System.out.println("=================");
        System.out.println("가" + '나');
        System.out.println("가나");
        System.out.print((new String("가나")));
        System.out.println(("가" + '나') == new String("가나"));
    }
}
