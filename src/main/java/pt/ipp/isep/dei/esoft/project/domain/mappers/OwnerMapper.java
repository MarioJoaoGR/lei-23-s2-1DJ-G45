package pt.ipp.isep.dei.esoft.project.domain.mappers;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.Owner;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgencyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OwnerDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The OwnerMapper class is responsible for mapping Owner objects to OwnerDTO objects and vice versa.
 */
public class OwnerMapper {


    /**
     * Maps an Owner object to an OwnerDTO object.
     *
     * @param owner the Owner object to be mapped
     * @return the corresponding OwnerDTO object
     */
    public OwnerDTO toDTO(Owner owner) {

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName(owner.getName());
        ownerDTO.setEmail(owner.getEmail());
        ownerDTO.setAddress(owner.getAddress());
        ownerDTO.setTaxNumber(owner.getTaxNumber());
        ownerDTO.setPhoneNumber(owner.getPhoneNumber());
        ownerDTO.setCardNumber(owner.getCardNumber());
        return ownerDTO;

    }

    /**
     * Maps an OwnerDTO object to an Owner object.
     *
     * @param dto the OwnerDTO object to be mapped
     * @return the corresponding Owner object
     */
    public Owner fromDTO(OwnerDTO dto) {
        return new Owner(dto.getName(),dto.getCardNumber(),dto.getTaxNumber(),dto.getAddress(),dto.getEmail(),dto.getPhoneNumber());
    }










    /*  public List<OwnerDTO> toDTO(List<Agency> agencyList) {
        List<AgencyDTO> requestDtos = new ArrayList<>();
        for (Agency agency : agencyList) {
            requestDtos.add(this.toDTO(agency));
        }
        return requestDtos;
    }
*/



}
