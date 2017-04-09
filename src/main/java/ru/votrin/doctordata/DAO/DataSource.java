package ru.votrin.doctordata.DAO;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wiseman on 09.04.17.
 */


@Component
public class DataSource extends BasicDataSource {
    @Value("${spring.datasource.driver-class-name}")
    private String className;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public DataSource() {
        super();
        setDriverClassName(this.className);
        setUrl(url);
        setUsername(username);
        setPassword(password);
    }
}
