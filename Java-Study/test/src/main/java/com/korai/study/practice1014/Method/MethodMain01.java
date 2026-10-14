package main.java.com.korai.study.practice1014.Method;
//  MathUtil 클래스에 세 정수 중 가장 큰 값을 돌려주는 max 메서드를 만드세요.

public class MethodMain01 {
    public static void main(String[] args) {
        System.out.println(MathUtil.max(3, 9, 5, 7, 11)); // 결과 값 : 9 도출
    }
}

//매개변수로 데이터를 전달 받아 max가 전달 받은 값을 하나씩 꺼내어 본 뒤 자신의 값이 nums[i]보다 낮다면 그 값을 대입하는
//조건문을 작성 반복문이 빠져 나오면 max값을 리턴해 다시 데이터를 전달 main에서 작동이 되게 한다.
class MathUtil {
    public static int max(int... nums){
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > max){
                max = nums[i];
            }
        }
        return max;
    }
}


