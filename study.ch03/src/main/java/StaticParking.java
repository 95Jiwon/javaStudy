public class StaticParking {
    public static void main(String[] args) {
        차량 c1 = 주차장.입차("12가3456");
        차량 c2 = 주차장.입차("34나5678");
        차량 c3 = 주차장.입차("56다7890");
        System.out.println("남은 자리: " + 주차장.남은자리);
        System.out.println(c3);
        System.out.println(c3.차번호);
    }
}

class 차량 {
    String 차번호;
    int 주차칸;

    차량(String 차번호, int 주차칸) {
        System.out.println("차량 생성자 호출");
        this.차번호 = 차번호;
        this.주차칸 = 주차칸;
    }
}

class 주차장 {
    static int 남은자리 = 2;
    static int 칸번호 = 1;

    static {
        System.out.println("주차장 클래스 로딩");
    }

    static 차량 입차(String 차번호) {
        if (남은자리 == 0) {
            System.out.println(차번호 + " 만차");
            return null;
        }
        남은자리--;
        return new 차량(차번호, 칸번호++);
    }
}