package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DriverDaoImpl implements DriverDao {

    Logger logger = LoggerFactory.getLogger(DriverDaoImpl.class);

    @Autowired
    private RedisTemplate<String, Employee> template;

//    @Autowired
//    public DriverDao(@Qualifier("redisTemplate")RedisTemplate<String, Employee> template){
//        this.template = template;
//    }

    public Set<Employee> findAllRoad(String resource){
        return template.opsForZSet().range(resource,0,template.opsForZSet().size(resource));
    }

    public Employee saveDriver(Employee employee){
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
        template.opsForZSet().add(employee.getResource(), employee, Double.parseDouble(result));
        logger.info("User is save: " + employee);
        return employee;
    }

    public Employee lastPointOfDriver(String resource){
        Set<Employee> product = template.opsForZSet().range(resource, (template.opsForZSet().size(resource)-1),
                template.opsForZSet().size(resource));

        Employee latPointOfProduct = product.iterator().next();
        logger.info("last position of driver: " + latPointOfProduct);
        return  latPointOfProduct;
    }
}
