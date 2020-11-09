package com.example.demo3.service.Impl;

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
    public List<User> tableFindAll(){
        List<User> users = myTableMapper.getAll();
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
    public List<User> tableQuery(User user){
        List<User> list = myTableMapper.queryTable(user);
        return list;
    }

    public static void main(String[] args) {

        Role r1 = new Role(null,"admin");
        Role r2 = new Role(null,"user");
        List<Role> roles = new ArrayList<>();
        roles.add(r1);
        roles.add(r2);
        System.out.println(roles.toString());
        User user = new User();
        user.setRoles(roles);
        System.out.println(user);
    }

}
