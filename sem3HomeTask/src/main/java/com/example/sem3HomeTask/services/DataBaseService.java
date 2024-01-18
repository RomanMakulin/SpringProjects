package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;

import java.util.List;

public interface DataBaseService {
    public List<User> findAllFromDB();
    public User createToDB(User user);
    public List<User> filterByAgeDB(int age);
    public List<User> sortByAgeDB();
}
