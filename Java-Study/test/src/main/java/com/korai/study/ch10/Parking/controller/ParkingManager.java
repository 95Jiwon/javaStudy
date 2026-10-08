package com.korai.study.ch10.Parking.controller;

import com.korai.study.ch10.Parking.model.Car;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingManager {
    //주차된 차량들을 관리할 리스트
    private List<Car> carList = new ArrayList<>();

    // 요구사항: static 변수 (자동 증가 번호, 누적 매출)
    private static int sequenceNo = 1;      // 차량 관리 번호 자동 증가
    private static int totalRevenue = 0;    // 누적 매출


//1. 차량 등록 및 리스트 추가
    public void registerCar(String vehicleNo) {
        //현재 시간(입차 시간) 생성
        LocalDateTime entryTime = LocalDateTime.now();

        //새로운 Car 객체 생성 (sequenceNo를 번호로 주고 후위 연산자로 증가)
//        Car newCar = new Car(sequenceNo++, vehicleNo, entryTime);

        //리스트에 추가
//        carList.add(newCar);
//        System.out.println(">>> 입차 완료: " + newCar.getVehicleNo() + " (관리번호: " + newCar.getCarNo() + ")");
    }
}
