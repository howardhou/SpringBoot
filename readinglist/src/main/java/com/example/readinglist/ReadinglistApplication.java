package com.example.readinglist;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadinglistApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadinglistApplication.class, args);


        // Create the Flyway instance and point it to the database
        // https://flywaydb.org/documentation/api/
//        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./out/test", "sa", null).load();
//        flyway.migrate();
    }
}
