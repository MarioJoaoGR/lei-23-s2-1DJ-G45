package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Agent;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * The type Agency repository.
 */
public class AgencyRepository implements Serializable{


    private final List<Agency> agencyList = new ArrayList<>();


    /**
     * Gets agency list.
     *
     * @return agency list
     */
    public List<Agency> getAgencyList() {
        return agencyList;
    }


    /**
     * if agency is valid returns the Optioanl agency if not return and Optional empty
     *
     * @param agencia to be added
     * @return Optional agencia
     */
    public Optional<Agency> add(Agency agencia) {

        Optional<Agency> newAgency = Optional.empty();

        boolean operationSuccess = false;

        if (validateAgency(agencia)) {
            newAgency = Optional.of(agencia.clone());
            operationSuccess = agencyList.add(newAgency.get());
        }

        if (!operationSuccess) {
            newAgency = Optional.empty();
        }

        return newAgency;

    }


    /**
     * gets a random Agency from agency list
     *
     * @return Agencia object
     */
    public Agency getRandomAgency() {
        Random rand = new Random();
        int index = 0;
        boolean flag=false;
        do {
            if (agencyList.size() > 1) {
                index = rand.nextInt(agencyList.size());
            }
            if (!agencyList.get(index).getAgentsList().isEmpty()){
                flag=true;
            }
        }while (!flag);

        return agencyList.get(index);
    }

    /**
     * this is a defensive copy, so that the repository cannot be modified from the outside
     *
     * @return copy of agencyList
     */
    public List<Agency> getAgencyListCopy() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(agencyList);
    }

    /**
     * returns true if theren`t equal agencies and false if not
     * @param agencia
     * @return boolean result
     */
    private boolean validateAgency(Agency agencia) {
        boolean isValid = !agencyList.contains(agencia);
        return isValid;
    }

    /**
     * search agency bu agency name
     *
     * @param name the name
     * @return agency agency by agency name
     */
    public Optional<Agency> getAgencyByAgencyName(String name) {
        Optional<Agency> result = Optional.empty();
        for (Agency agency : agencyList) {
            if (agency.getName().equals(name))
                result= Optional.of(agency);
        }
        return result;
    }


    /**
     * Get agent by agent mail optional.
     *
     * @param mail the mail
     * @return the optional
     */
    public Optional<Agent> getAgentByAgentMail(String mail){
        Optional<Agent> result = Optional.empty();
        for (Agency agency: agencyList) {
            if (agency.anyAgentHasMail(mail).isPresent()){
                result = agency.anyAgentHasMail(mail);
            }
        }
        return result;
    }






}


