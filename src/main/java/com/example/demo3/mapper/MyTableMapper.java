package com.example.demo3.mapper;

import com.example.demo3.dao.MyTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyTableMapper {
    MyTable getTableById(@Param("id") String id);
    List<MyTable> getAll();
    int updateTable(MyTable myTableDTO);
    int deleteTable(MyTable myTableDTO);
    int addTable(MyTable myTableDTO);
    List<MyTable> queryTable(MyTable myTableDTO);
}
