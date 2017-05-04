package ru.votrin.doctordata.UI;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votrin.doctordata.DAO.DictionaryDAO;
import ru.votrin.doctordata.model.Localisation;
import ru.votrin.doctordata.model.Patient;
import ru.votrin.doctordata.model.PatientDiagnos;

/**
 * Created by wiseman on 14.04.17.
 */
@SpringUI
public class PatientLayout extends VerticalLayout {

    private final TextField firstName;
    private final TextField lastName;
    private final TextField secondName;
    private final Label loc_lab;
    private final DictionaryDAO dictionaryDAO;
    private Grid<PatientDiagnos> diagnosGrid;

    public static final String PATIENT_FRN_ID = "ptnt_ptnt_id";
    public static final String HISTORY_NUM = "hist_num";
    public static final String INCOMING_DATE = "incoming_date";
    public static final String OUTCOMING_DATE= "outcoming_date";
    public static final String OPERATION_DATE = "operation_date";
    public static final String LOCALISATION = "localisation";
    public static final String DIAGNOS = "diagnos";

    private static final String[] gridColumnCaptions = {HISTORY_NUM, INCOMING_DATE, OUTCOMING_DATE, OPERATION_DATE, LOCALISATION, DIAGNOS};

    @Autowired
    public PatientLayout(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
        firstName = new TextField();
        lastName = new TextField();
        secondName = new TextField();

        ComboBox<Object> sex = new ComboBox<>();
        sex.setItems("Муж", "Жен");
        sex.setSelectedItem("Жен");

        VerticalLayout infoSpace = new VerticalLayout();
        HorizontalLayout hlf = new HorizontalLayout(new Label("Имя"), firstName);
        infoSpace.addComponent(hlf);

        HorizontalLayout hls = new HorizontalLayout(new Label("Фамилия"), secondName);

        infoSpace.addComponent(hls);

        HorizontalLayout hll = new HorizontalLayout(new Label("Отчество"), lastName);
        infoSpace.addComponent(hll);

        loc_lab = new Label("");
        infoSpace.addComponent(loc_lab);

        addComponent(infoSpace);
//        initDiagnosGrid();
    }

    private void initDiagnosGrid() {
        diagnosGrid = new Grid<>(PatientDiagnos.class);
        diagnosGrid.setSizeFull();
        diagnosGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        diagnosGrid.setColumns(HISTORY_NUM,
                                INCOMING_DATE,
                                OUTCOMING_DATE,
                                OPERATION_DATE,
                                DIAGNOS,
                                LOCALISATION);

        diagnosGrid.getColumn(OUTCOMING_DATE).setCaption("Дата выписки");
        diagnosGrid.getColumn(INCOMING_DATE).setCaption("Дата прибытия");
        diagnosGrid.getColumn(DIAGNOS).setCaption("Диагноз");
        diagnosGrid.getColumn(LOCALISATION).setCaption("Локализация");
        diagnosGrid.getColumn(OPERATION_DATE).setCaption("Дата операции");
        diagnosGrid.getColumn(HISTORY_NUM).setCaption("н.записи");

        addComponentsAndExpand(diagnosGrid);
    }

    public void setPatient(Patient newPatient) {

        firstName.setValue(newPatient.getFirst_name());
        lastName.setValue(newPatient.getSecond_name());
        secondName.setValue(newPatient.getPatronic());
        Localisation loc = dictionaryDAO.findLocalisationById(newPatient.getLoc_loc_id());
        loc_lab.setValue(loc.getDescription());
        /*
        if (null != patient) {
            List<PatientDiagnos> recs = patientDAO.getDiagnosByPtntId(patient.getPtnt_id());
            diagnosGrid.setItems(recs);
        }
        */
    }
}
