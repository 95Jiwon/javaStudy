package main.java.com.korai.study.ch12;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CollectionMain03 {
    @Data
    @AllArgsConstructor
   static class Car {
        private String model;
    }
    public static void main(String[] args) {
        int[] nums = new int[5];
        nums[0] = 100;
        nums[1] = 200;
        nums[2] = 300;
        nums[3] = 400;
        nums[4] = 500;

        print(nums);
        print(nums);

        String[] names = {"황지원", "황지투", "황지삼", "황지사"};

        print(names);

        Car[] cars = new Car[5];
        cars[0] = new Car("아반떼");
        cars[1] = new Car("소나타");
        cars[2] = new Car("그랜저");
        cars[3] = new Car("G80");
        cars[4] = new Car("G90");

        print(cars);
    }

    private static void print(Car[] cars) {
        for(int i = 0; i < cars.length; i++){
            System.out.println(cars[i]);
        }
        System.out.println();
    }


    private static void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);;
        }
        System.out.println();
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println();
    }
}
