package ru.votrin.doctordata.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.votrin.doctordata.model.Patient;


import java.util.List;

/**
 * Created by wiseman on 08.04.17.
 */

@Repository("PatientDAO")
public class PatientDAOImpl implements PatientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {

    }

    @Override
    public List<Patient> findAll() {
        String st = "select * from cases.patients";

        List<Patient> data = jdbcTemplate.query(st, new PatientMapper());
        return data;
    }
}
