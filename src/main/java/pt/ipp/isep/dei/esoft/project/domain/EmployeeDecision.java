package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.EmployeeDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * The type Employee decision.
 */
public class EmployeeDecision implements Serializable {


    /**
     * Create employee object employee.
     *
     * @param dto the dto
     * @return the employee
     */
    public Employee createEmployeeObject(EmployeeDTO dto) {
        try {
            String className = "pt.ipp.isep.dei.esoft.project.domain." + dto.getRole(); // Assuming DTO has a getRequestType() method to determine the object type
            Class<?> requestClass = Class.forName(className);
            Constructor<?> requestConstructor = requestClass.getConstructor(EmployeeDTO.class);

            return (Employee) requestConstructor.newInstance(dto);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {

            return null;
        }

    }





}
