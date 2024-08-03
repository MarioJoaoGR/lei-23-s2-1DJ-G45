package pt.ipp.isep.dei.esoft.project.domain.mappers;


import pt.ipp.isep.dei.esoft.project.domain.AnnouncementAvailable;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementSold;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.StoreManagerDTO;
import pt.ipp.isep.dei.esoft.project.domain.StoreManager;

/**
 * The StoreManagerMapper class is responsible for mapping StoreManager objects to StoreManagerDTO objects and vice versa.
 */
public class StoreManagerMapper {

    /**
     * Maps a StoreManager object to a StoreManagerDTO object.
     *
     * @param storeManager the StoreManager object to be mapped
     * @return the corresponding StoreManagerDTO object
     */
    public  StoreManagerDTO toDTO(StoreManager storeManager) {
        StoreManagerDTO storeManagerDTO = new StoreManagerDTO();
        storeManagerDTO.setId(storeManager.getId());
        if (storeManager.getAddress()!=null) {
            storeManagerDTO.setAddress(storeManager.getAddress());
        }
        storeManagerDTO.setMail(storeManager.getMail());
        storeManagerDTO.setName(storeManager.getName());
        storeManagerDTO.setPhoneNumber(storeManager.getPhoneNumber());
        storeManagerDTO.setCivilNumber(storeManager.getCivilNumber());
        storeManagerDTO.setTaxNumber(storeManager.getTaxNumber());
        return storeManagerDTO;
    }


    /**
     * Maps a StoreManagerDTO object to a StoreManager object.
     *
     * @param dto the StoreManagerDTO object to be mapped
     * @return the corresponding StoreManager object
     */
    public  StoreManager fromDTO(StoreManagerDTO dto) {
        return new StoreManager(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getTaxNumber(),
                dto.getPassword(), dto.getCivilNumber(), dto.getAddress(), dto.getMail());
    }
}

