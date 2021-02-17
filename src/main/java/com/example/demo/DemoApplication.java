package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.DriverDao;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class DemoApplication {

    @Autowired
    DriverDao dao;


    @KafkaListener(topics = "msg")
    public void orderListener(ConsumerRecord<String, Employee> record){
        Gson gson = new Gson();
        System.out.println(record.partition());
        System.out.println(record.key());
        System.out.println(record.value());
        Employee employee = record.value();
        dao.save(employee);

        System.out.println(employee);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
