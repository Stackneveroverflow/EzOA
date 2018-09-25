package cn.sweetyhut.ezoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EzoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzoaApplication.class, args);
    }
}
