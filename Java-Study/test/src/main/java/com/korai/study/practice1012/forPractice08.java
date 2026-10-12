package com.korai.study.practice1012;

public class forPractice08 {
    public static void main(String[] args) {
        //배열의 최소값과 최대값을 구하시오.
        int[] num = {10,30, 55, 76, 80, 92};
        int min = num[0];
        int max = num[0];

        for(int i = 0; i<num.length; i++){ //num의 배열 크기 만큼 반복하라.
            if(num[i] > max){
                max += num[i];
            }else if(num[i] < min){
                min += num[i];
            }
        }
        System.out.println("최댓값은 : " + max);
        System.out.println("최소값은 : " + min);
    }
}
