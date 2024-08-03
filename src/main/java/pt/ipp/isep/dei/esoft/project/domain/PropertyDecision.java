package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * The type Property decision.
 */
public class PropertyDecision implements Serializable {


    /**
     * Create property object property.
     *
     * @param dto the dto
     * @return the property
     */
    public Property createPropertyObject(RequestDTO dto) {
        try {
            String className = "pt.ipp.isep.dei.esoft.project.domain." + dto.getRequestType(); // Assuming DTO has a getRequestType() method to determine the object type

            Class<?> requestClass = Class.forName(className);
            Constructor<?> requestConstructor = requestClass.getConstructor(AnnouncementDTO.class);

            return (Property) requestConstructor.newInstance(dto);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {

            return null;
        }

    }



}
