package main.java.com.korai.study.ch14;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
class Role{
    private int id;
    private String name;
}

@Data
@AllArgsConstructor
class User {
    private int id;
    private String username;
    private List<Role> roles;
}

public class Stream02 {
    public static List<User> generateUsers(){
        List<User> users = new ArrayList<>();

        for (int i = 0; i< 10; i++){
            int num = i + 1;
            User user = new User(num,"user" + num, new ArrayList<>());

            Random random = new Random();

            if(random.nextInt(10) % 2 == 0) {//random.nextInt를 하면 0~9까지의 랜덤값을 만들어준다.
                user.getRoles().add(new Role(1, "ROLE_ADMIN"));
                user.getRoles().add(new Role(2, "ROLE_USER"));
                users.add(user);
                continue;
        }

            user.getRoles().add(new Role(2, "ROLE_USER"));
            users.add(user);
    }
        return users;
}


    public static void main(String[] args) {
        //admin유저와 일반 유저를 구분해서 List에 담기
        List<User> userAll = generateUsers();

//        for(int i = 0; i < 10; i++){
//            int num = i+1;
//            User users = new User(num, "user" + num, new ArrayList<>());
//
//            Random random = new Random();
//
//            if(random.nextInt(10) % 2 == 0) {
//                userAll.getRoles().add(new Role(1, "ROLE_ADMIN"));
//                userAll.getRoles().add(new Role(2, "ROLE_USER"));
//                users.add(user);
//                continue;
//            }
//        }
        // List<User> adminUser
        // List.<User users->
        List<User> adminUsers = userAll.stream()
                .filter(user -> !user.getRoles()
                .stream()
                .filter(role -> role.getId() == 1)
                .toList()
                .isEmpty())
                .toList();

        List<User> users = userAll.stream().filter(user -> {
            List<Role> roles = user.getRoles();
            List<Role> adminRoles = roles.stream().filter(role -> role.getId() == 1).toList();
            return adminRoles.isEmpty();
        }).toList();
    }
}
