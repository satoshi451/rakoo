package ru.votrin.doctordata.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import ru.votrin.doctordata.DAO.PatientDAO;
import ru.votrin.doctordata.model.Patient;

import java.util.LinkedList;

/**
 * Created by wiseman on 08.04.17.
 */


@SpringUI
@Theme("valo")
public class DoctorUI extends UI{

    private PatientDAO patientDAO;
    private Grid<Patient> grid;
    @Override
    protected void init(VaadinRequest request) {
       // setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
        setContent(grid);
        listPatient();
    }

    private void listPatient() {
        LinkedList<Patient> patients = patientDAO.findAll();
        grid.setItems(patients);
    }
}

