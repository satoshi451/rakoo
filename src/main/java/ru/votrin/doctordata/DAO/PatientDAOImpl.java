package ru.votrin.doctordata.DAO;

import org.springframework.stereotype.Repository;
import ru.votrin.doctordata.model.PatientDiagnos;
import ru.votrin.doctordata.model.Patient;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by wiseman on 08.04.17.
 */

@Repository("PatientDAO")
public class PatientDAOImpl extends AbstractDAO implements PatientDAO {

    @Override
    public List<Patient> findAll() {
        String st = "select * from cases.patients";

        return jdbcTemplate.query(st, new PatientMapper());
    }

    @Override
    public List<Patient> findWithFilter(String filterText) {
        StringBuilder st = new StringBuilder("select * ");
                st.append(" from cases.patients p ");
                st.append(" where lower(p.first_name) like lower('%").append(filterText).append("%')");
                st.append(" or lower(p.second_name) like lower('%").append(filterText).append("%')");
                st.append(" or lower(p.patronic) like lower('%").append(filterText).append("%')");

        System.out.println(st.toString());
        return jdbcTemplate.query(st.toString(), new PatientMapper());
    }


    @Override
    public void createPatient(String fname,
                              String sname,
                              String lname,
                              String birth,
                              String sex,
                              Long loc_id,
                              String diag,
                              String incDate,
                              String oucDate,
                              String operDate) {
        StringBuilder st = new StringBuilder("select cases.create_patient('");
        st.append(fname).append("','");
        st.append(lname).append("','");
        st.append(sname).append("','");
        st.append(birth).append("','");
        st.append(sex).append("','");
        st.append(loc_id).append("','");
        st.append(diag).append("','");
        st.append(incDate).append("','");
        st.append(oucDate).append("','");
        st.append(operDate).append("')");

        System.out.println(st.toString());
        jdbcTemplate.execute(st.toString());
    }

    @Override
    public List<PatientDiagnos> getDiagnosByPtntId(Long ptnt_id) {
        StringBuilder st = new StringBuilder("select h.* from");
        st.append(" (select h.ptnt_ptnt_id, max(h.hist_num) hn from cases.patient_history h");
        st.append(" group by h.ptnt_ptnt_id) as latest ");
        st.append(" join cases.patient_history h on h.hist_num = latest.hn");
        st.append(" where h.ptnt_ptnt_id = ").append(ptnt_id);

        System.out.println(st.toString());
        return jdbcTemplate.query(st.toString(), new DiagnosMapper());
    }
}
