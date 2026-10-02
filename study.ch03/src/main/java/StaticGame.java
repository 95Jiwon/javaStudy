public class StaticGame {
    public static void main(String[] args) {
        System.out.println("main 시작");
        캐릭터 p1 = 게임서버.캐릭터생성("준일");
        캐릭터 p2 = 게임서버.캐릭터생성("준이");
        p1.레벨업();
        p1.레벨업();
        System.out.println(p1.닉네임 + " Lv." + p1.레벨 + " " + p1.서버);
        System.out.println(p2.닉네임 + " Lv." + p2.레벨 + " " + p2.서버);
    }
}

class 캐릭터 {
    String 닉네임;
    int 레벨 = 1;
    String 서버 = 게임서버.서버이름;

    캐릭터(String 닉네임) {
        System.out.println("캐릭터 생성자 호출, 레벨=" + 레벨);
        this.닉네임 = 닉네임;
    }

    void 레벨업() {
        레벨++;
    }
}

class 게임서버 {
    static String 서버이름;
    static int 접속자수;

    static {
        서버이름 = "아시아-" + 7;
        System.out.println("게임서버 클래스 로딩: " + 서버이름);
    }

    static 캐릭터 캐릭터생성(String 닉네임) {
        접속자수++;
        return new 캐릭터(닉네임);
    }
}