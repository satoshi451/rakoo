package ru.votrin.doctordata.DAO;

import org.apache.commons.dbcp.BasicDataSource;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;

/**
 * Created by wiseman on 09.04.17.
 */


@Component
@Configuration
@PropertySource("classpath:application.properties")
public class DataSource extends BasicDataSource {

    @Value("${spring.datasource.driver-class-name}")
    private String className;// = "org.postgresql.Driver";

    @Value("${spring.datasource.url}")
    private String url;// = "jdbc:postgresql://localhost:5432/info";

    @Value("${spring.datasource.username}")
    private String username;// = "admin";

    @Value("${spring.datasource.password}")
    private String password;// = "admin";

    public DataSource() {
    }

    @PostConstruct
    public void init() {
        setDriverClassName(className);
        setUrl(url);
        setUsername(username);
        setPassword(password);

        System.out.println(className);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }

}