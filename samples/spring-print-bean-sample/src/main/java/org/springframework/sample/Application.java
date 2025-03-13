package org.springframework.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "org.springframework.sample")
public class Application {
    @Autowired
    TestComponentBean componentBean;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
class TestComponentBean {
}

