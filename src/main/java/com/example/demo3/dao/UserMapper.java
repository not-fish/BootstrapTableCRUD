package com.example.demo3.dao;

import com.example.demo3.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Peko.Lai
 */
@Mapper
public interface UserMapper {
    User getTableByUserId(@Param("userId") String id);
    List<User> getAll();
    int updateTable(User userDTO);
    int deleteTable(User userDTO);
    int addTable(User userDTO);
    List<User> queryTable(User userDTO);
}
