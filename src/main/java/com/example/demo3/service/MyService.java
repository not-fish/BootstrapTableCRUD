package com.example.demo3.service;

import com.example.demo3.entity.MyTableDTO;
import com.example.demo3.mapper.MyTableMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyService {
    @Resource
    MyTableMapper myTableMapper;

    public MyTableDTO TableFindById(String id){
        return myTableMapper.getTableById(id);
    }

    public List<MyTableDTO> TableFindAll(){
        return myTableMapper.getAll();
    }

    public String TableEdit(MyTableDTO myTableDTO){
        int i = myTableMapper.updateTable(myTableDTO);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    public String TableDelete(MyTableDTO myTableDTO){
        int i = myTableMapper.deleteTable(myTableDTO);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    public String TableAdd(MyTableDTO myTableDTO){
        System.out.println("there are add");
        int i = myTableMapper.addTable(myTableDTO);
        if(i==0){
            System.out.println("false");
            return "false";
        }
        System.out.println("true");
        return "true";
    }

    public List<MyTableDTO> TableQuery(MyTableDTO myTableDTO){
        List<MyTableDTO> list = myTableMapper.queryTable(myTableDTO);
        return list;
    }

}
