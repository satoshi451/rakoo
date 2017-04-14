package ru.votrin.doctordata.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
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
    private TextField fname;
    private TextField lname;
    private TextField sname;
    private Window wnd;
    private DateField birth;
    private ComboBox<String> sexField;
    private DoctorMenu menuBar;
    private PatientLayout patientDataLayout;

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

        menuBar = new DoctorMenu();
        menuBar.setWidth("100%");

        VerticalLayout dataLayout = new VerticalLayout();

 //       mainLayout.addComponents(menuBar);
        dataLayout.addComponent(searchLine);
        dataLayout.addComponentsAndExpand(grid);
        listPatient("");

        HorizontalLayout content = new HorizontalLayout();
        content.addComponent(dataLayout);
        content.setSizeFull();

        patientDataLayout = new PatientLayout(patientDAO);

        content.addComponent(patientDataLayout);

        dataLayout.setSizeFull();
//        patientDataLayout.setSizeFull();

        grid.addItemClickListener((ItemClickListener<Patient>) itemClick -> {
            patientDataLayout.setPatient(itemClick.getItem());
            System.out.println(itemClick.getItem());
        });


        setContent(content);
    }

    private void addPatientDialog(Button.ClickEvent e) {
        wnd = new Window("Добавить пациента");
        wnd.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        VerticalLayout layout = new VerticalLayout();
        wnd.setContent(layout);

        wnd.center();

        wnd.setModal(true);
        wnd.setWidth("500");
        wnd.setHeight("400");

        fname = new TextField();
        sname = new TextField();
        lname = new TextField();
        sexField = new ComboBox<String>();
        sexField.setItems("Муж", "Жен");

        fname.setWidth("350px");
        sname.setWidth("350px");
        lname.setWidth("350px");

        HorizontalLayout fnameLine = new HorizontalLayout();
        HorizontalLayout snameLine = new HorizontalLayout();
        HorizontalLayout lnameLine = new HorizontalLayout();

        fnameLine.addComponent(new Label("Имя:"));
        fnameLine.addComponents(fname);

        snameLine.addComponent(new Label("Отчество:"));
        snameLine.addComponents(sname);

        lnameLine.addComponent(new Label("Фамилия:"));
        lnameLine.addComponents(lname);

        Button addPatient = new Button("Добавить пациента");
        birth = new DateField();
        birth.setDateFormat("dd-MM-yyyy");
        birth.setPlaceholder("дд-мм-гггг");

        addPatient.addClickListener(this::createPatient);
        addPatient.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        layout.addComponentsAndExpand(fnameLine);
        layout.addComponentsAndExpand(snameLine);
        layout.addComponentsAndExpand(lnameLine);

        layout.addComponent(sexField);
        layout.addComponent(birth);
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

