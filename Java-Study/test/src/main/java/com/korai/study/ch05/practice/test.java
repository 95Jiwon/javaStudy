package com.korai.study.ch05.practice;

import java.util.Scanner;

public class test {
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

        for(int i = 0; i < nums.length +1; i++) {
            System.out.print("계속 추가 하시겠습니까? y/n ");
            String yesOrNo = sc.nextLine();

            if("y".equalsIgnoreCase(yesOrNo)) {
                System.out.print("입력 : ");
                int num = sc.nextInt();
                sc.nextLine();// 혹은 int num = sc.nextInt(); sc.nextLine();으로 줄바꿈을 날릴 수도 있다.

                int[] newNums = new int[nums.length+1];

                for(int j = 0; j < nums.length; j++){
                    newNums[j] = nums[j];
                }

                newNums[newNums.length -1] = num;

                nums = newNums;
            }else if("n".equalsIgnoreCase(yesOrNo)) {
                break;
            }else {
                System.out.println("다시 입력해주세요. ");
            }
        }

        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        System.out.println("총합 : " + sum);
    }
}