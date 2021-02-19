package com.example.demo.driverDaoTest;


import com.example.demo.entity.Employee;
import com.example.demo.repository.DriverDaoImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;


public class DriverDaoTest {

    @Mock
    private RedisTemplate<String, Employee> template;

    @Mock
    Employee employee;

    @InjectMocks
    DriverDaoImpl dao;

    @Test
    public void sayHello(){
        Assert.assertEquals("Hello", "Hello");
    }

    @Test
    public void save(){
        employee = Mockito.mock(Employee.class);
        employee.setCompany("Epam");
        employee.setLatitude(100);
        employee.setLongitude(200);
        employee.setResource("Dima");
        LocalDateTime localDateTime = LocalDateTime.of(2019, 02,02, 20,20);
        employee.setTime(localDateTime);
        String date = employee.getTime().toString();
        String[] splitDate = date.split("T");
        String[] splitDateOne = splitDate[0].split("-");
        String[] splitDateTwo = splitDate[1].split(":");
        String result = "";
        for(String s : splitDateOne){
            result+=s;
        }

        for(String s : splitDateTwo){
            result+=s;
        }

//
        Assert.assertEquals(employee, dao.saveDriver(employee));
    }

}

// Mockito.doReturn(true)
//                .when(template)
//                .opsForZSet().add(employee.getResource(), employee, Double.parseDouble(result));
//
//        Mockito.doReturn(employee)
//                .when(dao)
//                .saveDriver(employee);
