package com.example.demo3.service;

import com.example.demo3.dto.UserRole;
import com.example.demo3.dto.UserUpadteRole;
import com.example.demo3.entity.User;

import java.util.List;

public interface UserService {
     User tableFindByUserId(String id);

     List<UserRole> tableFindAll();
     String tableEdit(User user);

     String tableDelete(User user);

     String tableAdd(User user);

     List<UserRole> tableQuery(User user);

     boolean tableEditRole(UserUpadteRole uur);

     void tableDeleteRole(String userId);
}
