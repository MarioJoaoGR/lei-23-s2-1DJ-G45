package pt.ipp.isep.dei.esoft.project.domain.mappers;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The AgentMapper class is responsible for mapping Agent objects to AgentDTO objects and vice versa.
 */
public class AgentMapper {

    /**
     * Maps an Agent object to an AgentDTO object.
     *
     * @param agent the Agent object to be mapped
     * @return the corresponding AgentDTO object
     */
    public AgentDTO toDTO(Agent agent) {
        AgentDTO agentDTO = new AgentDTO();
        agentDTO.setMail(agent.getMail());
        agentDTO.setName(agent.getName());
        if (agent.getAddress()!=null) {
            agentDTO.setAddress(agent.getAddress());
        }
        agentDTO.setCivilNumber(agent.getCivilNumber());
        agentDTO.setPhoneNumber(agent.getPhoneNumber());
        agentDTO.setTaxNumber(agent.getTaxNumber());
        return agentDTO;
    }


    /**
     * Maps a list of Agent objects to a list of AgentDTO objects.
     *
     * @param agentList the list of Agent objects to be mapped
     * @return the corresponding list of AgentDTO objects
     */
    public List<AgentDTO> toDTO(List<Agent> agentList) {
        List<AgentDTO> agentDTOList = new ArrayList<>();
        for (Agent agent : agentList) {
            agentDTOList.add(this.toDTO(agent));
        }
        return agentDTOList;
    }

    /**
     * Maps an AgentDTO object to an Agent object.
     *
     * @param dto the AgentDTO object to be mapped
     * @return the corresponding Agent object
     */
    public Agent fromDTO(AgentDTO dto) {
        Agent agent = new Agent();

        agent.setName(dto.getName());
        agent.setPhoneNumber(dto.getPhoneNumber());
        agent.setTaxNumber(dto.getTaxNumber());
        agent.setCivilNumber(dto.getCivilNumber());
        if (dto.getAddress()!=null) {
            agent.setAddress(dto.getAddress());
        }
        agent.setMail(dto.getMail());
        return agent;
    }


}
