package ru.votrin.doctordata.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.votrin.doctordata.DAO.PatientDAO;
import ru.votrin.doctordata.model.Patient;

import java.util.LinkedList;
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
    private TextField filter;

    @Override
    protected void init(VaadinRequest request) {
        grid = new Grid<>(Patient.class);
        grid.setWidth("100%");
        filter = new TextField();
        filter.setWidth("100%");
        filter.setPlaceholder("Поиск...");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listPatient(e.getValue()));

        VerticalLayout mainLayout = new VerticalLayout(filter, grid);
        listPatient("");
        setContent(mainLayout);
    }

    private void listPatient(String filterText) {
        List<Patient> patients;
        if(StringUtils.isEmpty(filterText)) {
            patients = patientDAO.findAll();
        } else {
            patients = patientDAO.findWithFilter(filterText);
        }
        grid.setItems(patients);
    }
}

