package com.example.demo3.mapper;

import com.example.demo3.dto.MyTableDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyTableMapper {
    MyTableDTO getTableById(@Param("id") String id);
    List<MyTableDTO> getAll();
    int updateTable(MyTableDTO myTableDTO);
    int deleteTable(MyTableDTO myTableDTO);
    int addTable(MyTableDTO myTableDTO);
    List<MyTableDTO> queryTable(MyTableDTO myTableDTO);
}
