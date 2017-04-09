package ru.votrin.doctordata.DAO;

import com.sun.deploy.Environment;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by wiseman on 09.04.17.
 */


@Component
@Configuration
@PropertySource("classpath:application.properties")
public class DataSource extends BasicDataSource {

//    @Value("${spring.datasource.driver-class-name}")
    private String className = "org.postgresql.Driver";

//    @Value("${spring.datasource.url}")
    private String url = "jdbc:postgresql://trollcave:5432/info";

//    @Value("${spring.datasource.username}")
    private String username = "admin";

//    @Value("${spring.datasource.password}")
    private String password = "admin";

    public DataSource() {
        super();
        setDriverClassName(this.className);
        setUrl(url);
        setUsername(username);
        setPassword(password);
    }
/*
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
 */
}