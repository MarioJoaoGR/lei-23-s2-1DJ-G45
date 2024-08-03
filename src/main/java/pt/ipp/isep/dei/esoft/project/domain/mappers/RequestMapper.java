package pt.ipp.isep.dei.esoft.project.domain.mappers;

import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.RequestApartment;
import pt.ipp.isep.dei.esoft.project.domain.RequestHouse;
import pt.ipp.isep.dei.esoft.project.domain.RequestLand;
import pt.ipp.isep.dei.esoft.project.domain.enums.PropertyType;

import java.util.ArrayList;
import java.util.List;

/**
 * The RequestMapper class is responsible for mapping Request objects to RequestDTO objects and vice versa.
 */
public class RequestMapper {

    /**
     * Maps a Request object to a RequestDTO object.
     *
     * @param request the Request object to be mapped
     * @return the corresponding RequestDTO object
     */
    public RequestDTO toDTO(Request request) {
        if (request instanceof RequestHouse) {
            return ((RequestHouse) request).toDTO();
        } else if (request instanceof RequestLand) {
            return ((RequestLand) request).toDTO();
        } else {
            return ((RequestApartment) request).toDTO();
        }
    }


    /**
     * Maps a list of Request objects to a list of RequestDTO objects.
     *
     * @param requestList the list of Request objects to be mapped
     * @return the corresponding list of RequestDTO objects
     */
    public List<RequestDTO> toDTO(List<Request> requestList) {
        List<RequestDTO> requestDTOS = new ArrayList<>();
        for (Request request : requestList) {
            requestDTOS.add(this.toDTO(request));
        }
        return requestDTOS;
    }


    public Request fromDTO(RequestDTO requestDTO) {
        if (requestDTO.getRequestType().equalsIgnoreCase(PropertyType.APARTMENT.getDisplayName())) {
            return new RequestApartment(requestDTO);
        } else if (requestDTO.getRequestType().equalsIgnoreCase(PropertyType.HOUSE.getDisplayName())) {
            return new RequestHouse(requestDTO);
        } else {
            return new RequestLand(requestDTO);
        }
    }

}
