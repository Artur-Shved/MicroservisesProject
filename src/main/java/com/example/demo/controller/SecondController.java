package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.repository.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("msg")
public class SecondController {
    @Autowired
    private DriverDao dao;

    @GetMapping
    public Set<Employee> allRoad(String resource){
        return dao.findAllRoad(resource);
    }

}
