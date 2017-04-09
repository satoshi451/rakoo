package ru.votrin.doctordata.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votrin.doctordata.DAO.PatientDAO;
import ru.votrin.doctordata.model.Patient;

import java.util.List;

/**
 * Created by wiseman on 08.04.17.
 */


@SpringUI
@Theme("valo")
public class DoctorUI extends UI{

    @Autowired
    private PatientDAO patientDAO;
    private Grid<Patient> grid;
    @Override
    protected void init(VaadinRequest request) {
       // setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
        setContent(grid);
        listPatient();
    }

    private void listPatient() {
        List<Patient> patients = patientDAO.findAll();
        grid.setItems(patients);
    }
}

