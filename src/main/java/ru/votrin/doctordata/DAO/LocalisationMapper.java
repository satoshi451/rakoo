package ru.votrin.doctordata.DAO;

import ru.votrin.doctordata.model.Localisation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wiseman on 04.05.17.
 */
public class LocalisationMapper implements org.springframework.jdbc.core.RowMapper<Localisation> {

    @Override
    public Localisation mapRow(ResultSet rs, int rownum) throws SQLException {
        return new Localisation(rs.getLong("loc_id"),
                                rs.getString("description"));
    }
}
