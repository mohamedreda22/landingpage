package com.moaaz.modernhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceForMohamedAtefApplication {

    private static String SERVER_PORT = "9090";

    public static void main(String[] args) {
        SpringApplication.run(ECommerceForMohamedAtefApplication.class, args);

        System.out.println("Running Successfully On Port" + SERVER_PORT);
    }

}
