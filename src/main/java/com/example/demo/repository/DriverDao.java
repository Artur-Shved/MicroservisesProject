package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DriverDao {
    private static final String HASH_KEY = "Consumer";

    @Autowired
    private RedisTemplate<String, Employee> template;

//    @Autowired
//    public DriverDao(@Qualifier("redisTemplate")RedisTemplate<String, Employee> template){
//        this.template = template;
//    }

    public Set<Employee> findAll(String resource){
        return template.opsForZSet().range(resource,0,template.opsForZSet().size(resource));
    }

    public Employee save(Employee employee){
//        String month = product.getTime().getMonth().toString();
//        String year =String.valueOf(product.getTime().getYear());
//        String dayOfMonth = String.valueOf(product.getTime().getDayOfMonth());
//        String hour  = String.valueOf(product.getTime().getHour());
//        String minutes = String.valueOf(product.getTime().getMinute());
//        String second = String.valueOf(product.getTime().getSecond());
//        String result = month+year+dayOfMonth+hour+minutes+second;
//        StringBuilder time = new StringBuilder();
//        time.append(product.getTime().getYear());
//        time.append(product.getTime().getMonth());
//        time.append(product.getTime().getDayOfMonth());
//        time.append(product.getTime().getHour());
//        time.append(product.getTime().getMinute());
//        time.append(product.getTime().getSecond());

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

        Double score = Double.parseDouble(result);
        template.opsForZSet().add(employee.getResource(), employee, score);
        return employee;
    }

    public Employee lastPointOfDriver(String resource){
        Set<Employee> product = template.opsForZSet().range(resource, (template.opsForZSet().size(resource)-1),
                template.opsForZSet().size(resource));

        Employee latPointOfProduct = null;
        for(Employee product1 : product){
            latPointOfProduct = product1;
        }

        return  latPointOfProduct;
    }
}
