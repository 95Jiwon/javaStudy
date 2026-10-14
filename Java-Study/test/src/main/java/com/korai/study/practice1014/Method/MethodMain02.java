package main.java.com.korai.study.practice1014.Method;

public class MethodMain02 {
    public static void main(String[] args) {
        //TempConveter 클래스에 섭씨 -> 화씨 toFahrenheit, 화씨 -> 섭씨 toCelsius를 만드세요.
        //* 화씨 = 섭씨x9/5+32
        System.out.println(TempConveter.toFahrenheit(36.5));
        System.out.println(TempConveter.toCelsius(212));
    }
}

class TempConveter{
    public static double toFahrenheit(double a){
        a = a*9/5+32;
        return a;
    }
    public static double toCelsius(double a){
        a = (a-32) * 5/9;
        return a;
    }
}
