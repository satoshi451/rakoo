package ru.votrin.doctordata.DAO;

import ru.votrin.doctordata.model.Patient;

import java.util.List;

/**
 * Created by wiseman on 08.04.17.
 */
public interface PatientDAO {
    List<Patient> findAll();

    List<Patient> findWithFilter(String filterText);
}

