package com.example.bt.dao;

import com.example.bt.dto.UserRole;
import com.example.bt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Peko
 */
@Mapper
public interface UserMapper {
    User getTableByUserId(@Param("userId") String id);
    List<UserRole> getAll();
    int updateTable(User userDTO);
    int deleteTable(User userDTO);
    int addTable(User userDTO);
    List<UserRole> queryTable(User userDTO);
    int updateRole(@Param("userId")String userId,@Param("roleId")String roleId);
    String findRoleByName(@Param("roleName")String roleName);
    void deleteRoleByUserId(@Param("userId")String userId);
}
