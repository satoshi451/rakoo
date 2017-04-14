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
    public PatientDiagnos mapRow(ResultSet rs,
                                 int i) throws SQLException {
        return new PatientDiagnos(rs.getLong("ptnt_ptnt_id"),
                                    rs.getDate("incoming_date"),
                                    rs.getDate("outcoming_date"),
                                    rs.getString("diagnos"),
                                    rs.getDate("operation_date"),
                                    rs.getString("localisation"),
                                    rs.getInt("hist_num"));
    }
}
