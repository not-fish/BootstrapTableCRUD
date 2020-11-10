package com.example.demo3.dto;

import com.example.demo3.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * user表和role表的结合
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
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
