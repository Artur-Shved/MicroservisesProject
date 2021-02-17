package com.example.demo.repository;

import com.example.demo.entity.Employee;

import java.util.Set;

public interface DriverDao {
    Set<Employee> findAllRoad(String resource);
    Employee saveDriver(Employee employee);
    Employee lastPointOfDriver(String resource);
}
