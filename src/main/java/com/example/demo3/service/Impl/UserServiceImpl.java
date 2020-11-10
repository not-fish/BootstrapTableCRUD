package com.example.demo3.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo3.dto.UserRole;
import com.example.demo3.dto.UserUpadteRole;
import com.example.demo3.entity.Role;
import com.example.demo3.entity.User;
import com.example.demo3.dao.UserMapper;
import com.example.demo3.service.UserService;
import com.example.demo3.utils.SaltUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peko.Lai
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper myTableMapper;

    @Override
    public User tableFindByUserId(String id){
        return myTableMapper.getTableByUserId(id);
    }

    @Override
    public List<UserRole> tableFindAll(){
        List<UserRole> users = myTableMapper.getAll();
        System.out.println(users);
        return users;
    }

    @Override
    public String tableEdit(User user){
        int i = myTableMapper.updateTable(user);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    @Override
    public String tableDelete(User user){
        int i = myTableMapper.deleteTable(user);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    @Override
    public String tableAdd(User user){
        System.out.println("there are add");

        //明文密码进行 MD5 + salt + hash 处理
        String salt = SaltUtils.getSalt(8);
        System.out.println("salt = "+salt);
        user.setSalt(salt);
//        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(),salt,1024);
//        user.setUserPassword(md5Hash.toHex());
//        System.out.println("password = "+md5Hash.toHex());
        int i = myTableMapper.addTable(user);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    @Override
    public List<UserRole> tableQuery(User user){
        List<UserRole> list = myTableMapper.queryTable(user);
        return list;
    }

    public static void main(String[] args) {

        String[] strs = {"a","b"};
        System.out.println(strs[0]);
    }

    @Override
    public boolean tableEditRole(UserUpadteRole uur){
        String userId = uur.getUserId();
        String roleId = "";
        String[] roles = uur.getRoleNames();
        for (String role:roles) {
            roleId = myTableMapper.findRoleByName(role);
            int i = myTableMapper.updateRole(userId,roleId);
            if(i==0){
                System.out.println("授权失败");
                return false;
            }
        }

        return true;
    }

    @Override
    public void tableDeleteRole(String userId){

    }
}
