package main.java;


import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException, SQLException {

        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/resources/beans.fxml");

        Services services = context.getBean("SqlRequest", Services.class);
        services.all();
        services = context.getBean("ScanCSV", ScanCSV.class);
        services.all();
    }
}
