package ru.votrin.doctordata.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.votrin.doctordata.DAO.DictionaryDAO;
import ru.votrin.doctordata.DAO.PatientDAO;
import ru.votrin.doctordata.model.Localisation;
import ru.votrin.doctordata.model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * Created by wiseman on 08.04.17.
 */


@SpringUI
@Theme("valo")
public class DoctorUI extends UI{
    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private DictionaryDAO dictionaryDAO;

    private Grid<Patient> grid;
    private TextField fname;
    private TextField lname;
    private TextField sname;
    private Window wnd;
    private DateField birth;
    private ComboBox<String> sexField;
    private PatientLayout patientDataLayout;
    private TextArea diagnos;
    private DateField incDateCal;
    private DateField oucDateCal;
    private DateField operDateCal;
    private ComboBox<Localisation> locals;
    private TextField histNum;
    private HorizontalSplitPanel content;

    private static final ConfirmDialog confirmDialog = new ConfirmDialog();

    @Override
    protected void init(VaadinRequest request) {
        addWindow(confirmDialog);
        grid = new Grid<>(Patient.class);
        grid.setColumnOrder("hist_num", "first_name", "patronic", "second_name", "birth", "sex", "diagnos", "incoming_date", "outcoming_date", "operation_date");
        grid.getColumn("first_name").setCaption("Имя");
        grid.getColumn("patronic").setCaption("Отчество");
        grid.getColumn("second_name").setCaption("Фамилия");
        grid.getColumn("birth").setCaption("Дата рождения");
        grid.getColumn("sex").setCaption("Пол");
        grid.getColumn("incoming_date").setCaption("Дата прибытия");
        grid.getColumn("outcoming_date").setCaption("Дата выписки");
        grid.getColumn("diagnos").setCaption("Диагноз");
        grid.getColumn("operation_date").setCaption("Дата операции");
        grid.getColumn("hist_num").setCaption("№ и.б.");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.removeColumn("ptnt_id");
        grid.removeColumn("loc_loc_id");
        grid.setWidth("100%");

        grid.addSelectionListener(event -> {
            Set<Patient> items = event.getAllSelectedItems();
            confirmDialog.setItems(items);
            confirmDialog.show();
        });
        HorizontalLayout searchLine = new HorizontalLayout();

        TextField filter = new TextField();
        filter.setPlaceholder("Поиск...");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listPatient(e.getValue()));

        Button addButton = new Button(VaadinIcons.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.addClickListener(this::addPatientDialog);

        searchLine.addComponentsAndExpand(filter);
        searchLine.addComponent(addButton);
        searchLine.setWidth("100%");

        DoctorMenu menuBar = new DoctorMenu();
        menuBar.setWidth("100%");

        VerticalLayout dataLayout = new VerticalLayout();

        dataLayout.addComponent(searchLine);
        dataLayout.addComponentsAndExpand(grid);
        listPatient("");

        content = new HorizontalSplitPanel();

        content.addComponent(dataLayout);
        content.setSizeFull();

        patientDataLayout = new PatientLayout(this, dictionaryDAO, patientDAO);


        content.addComponent(patientDataLayout);

        dataLayout.setSizeFull();

        grid.addItemClickListener((ItemClickListener<Patient>) itemClick -> {
            patientDataLayout.setPatient(itemClick.getItem());
            System.out.println(itemClick.getItem());
        });

        content.setSplitPosition(90, Unit.PERCENTAGE);
        setContent(content);
    }

    private void addPatientDialog(Button.ClickEvent e) {
        wnd = new Window("Добавить пациента");
        wnd.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        VerticalLayout layout = new VerticalLayout();
        wnd.setContent(layout);

        wnd.center();

        wnd.setModal(true);
        wnd.setWidth("50%");
        wnd.setHeight("100%");

        fname = new TextField();
        sname = new TextField();
        lname = new TextField();

        sexField = new ComboBox<String>();
        sexField.setPlaceholder("Пол");
        sexField.setItems("Муж", "Жен");
/*
        fname.setWidth("350px");
        sname.setWidth("350px");
        lname.setWidth("350px");
*/
        histNum = new TextField();
        histNum.setPlaceholder("История болезни");

        incDateCal = new DateField();
        incDateCal.setDateFormat("dd-MM-yyyy");
        incDateCal.setPlaceholder("дд-мм-гггг");

        oucDateCal = new DateField();
        oucDateCal.setDateFormat("dd-MM-yyyy");
        oucDateCal.setPlaceholder("дд-мм-гггг");

        operDateCal = new DateField();
        operDateCal.setDateFormat("dd-MM-yyyy");
        operDateCal.setPlaceholder("дд-мм-гггг");

        birth = new DateField();
        birth.setDateFormat("dd-MM-yyyy");
        birth.setPlaceholder("дд-мм-гггг");

        HorizontalLayout fnameLine = new HorizontalLayout();
        HorizontalLayout snameLine = new HorizontalLayout();
        HorizontalLayout lnameLine = new HorizontalLayout();
        HorizontalLayout bLayout = new HorizontalLayout();
        HorizontalLayout incLayout = new HorizontalLayout();
        HorizontalLayout oucLayout = new HorizontalLayout();
        HorizontalLayout operLayout = new HorizontalLayout();

        fnameLine.addComponentsAndExpand(fname);
        fname.setPlaceholder("Имя");

        snameLine.addComponentsAndExpand(sname);
        sname.setPlaceholder("Отчество");

        lnameLine.addComponentsAndExpand(lname);
        lname.setPlaceholder("Фамилия");

        Button addPatient = new Button("Добавить пациента");
        addPatient.setSizeFull();

        bLayout.addComponent(new Label("Дата рождения:"));
        bLayout.addComponent(birth);

        incLayout.addComponent(new Label("Дата прибытия:"));
        incLayout.addComponent(incDateCal);

        operLayout.addComponent(new Label("Дата операции:"));
        operLayout.addComponent(operDateCal);

        oucLayout.addComponent(new Label("Дата выписки:"));
        oucLayout.addComponent(oucDateCal);

        locals = new ComboBox<Localisation>();
        locals.setPlaceholder("Выберите локализацию");
        locals.setSizeFull();

        locals.setItems(dictionaryDAO.getLocatiosations());

        addPatient.addClickListener(this::createPatient);
        addPatient.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        diagnos = new TextArea();
        diagnos.setPlaceholder("Введите диагноз...");
        //diagnos.setRows(3);
        diagnos.setSizeFull();

        layout.addComponentsAndExpand(fname);
        layout.addComponentsAndExpand(sname);
        layout.addComponentsAndExpand(lname);
        layout.addComponentsAndExpand(locals);
        layout.addComponentsAndExpand(diagnos);

        layout.addComponentsAndExpand(new HorizontalLayout(sexField, histNum));

        layout.addComponentsAndExpand(bLayout);
        layout.addComponentsAndExpand(incLayout);
        layout.addComponentsAndExpand(operLayout);
        layout.addComponentsAndExpand(oucLayout);
        layout.addComponentsAndExpand(addPatient);

        //layout.setSpacing(false);
        addWindow(wnd);
    }

    private void createPatient(Button.ClickEvent clickEvent) {
        String f_name = fname.getValue();
        String s_name = sname.getValue();
        String l_name = lname.getValue();
        String birthDate = birth.getValue().toString();
        String sex = sexField.getValue();
        Long loc_id = locals.getValue().getLoc_id();

        String hist = histNum.getValue();
        String diag = diagnos.getValue();
        String incDate = incDateCal.getValue().toString();
        String oucDate = oucDateCal.getValue().toString();
        String operDate = operDateCal.getValue().toString();

        if (!(StringUtils.isEmpty(f_name) && StringUtils.isEmpty(s_name) && StringUtils.isEmpty(l_name))) {
            patientDAO.createPatient(f_name, s_name, l_name, hist, birthDate, sex, loc_id, diag, incDate, oucDate, operDate);
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

    public void listPatient(String filterText) {
        List<Patient> patients;
        if(StringUtils.isEmpty(filterText)) {
            patients = patientDAO.findAll();
        } else {
            patients = patientDAO.findWithFilter(filterText);
        }
        grid.setItems(patients);
    }
    public void setSplitPosition(int position) {
        content.setSplitPosition(position, Unit.PERCENTAGE);
    }
    public float getSplitPosition() {
        return content.getSplitPosition();
    }
}

