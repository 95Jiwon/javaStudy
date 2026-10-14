package main.java.com.korai.study.practice1014.Method;

public class MethodMain05 {
    public static void main(String[] args) {
        //Validator 클래스에 비밀번호가 8자 이상이고 숫자를 하나 이상 포함하는지 검사하는
        //isValidPassword를 만드세요. 그리고 이 메서드를 인스턴스 메서드로 만들면 무엇이 불편한지 한 줄로 쓰시오.

        System.out.println(Validator.isValiPassword("abcd1234")); // true
        System.out.println(Validator.isValiPassword("abcdefg")); // false
        System.out.println(Validator.isValiPassword("a1b2")); // false(8자가 아니니깐
        System.out.println(Validator.isValiPassword("a1b2c3d4")); //
    }
}

class Validator {
    public static boolean isValiPassword(String password){
        if(password == null || password.length() < 8){
            return false;
        }
        return password.matches(".*\\d.*");
    }
}
