package ru.votrin.doctordata.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    private TextField filter;
    private Button addButton;

    @Override
    protected void init(VaadinRequest request) {
        grid = new Grid<>(Patient.class);
        grid.setWidth("100%");

        HorizontalLayout searchLine = new HorizontalLayout();

        filter = new TextField();
        filter.setPlaceholder("Поиск...");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listPatient(e.getValue()));

        addButton = new Button(VaadinIcons.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.addClickListener(e -> addClicked(e));

        searchLine.addComponentsAndExpand(filter);
        searchLine.addComponent(addButton);
        searchLine.setWidth("100%");
        VerticalLayout mainLayout = new VerticalLayout(searchLine, grid);
        listPatient("");
        setContent(mainLayout);
    }

    private void addClicked(Button.ClickEvent e) {
        Window wnd = new Window("Добавить пациента");
        VerticalLayout layout = new VerticalLayout();

        wnd.setContent(layout);
        layout.addComponent(new Label("Karamba!"));

        wnd.center();
        addWindow(wnd);
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

