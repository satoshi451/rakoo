package ru.votrin.doctordata.DAO;

import ru.votrin.doctordata.UI.PatientLayout;
import ru.votrin.doctordata.model.PatientDiagnos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wiseman on 14.04.17.
 */
public class DiagnosMapper implements RowMapper<PatientDiagnos> {
    @Override
    public PatientDiagnos mapRow(ResultSet rs,
                                 int i) throws SQLException {
        return new PatientDiagnos(rs.getLong(PatientLayout.PATIENT_FRN_ID),
                                    rs.getDate(PatientLayout.INCOMING_DATE),
                                    rs.getDate(PatientLayout.OUTCOMING_DATE),
                                    rs.getString(PatientLayout.DIAGNOS),
                                    rs.getDate(PatientLayout.OPERATION_DATE),
                                    rs.getString(PatientLayout.LOCALISATION),
                                    rs.getInt(PatientLayout.HISTORY_NUM));
    }
}
