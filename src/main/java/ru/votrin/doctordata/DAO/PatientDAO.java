package ru.votrin.doctordata.DAO;

import com.vaadin.ui.DateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import ru.votrin.doctordata.model.PatientDiagnos;
import ru.votrin.doctordata.model.Patient;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by wiseman on 08.04.17.
 */
public interface PatientDAO {
    List<Patient> findAll();

    List<Patient> findWithFilter(String filterText);

    void createPatient(String fname,
                       String sname,
                       String lname,
                       String hist,
                       String birth,
                       String sex,
                       Long loc_id,
                       String diag,
                       String incDate,
                       String oucDate,
                       String operDate);

    List<PatientDiagnos> getDiagnosByPtntId(Long ptnt_id);

    void update(Long ptnt_id,
                String firstName,
                String lastName,
                String secondName,
                String histNum,
                LocalDate bDate,
                LocalDate incDate,
                LocalDate oucDate,
                LocalDate operDate,
                String diag);

}

