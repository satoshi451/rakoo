package ru.votrin.doctordata.DAO;

import ru.votrin.doctordata.model.PatientDiagnos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wiseman on 14.04.17.
 */
public class DiagnosMapper implements RowMapper<PatientDiagnos> {
    @Override
    public PatientDiagnos mapRow(ResultSet resultSet,
                                 int i) throws SQLException {
        return null;
    }
}
