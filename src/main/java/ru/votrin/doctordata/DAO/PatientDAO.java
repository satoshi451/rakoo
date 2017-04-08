package ru.votrin.doctordata.DAO;

import ru.votrin.doctordata.model.Patient;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wiseman on 08.04.17.
 */
public interface PatientDAO {
    LinkedList<Patient> findAll();
}

