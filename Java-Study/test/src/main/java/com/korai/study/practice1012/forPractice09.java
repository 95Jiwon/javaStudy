package com.korai.study.practice1012;

public class forPractice09 {
    public static void main(String[] args) {
        //배열의 요소 거꾸로 뒤집기
        int[] arr = {10, 20, 30, 40, 50};

        System.out.print("원래 배열 : ");
        for(int i = 0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //배열 뒤집기 로직
        for(int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length -1 -i];
            arr[arr.length -1 -i] = temp;
        }

        System.out.print("뒤집힌 배열 : ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
