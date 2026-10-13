package main.java.com.korai.study.ch11.Tutorial.Repository;

import java.util.List;

interface Repository<T> {
    void insert(T t);
    T update(T t);
    T delete(int id);
    T findById(int id);
    List<T> findAll();
}