package com.example.datatier_sep3;

import com.example.datatier_sep3.core.ModelFactory;
import com.example.datatier_sep3.networking.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataTierSep3Application {

    public static void main(String[] args) {
        SpringApplication.run(DataTierSep3Application.class, args);
        ModelFactory modelFactory = new ModelFactory();
        Server server = new Server(modelFactory.getUserModel());
        server.run();
    }

}
