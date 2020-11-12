package com.example.bt.service;

import com.example.bt.dto.UserRole;
import com.example.bt.dto.UserUpadteRole;
import com.example.bt.entity.User;

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
