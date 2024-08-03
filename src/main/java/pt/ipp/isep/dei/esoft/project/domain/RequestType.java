package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * The type Request type.
 */
public class RequestType implements Serializable {

    /**
     * Create request object request.
     *
     * @param dto the dto
     * @return the request
     */
    public Request createRequestObject(RequestDTO dto) {
        try {
            String className = "pt.ipp.isep.dei.esoft.project.domain.Request" + dto.getRequestType(); // Assuming DTO has a getRequestType() method to determine the object type

            Class<?> requestClass = Class.forName(className);
            Constructor<?> requestConstructor = requestClass.getConstructor(RequestDTO.class);

            return (Request) requestConstructor.newInstance(dto);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {

            return null;
        }

    }
}