package com.korai.study.ch09.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

// 엔티티 클래스 (정보를 저장하는 클래스)
// 필수 요소 : 생성자, getter, setter, equals, hashCode, toString

@AllArgsConstructor //All - 모든, args - 인자가 있는, Constructor - 생성자(getter, setter, equals, hashCode, toString)를 만들어라.
@Data
public class Car {
    private Long id;
    private String number;
    private String model;
    private String owner;
}
