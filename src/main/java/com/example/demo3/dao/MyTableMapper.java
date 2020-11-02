package com.example.demo3.dao;

import com.example.demo3.entity.MyTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Peko.Lai
 */
@Mapper
public interface MyTableMapper {
    MyTable getTableById(@Param("id") String id);
    List<MyTable> getAll();
    int updateTable(MyTable myTableDTO);
    int deleteTable(MyTable myTableDTO);
    int addTable(MyTable myTableDTO);
    List<MyTable> queryTable(MyTable myTableDTO);
}
