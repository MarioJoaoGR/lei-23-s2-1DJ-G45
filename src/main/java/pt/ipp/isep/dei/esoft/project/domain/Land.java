package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * The type Land.
 */
public class Land extends Property implements Serializable {

    /**
     * Instantiates a new Land.
     */
    public Land() {
    }

    /**
     * Instantiates a new Land.
     *
     * @param id             the id
     * @param location       the location
     * @param area           the area
     * @param distanceCenter the distance center
     * @param photographs    the photographs
     * @param owner          the owner
     */
    public Land(int id, String location, double area, double distanceCenter,List<String> photographs,
                  Owner owner) {
        super( id,location, area, distanceCenter,photographs,owner);
    }

    // Constructor for salecontroller

    /**
     * Instantiates a new Land.
     *
     * @param location       the location
     * @param area           the area
     * @param distanceCenter the distance center
     * @param photographs    the photographs
     * @param owner          the owner
     */
    public Land( String location, double area, double distanceCenter,List<String> photographs,
                  Owner owner) {
        super( location, area, distanceCenter,photographs,owner);
    }




}
