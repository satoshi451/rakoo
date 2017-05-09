package ru.votrin.doctordata.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by wiseman on 29.04.17.
 */
public abstract class AbstractDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        jdbcTemplate.setDataSource(dataSource);
    }

}
