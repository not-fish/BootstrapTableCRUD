package com.example.demo3.service;

import com.example.demo3.entity.User;

import java.util.List;

public interface UserService {
     User tableFindByUserId(String id);

     List<User> tableFindAll();
     String tableEdit(User user);

     String tableDelete(User user);

     String tableAdd(User user);

     List<User> tableQuery(User user);
}
