package com.korai.study.ch07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AbstractMain01 {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("황지원");
        names.add("황지투");
        names.add("황지삼");
        System.out.println(names);

        LinkedList<String> names2 = new LinkedList<>();
        names2.add("황지원");
        names2.add("황지투");
        names2.add("황지삼");
        System.out.println(names2);

        ArrayList<List<String>> lists = new ArrayList<>();
        double[][] doubles = new double[2][2];
        lists.add(new ArrayList<>());
        lists.add(new LinkedList<>());
        lists.add(new ArrayList<>());
        lists.get(0).get(0);
        List<String> strings = lists.get(0);
        String str = lists.get(0).get(0);

        double d = 10;
        int i = (int) d;
    }
}
