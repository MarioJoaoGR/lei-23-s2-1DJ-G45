package pt.ipp.isep.dei.esoft.project.domain.mappers;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.PropertyDTO;

/**
 * The PropertyMapper class is responsible for mapping Property objects to PropertyDTO objects and vice versa.
 */
public class PropertyMapper {

    /**
     * Maps a Property object to a PropertyDTO object.
     *
     * @param property the Property object to be mapped
     * @return the corresponding PropertyDTO object
     */
    public PropertyDTO toPropertyDTO(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setId(property.getId());
        dto.setPropertyType(property.getPropertyType());
        dto.setLocation(property.getLocation());
        dto.setArea(property.getArea());
        dto.setDistanceCenter(property.getDistanceCenter());
        dto.setPhotographsAnnouncement(property.getPhotographsAnnouncement());
        dto.setOwner(property.getOwner());

        if (property instanceof Apartment) {
            Apartment apartment = (Apartment) property;
            dto.setNumberRooms(apartment.getNumberRooms());
            dto.setNumberBathrooms(apartment.getNumberBathrooms());
            dto.setNumberParking(apartment.getNumberParking());
            dto.setEquipment(apartment.getEquipment());
            dto.setBasement(false);
            dto.setLoft(false);
            dto.setSunExposure(null);
        } else if (property instanceof Land) {
            Land land = (Land) property;
            dto.setNumberRooms(0);
            dto.setNumberBathrooms(0);
            dto.setNumberParking(0);
            dto.setEquipment(null);
            dto.setBasement(false);
            dto.setLoft(false);
            dto.setSunExposure(null);
        } else if (property instanceof House) {
            House house = (House) property;
            dto.setNumberRooms(house.getNumberRooms());
            dto.setNumberBathrooms(house.getNumberBathrooms());
            dto.setNumberParking(house.getNumberParking());
            dto.setEquipment(house.getEquipment());
            dto.setBasement(house.isBasement());
            dto.setLoft(house.isLoft());
            dto.setSunExposure(house.getSunExposure());
        }

        return dto;
    }


    /**
     * Maps a PropertyDTO object to a Property object.
     *
     * @param dto the PropertyDTO object to be mapped
     * @return the corresponding Property object
     */
    public static Property toProperty(PropertyDTO dto) {
        Property property;
        switch (dto.getPropertyType()) {
            case "Apartment":
                property = new Apartment();
                Apartment apartment = (Apartment) property;
                apartment.setNumberRooms(dto.getNumberRooms());
                apartment.setNumberBathrooms(dto.getNumberBathrooms());
                apartment.setNumberParking(dto.getNumberParking());
                apartment.setEquipment(dto.getEquipment());

                break;
            case "Land":
                property = new Land();
                break;
            case "House":
                property = new House();
                House house = (House) property;
                house.setNumberRooms(dto.getNumberRooms());
                house.setNumberBathrooms(dto.getNumberBathrooms());
                house.setNumberParking(dto.getNumberParking());
                house.setEquipment(dto.getEquipment());
                house.setBasement(dto.isBasement());
                house.setLoft(dto.isLoft());
                house.setSunExposure(dto.getSunExposure());
                break;
            default:
                throw new IllegalArgumentException("Invalid property type");
        }
        property.setId(dto.getId());
        property.setPropertyType(dto.getPropertyType());
        property.setLocation(dto.getLocation());
        property.setArea(dto.getArea());
        property.setDistanceCenter(dto.getDistanceCenter());
        property.setPhotographsAnnouncement(dto.getPhotographsAnnouncement());
        property.setOwner(dto.getOwner());
        return property;
    }

    /**
     * Maps a PropertyDTO object to a Property object.
     *
     * @param dto the PropertyDTO object to be mapped
     * @return the corresponding Property object
     */
    public Property fromDTO(PropertyDTO dto) {
        Property property;
        switch (dto.getPropertyType()) {
            case "Apartment":
                property = new Apartment();
                Apartment apartment = (Apartment) property;
                apartment.setId(dto.getId());
                apartment.setLocation(dto.getLocation());
                apartment.setArea(dto.getArea());
                apartment.setDistanceCenter(dto.getDistanceCenter());
                apartment.setPhotographsAnnouncement(dto.getPhotographsAnnouncement());
                apartment.setOwner(dto.getOwner());
                apartment.setNumberRooms(dto.getNumberRooms());
                apartment.setNumberBathrooms(dto.getNumberBathrooms());
                apartment.setNumberParking(dto.getNumberParking());
                apartment.setEquipment(dto.getEquipment());
                break;
            case "Land":
                property = new Land();
                property.setId(dto.getId());
                property.setPropertyType(dto.getPropertyType());
                property.setLocation(dto.getLocation());
                property.setArea(dto.getArea());
                property.setDistanceCenter(dto.getDistanceCenter());
                property.setPhotographsAnnouncement(dto.getPhotographsAnnouncement());
                property.setOwner(dto.getOwner());
                break;
            case "House":
                property = new House();
                House house = (House) property;
                house.setId(dto.getId());
                house.setLocation(dto.getLocation());
                house.setArea(dto.getArea());
                house.setDistanceCenter(dto.getDistanceCenter());
                house.setPhotographsAnnouncement(dto.getPhotographsAnnouncement());
                house.setOwner(dto.getOwner());
                house.setNumberRooms(dto.getNumberRooms());
                house.setNumberBathrooms(dto.getNumberBathrooms());
                house.setNumberParking(dto.getNumberParking());
                house.setEquipment(dto.getEquipment());
                house.setBasement(dto.isBasement());
                house.setLoft(dto.isLoft());
                house.setSunExposure(dto.getSunExposure());
                break;
            default:
                throw new IllegalArgumentException("Invalid property type");
        }
        return property;
    }

}

