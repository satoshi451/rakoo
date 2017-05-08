package ru.votrin.doctordata.model;

/**
 * Created by wiseman on 04.05.17.
 */
public class Localisation {
    private Long loc_id;
    private String description;

    public Localisation(Long loc_id,
                        String description) {
        this.loc_id = loc_id;
        this.description = description;
    }

    public Localisation() {

    }

    public Long getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(Long loc_id) {
        this.loc_id = loc_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
