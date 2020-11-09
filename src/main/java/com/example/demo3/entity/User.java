package com.example.demo3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Peko.Lai
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String userId;
    private String userName;
    private String userPhone;
    private String userImg;
    private String userPassword;
    private String salt;

    //角色集合
    private List<Role> roles;
}

