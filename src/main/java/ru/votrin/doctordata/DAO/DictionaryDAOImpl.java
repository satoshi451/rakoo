package ru.votrin.doctordata.DAO;

import org.springframework.stereotype.Repository;
import ru.votrin.doctordata.model.Localisation;

import java.util.List;

/**
 * Created by wiseman on 29.04.17.
 */
@Repository("DictionaryDAO")
public class DictionaryDAOImpl extends AbstractDAO implements DictionaryDAO {
    @Override
    public List<Localisation> getLocatiosations() {
        String st = "SELECT * FROM cases.localisations";

        return jdbcTemplate.query(st, new LocalisationMapper());
    }

    @Override
    public Localisation findLocalisationById(Long loc_loc_id) {
        StringBuilder st = new StringBuilder("select * from cases.localisations ");
        st.append(" where loc_id = ").append(loc_loc_id);

        return jdbcTemplate.query(st.toString(), new LocalisationMapper()).get(0);
    }
}
