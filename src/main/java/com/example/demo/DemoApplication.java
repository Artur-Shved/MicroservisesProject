package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.repository.DriverDao;
import com.example.demo.repository.DriverDaoImpl;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class DemoApplication {

    Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    DriverDao dao;


    @KafkaListener(topics = "msg")
    public void orderListener(ConsumerRecord<String, Employee> record){
        Gson gson = new Gson();
        logger.info(String.valueOf(record.partition()));
        logger.info(record.key());
        logger.info(String.valueOf(record.value()));
        Employee employee = record.value();
        dao.saveDriver(employee);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
