package ru.votrin.doctordata.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.votrin.doctordata.model.Patient;
import org.springframework.jdbc.*;

import java.util.LinkedList;

/**
 * Created by wiseman on 08.04.17.
 */
public class PatientDAOImpl implements PatientDAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public LinkedList<Patient> findAll() {
        String st = "select * from cases.patients";

        LinkedList<Patient> data = jdbcTemplate.query(st, new PatientMapper());
        return null;
    }
}
