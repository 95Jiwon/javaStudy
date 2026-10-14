package main.java.com.korai.study.practice1014.Method;

public class MethodMain03 {
    public static void main(String[] args) {
        // StringUtil 클래스에 앞에서 읽으나 뒤에서 읽으나 같은 문자열인지 검사하는
        // isPalindrome 을 만드세요. StringBuilder.reverse() 는 쓰지 말고 반복문으로 풀어요
        System.out.println(StringUtil.isPalindrome("level")); //true
        System.out.println(StringUtil.isPalindrome("java")); //false
    }
}

class StringUtil{
    public static boolean isPalindrome(String str){
        int len = str.length();
        for(int i = 0; i < len/2; i++){
            char frontChar = str.charAt(i);
            char backChar = str.charAt(len-i -1);

            System.out.print("[" +
                    frontChar + ", "
            + " ]");
            System.out.print("[" +
                    backChar + ", "
            + " ]");
            if(frontChar != backChar) {
                return false;
            }
        }
        return true;
    }
}
