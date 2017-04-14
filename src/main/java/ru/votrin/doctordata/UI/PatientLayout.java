package ru.votrin.doctordata.UI;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votrin.doctordata.DAO.PatientDAO;
import ru.votrin.doctordata.model.Patient;
import ru.votrin.doctordata.model.PatientDiagnos;

import java.util.List;

/**
 * Created by wiseman on 14.04.17.
 */
public class PatientLayout extends VerticalLayout {

    private final PatientDAO patientDAO;

    private final TextField firstName;
    private final TextField lastName;
    private final TextField secondName;
    private Grid<PatientDiagnos> diagnosGrid;
    private Patient patient;

    @Autowired
    public PatientLayout(PatientDAO patientDAO) {
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

        addComponent(infoSpace);

        initDiagnosGrid();
        addComponentsAndExpand(diagnosGrid);
        this.patientDAO = patientDAO;
    }

    private void initDiagnosGrid() {
        diagnosGrid = new Grid<>(PatientDiagnos.class);
        diagnosGrid.setSelectionMode(Grid.SelectionMode.MULTI);
/*
        diagnosGrid.setColumns("ptnt_ptnt_id",
                                "incoming_date",
                                "outcoming_date",
                                "diagnos",
                                "operation_date",
                                "licalisation",
                                "hist_num");
*/
        if (null != patient) {
            List<PatientDiagnos> recs = patientDAO.getDiagnosByPtntId(patient.getPtnt_id());
            diagnosGrid.setItems(recs);

            System.out.println(recs);
        }
    }

    public void setPatient(Patient patient) {
        this.patient = patient;

        firstName.setValue(patient.getFirst_name());
        lastName.setValue(patient.getSecond_name());
        secondName.setValue(patient.getPatronic());

        initDiagnosGrid();
    }
}
