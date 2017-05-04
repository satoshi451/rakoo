package ru.votrin.doctordata.DAO;

import ru.votrin.doctordata.model.Localisation;

import java.util.List;

/**
 * Created by wiseman on 29.04.17.
 */
public interface DictionaryDAO {
    List<Localisation> getLocatiosations();

    Localisation findLocalisationById(Long loc_loc_id);
}
