    package com.korai.study.ch09;

    import com.korai.study.ch09.entity.Car;
    import com.korai.study.ch09.service.InitService;

    public class CarMain {
        public static void main(String[] args) {
            new InitService(); // InitService 생성자 호출

            InitService.getCarRepository().printAll(); //InitService안의  CarRepository주소로 들어가서 PrintAll을 실행 하지만, 이 줄에서는 CarRepository에 값이 없으므로 아무것도 출력되지 않음
            Car car1 = new Car(null, "189누 3446", "K8", "김준일");
            Car car2 = new Car(null, "123가 4567", "아반떼", "이서연");
            Car car3 = new Car(null, "234나 5678", "쏘나타", "박민준");
            Car car4 = new Car(null, "45다 1234", "그랜저", "최지우");
            Car car5 = new Car(null, "312로 7890", "투싼", "정하늘");
            Car car6 = new Car(null, "67마 2468", "쏘렌토", "강도윤");
            Car car7 = new Car(null, "158보 1357", "K5", "윤서아");
            Car car8 = new Car(null, "221소 9753", "카니발", "임재현");
            Car car9 = new Car(null, "89우 8642", "G80", "한예린");
            Car car10 = new Car(null, "376주 3141", "팰리세이드", "오승우"); // import한 entity의 Car클래스를 통해 변수 car1~10에 데이터를 추가 및 주소 할당
            InitService.getCarRepository().insert(car1);
            InitService.getCarRepository().insert(car2);
            InitService.getCarRepository().insert(car3);
            InitService.getCarRepository().insert(car4);
            InitService.getCarRepository().insert(car5);
            InitService.getCarRepository().insert(car6);
            InitService.getCarRepository().insert(car7);
            InitService.getCarRepository().insert(car8);
//            new InitService();
            InitService.getCarRepository().insert(car9);
            InitService.getCarRepository().insert(car10); //실질적으로 데이터가 들어가는 줄
            InitService.getCarRepository().printAll(); // CarRepository안의 차량 데이터를 출력.
            InitService.getCarRepository().delete(5l);
            InitService.getCarRepository().printAll();

        }
    }
