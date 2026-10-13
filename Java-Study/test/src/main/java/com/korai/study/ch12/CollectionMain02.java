package main.java.com.korai.study.ch12;

import lombok.AllArgsConstructor;

public class CollectionMain02 {
    public static void main(String[] args) {
        CustomLinkedList<String> list1 = new CustomLinkedList<>();
        list1.add("자바");
        list1.add("자바2");
        list1.add("자바3");

        System.out.println(list1);
        System.out.println(list1.get(1));
        list1.remove(1); // 1번 인덱스가 삭제 되어야 한다.
        System.out.println(list1);
    }
}

// LinkedList -> 선형리스트(단방향)
// 노드를 연결한다.
// 노드란? 연결된 다른 노드들의 주소와 저장할 값을 포함한 것
// 노드의 첫 부분은 헤드(머리), 끝 부분은 태일(꼬리)라고 한다.
class CustomLinkedList<E> { //대문자 E 자리는 다 String이다.
    @AllArgsConstructor
    private static class Node<E> { //외부에서는 생성 할 수 없게 접근제한(private)를 걸고 바로 사용 가능하게 static을 넣어둔다.
        E item; //데이터를 저장하는 저장소
//        Node<E> prev;
        Node<E> next; // Node<E> 데이터를 추가
    }

    private Node<E> head; //접근 제한을 걸어 외부에서 사용할 수 없는 head를 생성
    private Node<E> tail; //접근 제한을 걸어 외부에서 사용할 수 없는 tail을 생성
    private int size; // 배열의 크기가 될 size를 생성

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(); // builder에 StringBuilder를 생성하여 저장
        builder.append("["); // builder의 append를 이용해 매개변수를 주어 데이터를 전달한다.
        Node<E> current = head; // current에 head 주소를 저장
        for( int i = 0;  i < size; i++) { // size의 크기만큼 반복
            builder.append(current.item); // current(head)의 저장된 값을 전달
            if (i != size - 1) { // i가 size의 크기를 -1 한 만큼의 크기와 같지 않으면 문자열에 ,를 추가
                builder.append(", ");
            }
            current = current.next; // 이후 current에 새로 데이터를 전달 받을 current.next를 실행
        }
        builder.append("]"); //반복문을 빠져 나오면 append를 통해 builder에 문자열을 통해 ]를 추가해 글을 닫아주게 꾸민다.
        return builder.toString(); // builder 안의 toString값을 반환
    }

    public void add(E e) { //매개변수를 통해 제네릭 E를 전달한다.
        Node<E> newNode = new Node<>(e, null); //
        if(head == null) { //head가 null이면
            head = newNode; //head에 Node값을 넣고
            tail = head; //tail에 head주소를 넣는다.
        } else { //그렇지 않을 경우
            tail.next = newNode; // tail.next를 통해 새로운 선형리스트 newNode를 저장
            tail = tail.next; // tail에 위에서 저장한 리스트를 저장한다.
            }
        size++; // 그리고 배열의 크기를 한 칸 늘려준다.
    }

    public E get(int index) {
        if(!(index < size && index > - 1)) {
            throw new IndexOutOfBoundsException("인덱스 범위를 초과하였습니다."); //index가 범위를 벗어난걸 터트려주겠다. 예외처리
        }
        Node<E> current = head;
        for( int i = 0; i < index; i++) {
                current = current.next;
        }
        return current.item;
    }

    public E remove(int index) {
        if (!(index < size && index > -1)) {
            throw new IndexOutOfBoundsException("인덱스 범위를 초과하였습니다.");
        } //예외처리
        E removedItem; //삭제되는 노드의 데이터를 저장할 변수
        if (index == 0) {
            removedItem = head.item;
            head = head.next; // head를 다음 노드로 이동

            //만약 리스트에 데이터가 1개 밖에 없어서 지운 후 hear가 null이 되었다면 tail도 null로 처리
            if (head == null) {
                tail = null;
            }
        } else {
            Node<E> prev = head;

            for (int i = 0; i < index -1; i++) {
                prev = prev.next;
            }

            Node<E> target = prev.next;
            removedItem = target.item;

            prev.next = target.next;

            if(target == tail) {
                tail = prev;
            }
        }
        size--;
        return removedItem;
    }
}
