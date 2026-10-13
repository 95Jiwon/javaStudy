package main.java.com.korai.study.ch11.Tutorial.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Todo {
    private int id = 0;
    private String content = "";
}
