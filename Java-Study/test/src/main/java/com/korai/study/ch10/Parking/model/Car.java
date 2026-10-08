package com.korai.study.ch10.Parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Data
public class Car {
    private int carNo;                  // 관리 번호(자동 증가 번호용)
    private String vehicleNo;           // 차량 번호(예: "12가 3456")
    private LocalDateTime entryTime;    //입차 시간
    private int fee;                    // 요금 (정산 후 저장되거나 초기 0원

    @Override
public String toString(){
    //입차 시간을 보기 좋은 포맷(예: 연-월-일 시:분:초)으로 변환
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedTime = (entryTime != null) ? entryTime.format(formatter) : "정보 없음";

    return "주차 정보 [관리 번호: " + carNo +
            ", 차량 번호: " + vehicleNo +
            ", 입차 시간: " + formattedTime +
            ", 요금: " + fee + "원]";
    }
}
