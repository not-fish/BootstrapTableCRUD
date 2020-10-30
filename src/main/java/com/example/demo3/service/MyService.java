package com.example.demo3.service;

import com.example.demo3.dao.MyTable;
import com.example.demo3.mapper.MyTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyService {
    @Resource
    MyTableMapper myTableMapper;

    public MyTable TableFindById(String id){
        return myTableMapper.getTableById(id);
    }

    public List<MyTable> TableFindAll(){
        return myTableMapper.getAll();
    }

    public String TableEdit(MyTable myTableDTO){
        int i = myTableMapper.updateTable(myTableDTO);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    public String TableDelete(MyTable myTableDTO){
        int i = myTableMapper.deleteTable(myTableDTO);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    public String TableAdd(MyTable myTableDTO){
        System.out.println("there are add");
        int i = myTableMapper.addTable(myTableDTO);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    public List<MyTable> TableQuery(MyTable myTableDTO){
        List<MyTable> list = myTableMapper.queryTable(myTableDTO);
        return list;
    }

}
