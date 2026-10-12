package com.korai.study.practice1012;

public class forPractice07 {
    public static void main(String[] args) {
        //배열의 안에 있는 수 중 짝수값에 해당하는 값만 뽑아내어 출력하라.
        int[] num = {10, 25, 30, 40, 43, 52};

        for(int i = 0; i<num.length; i++){
            if(num[i] % 2 != 0){
                System.out.println(num[i] + "는 홀수 입니다.");
            }
        }
    }
}
