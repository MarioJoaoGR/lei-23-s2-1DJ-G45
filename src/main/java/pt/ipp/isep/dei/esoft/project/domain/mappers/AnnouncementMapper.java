package pt.ipp.isep.dei.esoft.project.domain.mappers;


import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * The AnnouncementMapper class is responsible for mapping Announcement objects to AnnouncementDTO objects and vice versa.
 */
public class AnnouncementMapper {
    private final PropertyMapper propertyMapper = new PropertyMapper();
    private final AgentMapper agentMapper = new AgentMapper();
    private final AgencyMapper agencyMapper = new AgencyMapper();
    private final OrderMapper orderMapper = new OrderMapper();
    VisitRequestMapper visitRequestMapper = new VisitRequestMapper();


    /**
     * Maps an AnnouncementAvailable object to an AnnouncementDTO object.
     *
     * @param announcement the AnnouncementAvailable object to be mapped
     * @return the corresponding AnnouncementDTO object
     */
    public AnnouncementDTO toAnnouncementDTO(AnnouncementAvailable announcement) {


        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setId(announcement.getId());
        dto.setComission(announcement.getCommission());
        dto.setDate(announcement.getDate());
        dto.setBusinessType(announcement.getBusinessType());
        dto.setRequestPrice(announcement.getPrice());
        dto.setProperty(propertyMapper.toPropertyDTO(announcement.getProperty()));

        if (announcement.getVisitRequests()!=null){
            dto.setVisitRequestList(visitRequestMapper.toDTO(announcement.getVisitRequests()));

        }
        if (announcement.getAgent()!=null){
            dto.setAgent(agentMapper.toDTO(announcement.getAgent()));
        }
        if (announcement.getOrderList()!=null){
            dto.setOrderList(orderMapper.toDTO(announcement.getOrderList()));

        }
        return dto;
    }

    /**
     * Maps an AnnouncementSold object to an AnnouncementDTO object.
     *
     * @param announcement the AnnouncementSold object to be mapped
     * @return the corresponding AnnouncementDTO object
     */
    public AnnouncementDTO toAnnouncementDTO(AnnouncementSold announcement) {
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setId(announcement.getId());
        dto.setComission(announcement.getComission());
        dto.setDate(announcement.getDate());
        dto.setBusinessType(announcement.getBusinessType());
        if (announcement.getProperty()!=null){
            dto.setProperty(propertyMapper.toPropertyDTO(announcement.getProperty()));
        }
        if (announcement.getAgent()!=null){
            dto.setAgent(agentMapper.toDTO(announcement.getAgent()));
        }
        dto.setContractDuration(announcement.getContractDuraction());
        dto.setSalePrice(announcement.getSalePrice());
        dto.setSaleDate(announcement.getSaleDate());
        dto.setAgency(agencyMapper.toDTO(announcement.getAgency()));
        return dto;
    }


    /**
     * Maps a list of AnnouncementAvailable objects to a list of AnnouncementDTO objects.
     *
     * @param announcementAvailables the list of AnnouncementAvailable objects to be mapped
     * @return the corresponding list of AnnouncementDTO objects
     */
    public List<AnnouncementDTO> toDTO(List<AnnouncementAvailable> announcementAvailables) {
        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        for (AnnouncementAvailable announcementAvailable : announcementAvailables) {
            announcementDTOS.add(this.toAnnouncementDTO(announcementAvailable));
        }
        return announcementDTOS;
    }


    /**
     * Maps an AnnouncementDTO object to an AnnouncementAvailable object.
     *
     * @param dto the AnnouncementDTO object to be mapped
     * @return the corresponding AnnouncementAvailable object
     */
    public AnnouncementAvailable toAnnouncementAvailable(AnnouncementDTO dto) {
        AnnouncementAvailable announcement = new AnnouncementAvailable();
        announcement.setId(dto.getId());
        announcement.setCommission(dto.getComission());
        announcement.setDate(dto.getDate());
        announcement.setBusinessType(dto.getBusinessType());
        announcement.setProperty(propertyMapper.fromDTO(dto.getProperty()));
        announcement.setPrice(dto.getRequestPrice());

        if (dto.getAgent()!=null){
            announcement.setAgent(agentMapper.fromDTO(dto.getAgent()));

        }
        announcement.setOrderList(orderMapper.fromDTO(dto.getOrderList()));
        VisitRequestMapper visitRequestMapper = new VisitRequestMapper();
        if (dto.getVisitRequestList()!=null){
            announcement.setVisitRequests(visitRequestMapper.fromDTO(dto.getVisitRequestList()));

        }
        return announcement;
    }


    /**
     * Maps an AnnouncementDTO object to an AnnouncementSold object.
     *
     * @param dto the AnnouncementDTO object to be mapped
     * @return the corresponding AnnouncementSold object
     */
    public AnnouncementSold toAnnouncementSold(AnnouncementDTO dto) {
        AnnouncementSold announcement = new AnnouncementSold();
        announcement.setId(dto.getId());
        announcement.setComission(dto.getComission());
        announcement.setDate(dto.getDate());
        announcement.setBusinessType(dto.getBusinessType());
        announcement.setProperty(propertyMapper.fromDTO(dto.getProperty()));
        announcement.setAgent(agentMapper.fromDTO(dto.getAgent()));
        announcement.setContractDuraction(dto.getContractDuration());
        announcement.setSalePrice(dto.getSalePrice());
        announcement.setSaleDate(dto.getSaleDate());
        announcement.setAgency(agencyMapper.fromDTO(dto.getAgency()));
        return announcement;
    }
}
