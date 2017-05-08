package ru.votrin.doctordata.UI;

import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votrin.doctordata.DAO.DictionaryDAO;
import ru.votrin.doctordata.DAO.PatientDAO;
import ru.votrin.doctordata.model.Localisation;
import ru.votrin.doctordata.model.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Created by wiseman on 14.04.17.
 */
@SpringUI
public class PatientLayout extends VerticalLayout {

    private final TextField firstName;
    private final TextField lastName;
    private final TextField secondName;
    private final ComboBox<Localisation> loc_lab;
    private final DictionaryDAO dictionaryDAO;
    private final DateField incDate;
    private final DateField oucDate;
    private final DateField bDate;
    private final DateField operDate;
    private final TextArea diag;
    private final TextField histNum;
    private final PatientDAO patientDAO;
    private final DoctorUI parent;
    private final Button splitMove;
    private final HorizontalLayout bLayout;
    private final HorizontalLayout incLayout;
    private final HorizontalLayout oucLayout;
    private final HorizontalLayout operLayout;
    private final HorizontalLayout hLayout;
    private final HorizontalLayout hlf;
    private final HorizontalLayout hls;
    private final HorizontalLayout hll;
    private final Button lockB;
    private final List<Localisation> localisations;

    private boolean isMinimized;
    private boolean isEnabled;
    private boolean isTextChanged;

    public static final String PATIENT_FRN_ID = "ptnt_ptnt_id";
    public static final String HISTORY_NUM = "hist_num";
    public static final String INCOMING_DATE = "incoming_date";
    public static final String OUTCOMING_DATE= "outcoming_date";
    public static final String OPERATION_DATE = "operation_date";
    public static final String LOCALISATION = "localisation";
    public static final String DIAGNOS = "diagnos";

    private Patient patient;

    @Autowired
    public PatientLayout(DoctorUI components,
                         DictionaryDAO dictionaryDAO,
                         PatientDAO patientDAO) {
        this.parent = components;
        this.dictionaryDAO = dictionaryDAO;
        this.patientDAO = patientDAO;

        localisations = this.dictionaryDAO.getLocatiosations();

        isEnabled = true;
        isMinimized = true;
        isTextChanged = false;

        firstName = new TextField();
        lastName = new TextField();
        secondName = new TextField();
        splitMove = new Button(VaadinIcons.ARROW_LEFT);
        splitMove.addClickListener(click -> {
            if (isMinimized) {
                maximize();
            } else {
                minimize();
            }
        });
        lockB = new Button(VaadinIcons.LOCK);

        addComponent(new HorizontalLayout(splitMove, lockB));

        ComboBox<Object> sex = new ComboBox<>();
        sex.setItems("Муж", "Жен");
        sex.setSelectedItem("Жен");

        histNum = new TextField();
        hLayout = new HorizontalLayout(new Label("Ист. болезни:"), histNum);
        addComponent(hLayout);

        hlf = new HorizontalLayout(new Label("Имя"), firstName);
        addComponent(hlf);
        hls = new HorizontalLayout(new Label("Фамилия"), secondName);
        addComponent(hls);
        hll = new HorizontalLayout(new Label("Отчество"), lastName);
        addComponent(hll);

        loc_lab = new ComboBox<Localisation>();
        loc_lab.setItems(localisations);
        loc_lab.setSelectedItem(new Localisation());
        loc_lab.setWidth("100%");
        addComponentsAndExpand(loc_lab);


        diag = new TextArea();
        addComponentsAndExpand(diag);
        diag.setSizeFull();

        bDate = new DateField();
        incDate = new DateField();
        oucDate = new DateField();
        operDate = new DateField();

        bLayout = new HorizontalLayout(new Label("Дата рождения:"), bDate);
        addComponentsAndExpand(bLayout);

        incLayout = new HorizontalLayout(new Label("Дата поступления:"), incDate);
        addComponentsAndExpand(incLayout);
        oucLayout = new HorizontalLayout(new Label("Дата выписки:"), oucDate);
        addComponentsAndExpand(oucLayout);
        operLayout = new HorizontalLayout(new Label("Дата операции:"), operDate);
        addComponentsAndExpand(operLayout);

        lockB.addClickListener(click -> {
            if (null != patient) {
                firstName.setEnabled(isEnabled);
                lastName.setEnabled(isEnabled);
                secondName.setEnabled(isEnabled);
                histNum.setEnabled(isEnabled);
                bDate.setEnabled(isEnabled);
                incDate.setEnabled(isEnabled);
                oucDate.setEnabled(isEnabled);
                operDate.setEnabled(isEnabled);
                diag.setEnabled(isEnabled);
                loc_lab.setEnabled(isEnabled);

                if (isEnabled) {
                    enableAll();
                } else {
                    updatePatient();
                    disableAll();
                }
            } else {
                Notification.show("Пациент не выбран", Notification.Type.WARNING_MESSAGE);
            }

        });
        disableAll();
        hideAll();
        addInputListeners();
    }

    private void addInputListeners() {
        HasValue.ValueChangeListener<String> textChangeListener = (HasValue.ValueChangeListener<String>) event -> {
            if (!isEnabled && !event.getOldValue().equals("")) {
                System.out.println(event.getValue() + " , [text changed]");
                isTextChanged = true;
            }
        };

        HasValue.ValueChangeListener<LocalDate> dateChangeListener = (HasValue.ValueChangeListener<LocalDate>) event -> {
            if (!isEnabled && null != event.getOldValue()) {
                System.out.println(event.getValue() + " , [text changed]");
                isTextChanged = true;
            }
        };

        firstName.addValueChangeListener(textChangeListener);
        secondName.addValueChangeListener(textChangeListener);
        lastName.addValueChangeListener(textChangeListener);
        histNum.addValueChangeListener(textChangeListener);
        diag.addValueChangeListener(textChangeListener);

        loc_lab.addValueChangeListener((HasValue.ValueChangeListener<Localisation>) event -> {
            if (!isEnabled && !event.getOldValue().equals("null")) {
                System.out.println(event.getValue() + " , [text changed]");
                isTextChanged = true;
            }
        });

        bDate.addValueChangeListener(dateChangeListener);
        incDate.addValueChangeListener(dateChangeListener);
        oucDate.addValueChangeListener(dateChangeListener);
        operDate.addValueChangeListener(dateChangeListener);
        bDate.addValueChangeListener(dateChangeListener);


    }

    private void updatePatient() {
        System.out.println("ISTEXTCHANGE: " + isTextChanged);
        if (isTextChanged) {
            patientDAO.update(patient.getPtnt_id(), firstName.getValue(), lastName.getValue(), secondName.getValue(), histNum.getValue(), bDate.getValue(), incDate.getValue(), oucDate.getValue(), operDate.getValue(), diag.getValue());
            parent.listPatient("");
        }
    }

    private void hideAll(){
        hlf.setVisible(false);
        hls.setVisible(false);
        hll.setVisible(false);
        hLayout.setVisible(false);
        bLayout.setVisible(false);
        incLayout.setVisible(false);
        oucLayout.setVisible(false);
        operLayout.setVisible(false);
        diag.setVisible(false);
        lockB.setVisible(false);
        loc_lab.setVisible(false);
    }
    private void showAll() {
        hlf.setVisible(true);
        hls.setVisible(true);
        hll.setVisible(true);
        hLayout.setVisible(true);
        bLayout.setVisible(true);
        incLayout.setVisible(true);
        oucLayout.setVisible(true);
        operLayout.setVisible(true);
        diag.setVisible(true);
        lockB.setVisible(true);
        loc_lab.setVisible(true);
    }
    private void disableAll(){
        lockB.setIcon(VaadinIcons.LOCK);

        firstName.setEnabled(false);
        lastName.setEnabled(false);
        secondName.setEnabled(false);

        incDate.setEnabled(false);
        oucDate.setEnabled(false);
        operDate.setEnabled(false);

        histNum.setEnabled(false);

        bDate.setEnabled(false);
        diag.setEnabled(false);
        loc_lab.setEnabled(false);

        isEnabled = true;

        isTextChanged = false;
    }
    private void enableAll() {
        lockB.setIcon(VaadinIcons.UNLOCK);
        isEnabled = false;

        firstName.setEnabled(true);
        lastName.setEnabled(true);
        secondName.setEnabled(true);

        incDate.setEnabled(true);
        oucDate.setEnabled(true);
        operDate.setEnabled(true);

        histNum.setEnabled(true);

        bDate.setEnabled(true);
        diag.setEnabled(true);
        loc_lab.setEnabled(true);
    }
    private void minimize() {
        parent.setSplitPosition(90);
        hideAll();
        splitMove.setIcon(VaadinIcons.ARROW_LEFT);
        isMinimized = true;
    }
    private void maximize() {
        parent.setSplitPosition(70);
        showAll();
        splitMove.setIcon(VaadinIcons.ARROW_RIGHT);
        isMinimized = false;
    }
    void setPatient(Patient patient) {
        maximize();
        disableAll();

        this.patient = patient;
        String nm = patient.getHist_num();
        if (null == nm) {
            nm = "";
        }

        histNum.setValue(nm);
        firstName.setValue(patient.getFirst_name());
        lastName.setValue(patient.getSecond_name());
        secondName.setValue(patient.getPatronic());

        Localisation loc = dictionaryDAO.findLocalisationById(patient.getLoc_loc_id());

        loc_lab.setValue(loc);

        bDate.setValue(patient.getBirth().toLocalDate());
        incDate.setValue(patient.getIncoming_date().toLocalDate());
        oucDate.setValue(patient.getOutcoming_date().toLocalDate());
        operDate.setValue(patient.getOperation_date().toLocalDate());

        diag.setValue(patient.getDiagnos());

        Localisation curLoc = null;

        for (Localisation i : localisations) {
            if (Objects.equals(i.getLoc_id(), this.patient.getLoc_loc_id())) {
                curLoc = i;
                break;
            }
        }

        loc_lab.setSelectedItem(curLoc);
        showAll();
    }
}
