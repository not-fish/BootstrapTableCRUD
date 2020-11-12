package com.example.bt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 用于修改权限（角色）
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpadteRole {
    private String id;
    private String userId;
    private String userName;
    private String userPhone;
    private String userImg;
    private String userPassword;
    private String salt;

    //角色集合
    private String[] roleNames;
}
