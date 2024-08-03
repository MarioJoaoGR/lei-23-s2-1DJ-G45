package pt.ipp.isep.dei.esoft.project.domain.mappers;

import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OwnerDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Owner;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * The VisitRequestMapper class is responsible for mapping VisitRequest objects to VisitRequestDTO objects and vice versa.
 */
public class VisitRequestMapper {

    /**
     * Maps a VisitRequest object to a VisitRequestDTO object.
     *
     * @param visitRequest the VisitRequest object to be mapped
     * @return the corresponding VisitRequestDTO object
     */
    public VisitRequestDTO toDTO(VisitRequest visitRequest) {

        VisitRequestDTO visitRequestDTO = new VisitRequestDTO();
        visitRequestDTO.setDate(visitRequest.getDate());
        visitRequestDTO.setEmail(visitRequest.getEmail());
        visitRequestDTO.setUserName(visitRequest.getUserName());
        visitRequestDTO.setTimeSlot(visitRequest.getTimeSlot());
        AgentMapper agentMapper = new AgentMapper();
        if (visitRequest.getAgent()!=null){
            visitRequestDTO.setAgentDTO( agentMapper.toDTO(visitRequest.getAgent()));

        }
        visitRequestDTO.setStatus(visitRequest.getStatus());
        return visitRequestDTO;

    }

    /**
     * Maps a VisitRequestDTO object to a VisitRequest object.
     *
     * @param dto the VisitRequestDTO object to be mapped
     * @return the corresponding VisitRequest object
     */
    public VisitRequest fromDTO(VisitRequestDTO dto) {
        AgentMapper agentMapper = new AgentMapper();
        Agent agent = agentMapper.fromDTO(dto.getAgentDTO());
        return new VisitRequest(dto.getDate(),dto.getTimeSlot(),dto.getUserName(),dto.getEmail(),agent);
    }

    /**
     * Maps a list of VisitRequest objects to a list of VisitRequestDTO objects.
     *
     * @param visitRequests the list of VisitRequest objects to be mapped
     * @return the corresponding list of VisitRequestDTO objects
     */
    public List<VisitRequestDTO> toDTO(List<VisitRequest> visitRequests) {
        List<VisitRequestDTO> visitRequestDTOS = new ArrayList<>();
        for (VisitRequest visitRequest : visitRequests) {
           visitRequestDTOS.add(this.toDTO(visitRequest));
        }
        return visitRequestDTOS;
    }


    /**
     * Maps a list of VisitRequestDTO objects to a list of VisitRequest objects.
     *
     * @param visitRequests the list of VisitRequestDTO objects to be mapped
     * @return the corresponding list of VisitRequest objects
     */
    public List<VisitRequest> fromDTO(List<VisitRequestDTO> visitRequests) {
        List<VisitRequest> visitRequest = new ArrayList<>();
        for (VisitRequestDTO visitRequestDTOS : visitRequests) {
            visitRequest.add(this.fromDTO(visitRequestDTOS));
        }
        return visitRequest;
    }
}
