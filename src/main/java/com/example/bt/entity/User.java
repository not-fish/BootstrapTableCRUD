package com.example.bt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

}

