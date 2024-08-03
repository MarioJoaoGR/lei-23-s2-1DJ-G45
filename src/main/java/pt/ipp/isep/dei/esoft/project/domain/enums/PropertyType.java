package pt.ipp.isep.dei.esoft.project.domain.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * The enum Property type.
 */
public enum PropertyType {
    /**
     * Land property type.
     */
    LAND("Land"),
    /**
     * Apartment property type.
     */
    APARTMENT("Apartment"),
    /**
     * House property type.
     */
    HOUSE("House");

    private final String displayName;

    PropertyType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets display Propery type.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets all fields.
     *
     * @return the all fields
     */
    public static List<String> getAllFields() {
        List<String> fields = new ArrayList<>();
        for (PropertyType propertyType : PropertyType.values()) {
            fields.add(propertyType.getDisplayName());
        }
        return fields;
    }

}
