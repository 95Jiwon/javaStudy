package main.java.com.korai.study.practice1014.Method;
//Rectangle(int width, int height)을 만들고 넓이area(), 둘레perimeter()
//정사각형인지 isSquard()를 만드세요.
public class MethodMain07 {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(4, 5);
        System.out.println(r.area() + " " + r.perimeter() + " "
        + r.isSquare()); // 결과값 20 18 false
    }
}

class Rectangle {
    int width;
    int height;

    public Rectangle(int w, int h) {
        width = w;
        height = h;
    }

    public int area() {
        return width * height;
    }

    public int perimeter() {
        return 2 * (width + height);
    }
    public boolean isSquare() {
        if(width == height){
            return true;
        }else {
            return false;
        }
    }
}



