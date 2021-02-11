package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverDao {
    private static final String HASH_KEY = "Consumer";

    @Autowired
    private RedisTemplate<String, Object> template;

    public List<Object> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

//    public Product save(Product product){
//        template.opsForHash().put(HASH_KEY, product.getResource(), product);
//        return product;
//    }

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
}
