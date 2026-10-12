package com.korai.study.practice1012;

public class forPractice06 {
    public static void main(String[] args) {
        //배열을 활용하여 배열에 들어있는 수 총합과 평균을 구하여라.
        int[] num = {60, 75, 80, 92, 88};
        int sum = 0;

        for(int i =0; i<num.length; i++){
            sum += num[i]; //sum에 num배열 i번째 의 수를 더하여라.
        }
        System.out.println(sum);

        double average = sum / num.length;

        System.out.println("총합 : " + sum);
        System.out.println("평균 : " + average);
    }
}
