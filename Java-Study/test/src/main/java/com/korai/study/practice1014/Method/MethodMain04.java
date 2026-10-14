package main.java.com.korai.study.practice1014.Method;

public class MethodMain04 {
    public static void main(String[] args) {
        // ArrayUtil클래스에 정수 배열의 평균 double로 돌려주는 average를 만드시오. 빈 배열이면 0을 반환.
        System.out.println("정수 배열의 평균 = " + ArrayUtil.average(new int[] {80, 95, 70})); //81.7
    }
}

class ArrayUtil{
    public static double average(int[] arr){
        double sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        return sum/arr.length;
    }
}
