package main.java.com.korai.study.ch12;

import java.awt.print.Printable;
import java.util.ArrayList;

class 내가만든예외 extends RuntimeException{
    public 내가만든예외(String message) {
        super(message);
    }
}

public class CollectionMain04 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(100);
        arrayList.add(200);
        arrayList.add(300);
        arrayList.add(400);
        arrayList.add(500);

        try{
            print(arrayList, arrayList.size() + 2);
        } catch (IndexOutOfBoundsException e){
            System.out.println("인덱스 초과 요류 해결함");
        } catch(NullPointerException e) {
            System.out.println("Null주소 오류 해결함.");
        } catch (Exception e){
            System.out.println("예외 처리됨!");
            System.out.println(e.getMessage());
        }

        System.out.println("프로그램 정상 종료");
    }

    static void print(ArrayList<Integer> arr, int size) throws Exception { //예외가 2개로 나뉘면 강제성이 없어진다.
        System.out.println("리스트 크기 : " + arr.size());
        if (size == 7) {
            throw new 내가만든예외("내가 강제로 효출한 예외");
        }
        for (int i = 0; i < size; i++) {
                System.out.println(arr.get(i)); //Error가 날 수 있는 코드를 집어 넣는다.
        }
        System.out.println();
    }
}
