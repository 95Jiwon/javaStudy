package com.korai.study.ch05.practice;

import java.util.Scanner;

public class ScannerMain03 {
    public static void main(String[] args) {
        //
        int[] nums = new int[0];
        Scanner sc = new Scanner(System.in);
        // 계속 추가 하시겠습니까? y/n y
        // 입력 : 10
        // 계속 추가 하시겠습니까? y/n y
        // 입력 : 20
        // 계속 추가 하시겠습니까? y/n y
        // 입력 : 30
        // 계속 추가 하시겠습니까? y/n y
        // 입력 : 40
        // 계속 추가 하시겠습니까? y/n n
        // 총합: 100

        for(int i = 0; i < nums.length+1; i++){ // 배열을 1 추가하고 i를 후위증가한다.

            System.out.print("계속 추가 하시겠습니까? y/n ");
            String yesOrNo = sc.nextLine();

            if ("y".equalsIgnoreCase(yesOrNo)){ //10이라는 숫자를 입력 받으면 배열을 추가하고 다시 for문으로 돌아가게 작성
                System.out.print("입력 : ");
                int inputVal = Integer.parseInt(sc.nextLine());

                int[] newNums = new int[nums.length +1];

                for(int j = 0; j < nums.length; j++) {
                    newNums[j] = nums[j];
                }

                newNums[newNums.length - 1] = inputVal;

                nums = newNums;

            }else if ("n".equalsIgnoreCase(yesOrNo)){
                break;
            }else{
                System.out.println("다시 입력하세요");
            }
        }

        int sum = 0;
        for(int i = 0 ; i < nums.length; i++){
            sum += nums[i];
        }

        System.out.println("총합 : " + sum);
        sc.close();
    }
}
