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
    private Button addButton;
    private TextField fname;
    private TextField lname;
    private TextField sname;
    private Window wnd;
    private DateField birth;
    private ComboBox<String> sexField;

    @Override
    protected void init(VaadinRequest request) {
        grid = new Grid<>(Patient.class);
        grid.setColumnOrder("first_name", "patronic", "second_name", "birth", "sex");
        grid.getColumn("first_name").setCaption("Имя");
        grid.getColumn("patronic").setCaption("Отчество");
        grid.getColumn("second_name").setCaption("Фамилия");
        grid.getColumn("birth").setCaption("Дата рождения");
        grid.getColumn("sex").setCaption("Пол");

        grid.removeColumn("ptnt_id");
        grid.setWidth("100%");

        HorizontalLayout searchLine = new HorizontalLayout();

        filter = new TextField();
        filter.setPlaceholder("Поиск...");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listPatient(e.getValue()));

        addButton = new Button(VaadinIcons.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.addClickListener(this::addPatientDialog);

        searchLine.addComponentsAndExpand(filter);
        searchLine.addComponent(addButton);
        searchLine.setWidth("100%");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addComponent(searchLine);
        mainLayout.addComponentsAndExpand(grid);
        listPatient("");
        setContent(mainLayout);
    }

    private void addPatientDialog(Button.ClickEvent e) {
        wnd = new Window("Добавить пациента");
        wnd.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        VerticalLayout layout = new VerticalLayout();
        wnd.setContent(layout);

        wnd.center();

        wnd.setModal(true);
        wnd.setWidth("500");
        wnd.setHeight("500");

        fname = new TextField();
        sname = new TextField();
        lname = new TextField();
        sexField = new ComboBox<String>();
        sexField.setItems("Муж", "Жен");

        layout.addComponent(new Label("Имя:"));
        layout.addComponentsAndExpand(fname);
        layout.addComponent(new Label("Фамилия:"));
        layout.addComponentsAndExpand(lname);
        layout.addComponent(new Label("Отчество:"));
        layout.addComponentsAndExpand(sname);

        Button addPatient = new Button("Добавить пациента");
        birth = new DateField();
        birth.setDateFormat("dd-MM-yyyy");
        birth.setPlaceholder("dd-MM-yyyy");
        layout.addComponent(sexField);
        layout.addComponent(birth);

        addPatient.addClickListener(this::createPatient);
        addPatient.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layout.addComponentsAndExpand(addPatient);
        addWindow(wnd);
    }

    private void createPatient(Button.ClickEvent clickEvent) {
        String f_name = fname.getValue();
        String s_name = sname.getValue();
        String l_name = lname.getValue();
        String birthDate = birth.getValue().toString();
        String sex = sexField.getValue();
        if (sex.equals("Муж"))
            sex = "m";
        else
            sex = "f";

        if (!(StringUtils.isEmpty(f_name) && StringUtils.isEmpty(s_name) && StringUtils.isEmpty(l_name))) {
            patientDAO.createPatient(f_name, s_name, l_name, birthDate, sex);
            wnd.close();
            fname.clear();
            sname.clear();
            lname.clear();

            Notification.show("Пациент добавлен");
            listPatient("");

        } else {
            Notification.show("Заполните, пожалуйста, все поля", Notification.Type.ERROR_MESSAGE);
        }
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

