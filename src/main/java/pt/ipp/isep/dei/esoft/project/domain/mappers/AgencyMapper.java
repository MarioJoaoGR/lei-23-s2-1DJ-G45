package pt.ipp.isep.dei.esoft.project.domain.mappers;


import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The AgencyMapper class is responsible for mapping Agency objects to AgencyDTO objects and vice versa.
 */
public class AgencyMapper {

    StoreManagerMapper storeManagerMapper = new StoreManagerMapper();

    /**
     * Maps an Agency object to an AgencyDTO object.
     *
     * @param agency the Agency object to be mapped
     * @return the corresponding AgencyDTO object
     */
    public AgencyDTO toDTO(Agency agency) {
        if (agency != null){
            AgencyDTO agencyDTO = new AgencyDTO();
            agencyDTO.setEmail(agency.getEmail());
            agencyDTO.setName(agency.getName());
            if (!agency.getAddress().isEmpty() && agency.getAddress() != null) {
                agencyDTO.setAddress(agency.getAddress());
            }
            agencyDTO.setPhoneNumber(agency.getPhoneNumber());
            StoreManagerMapper storeManagerMapper1 = new StoreManagerMapper();
            if (agency.getStoreManager()!=null){
                agencyDTO.setStoreManager(storeManagerMapper1.toDTO(agency.getStoreManager()));
            }

            for (Agent agent : agency.getAgentsList()) {
                agencyDTO.getAgentDTOList().add(toDTO(agent));
            }
            return agencyDTO;
        }
        return null;

    }

    /**
     * Maps an Agent object to an AgentDTO object.
     *
     * @param agent the Agent object to be mapped
     * @return the corresponding AgentDTO object
     */
    private AgentDTO toDTO(Agent agent) {
        AgentMapper agentMapper = new AgentMapper();
        return agentMapper.toDTO(agent);
    }

    /**
     * Maps a list of Agency objects to a list of AgencyDTO objects.
     *
     * @param agencyList the list of Agency objects to be mapped
     * @return the corresponding list of AgencyDTO objects
     */
    public List<AgencyDTO> toDTO(List<Agency> agencyList) {
        List<AgencyDTO> requestDtos = new ArrayList<>();
        for (Agency agency : agencyList) {
            requestDtos.add(this.toDTO(agency));
        }
        return requestDtos;
    }


    /**
     * Maps an AgencyDTO object to an Agency object.
     *
     * @param dto the AgencyDTO object to be mapped
     * @return the corresponding Agency object
     */
    public Agency fromDTO(AgencyDTO dto) {
        if (dto!=null){
            return  new Agency(dto.getName(), dto.getAddress(), dto.getPhoneNumber(), dto.getEmail(),storeManagerMapper.fromDTO(dto.getStoreManager()));

        }
        return null;
    }

}
