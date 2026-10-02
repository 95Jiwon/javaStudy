public class StaticCafe {
    public static void main(String[] args) {
        주문 o1 = 카페.주문하기("아메리카노");
        주문 o2 = 카페.주문하기("라떼");
        주문 o3 = 카페.주문하기("아메리카노");
        System.out.println(o1.주문번호 + " " + o1.메뉴);
        System.out.println(o2.주문번호 + " " + o2.메뉴);
        System.out.println(o3.주문번호 + " " + o3.메뉴);
        System.out.println("마지막 번호: " + 카페.번호);
    }
}

class 주문 {
    int 주문번호;
    String 메뉴;
    int 가격;

    주문(int 주문번호, String 메뉴) {
        System.out.println("주문 생성자 호출");
        this.주문번호 = 주문번호;
        this.메뉴 = 메뉴;
        가격 = 메뉴.equals("라떼") ? 5000 : 4500;
    }
}

class 카페 {
    static int 번호 = 100;

    static {
        System.out.println("카페 오픈");
    }

    static 주문 주문하기(String 메뉴) {
        return new 주문(++번호, 메뉴);
    }
}
