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
        String st = "select ptnt_id, first_name, second_name, patronic, birth, sex from cases.patients";

        List<Patient> data = jdbcTemplate.query(st, new PatientMapper());
        return data;
    }

    @Override
    public List<Patient> findWithFilter(String filterText) {
        StringBuilder st = new StringBuilder("select ptnt_id, first_name, second_name, patronic, birth, sex from cases.patients p ");
                st.append(" where lower(p.first_name) like lower('%").append(filterText).append("%')");
                st.append(" or lower(p.second_name) like lower('%").append(filterText).append("%')");
                st.append(" or lower(p.patronic) like lower('%").append(filterText).append("%')");

        System.out.println(st.toString());
        List<Patient> data = jdbcTemplate.query(st.toString(), new PatientMapper());

        return data;
    }

    @Override
    public void createPatient(String fname,
                              String sname,
                              String lname,
                              String birth,
                              String sex) {
        StringBuilder sb = new StringBuilder("select cases.create_patient('");
        sb.append(fname).append("','").append(lname).append("','").append(sname).append("','").append(birth).append("','").append(sex).append("')");
        System.out.println(sb.toString());
        jdbcTemplate.execute(sb.toString());
    }
}
