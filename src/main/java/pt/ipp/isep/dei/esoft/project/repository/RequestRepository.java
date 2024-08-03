package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;
import pt.ipp.isep.dei.esoft.project.domain.mappers.RequestMapper;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.Serializable;
import java.util.*;

/**
 * The type Request repository.
 */
public class RequestRepository implements Serializable {

    private final List<Request> requestsList = new ArrayList<>();

    /**
     * Gets requests list.
     *
     * @return requestList requests list
     */
    public List<Request> getRequestsList() {
        return requestsList;
    }

    /**
     * returns and Optional with a Request if Request is valid if not return and empty Optional
     *
     * @param requestToAdd the request to add
     * @return Optional Request
     */
    public Optional<Request> add(Request requestToAdd) {
        Optional<Request> newRequests = Optional.empty();

        boolean operationSuccess = false;
        if (validateRequest(requestToAdd)) {

            newRequests = Optional.of(requestToAdd.clone());
            operationSuccess = requestsList.add(newRequests.get());
        }
        if (!operationSuccess) {
            newRequests = Optional.empty();
        }
        return newRequests;
    }

    /**
     * search for the requested mail
     *
     * @param mail email
     * @return boolean boolean
     */
    public boolean verifyMailIsPresent(String mail) {
        for (Request request : requestsList) {
            if (request.getOwner().getEmail().equals(mail)) {
                return false;
            }
        }
        return true;
    }


    /**
     * returns true if theren`t equal requests and false if not
     *
     * @param requestToAdd
     * @return boolean result
     */
    private boolean validateRequest(Request requestToAdd) {
        boolean isValid = !requestsList.contains(requestToAdd);
        return isValid;
    }

    /**
     * returns true if the list contains the request and false if not
     *
     * @param requestAdded the request added
     * @return boolean result
     */
    public boolean checkIfRequestExists(Request requestAdded) {
        return requestsList.contains(requestAdded);
    }

    /**
     * This is a defensive copy, so that the repository cannot be modified from the outside.
     *
     * @return copy of list of request
     */
    public List<Request> getRequests() {
        return List.copyOf(requestsList);
    }

    /**
     * Searchs the request by owner name
     *
     * @param name the name
     * @return Optional request
     */
    public Optional<Request> getRequestByOwnerName(String name) {
        Optional<Request> returnRequest = Optional.empty();

        for (Request request : requestsList) {

            if (request.getOwner().getName().equals(name)) {
                returnRequest = Optional.of(request);
            }
        }
        return returnRequest;
    }

    /**
     * search if there are any request for the agent passed by parameter
     *
     * @param agent the agent
     * @return Optional list of requests
     */
    public Optional<List<Request>> getRequestsForAgent(Agent agent) {
        List<Request> matchingRequests = new ArrayList<>();
        for (Request request : requestsList) {
            if (request.getAgente().equals(agent)) {
                matchingRequests.add(request);
            }
        }
        if (matchingRequests.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(matchingRequests);
        }
    }

    /**
     * Gets request by id.
     *
     * @param id the id
     * @return the request by id
     */
    public Optional<Request> getRequestById(int id) {
        Optional<Request> returnRequest = Optional.empty();
        for (Request request : requestsList) {
            if (request.getRequestId() == id) {
                Optional.of(request);
            }
        }
        return returnRequest;
    }

    /**
     * This method checks if a given request id is present in the request repository.
     * That is the same as checking if the requests itself is present since each id is unique.
     *
     * @param requestId request id to check
     * @return true or false according to ether it exists or not
     */
    public boolean checkRequestId(int requestId) {
        boolean isValid = false;
        List<Request> requests = this.getRequests();
        for (Request request : requests) {
            if (request.getRequestId() == requestId) {
                isValid = true;
            }
        }
        return isValid;
    }


    /**
     * Returns a request from the repository by its id.
     *
     * @param requestId the id of the request the user wants to return
     * @return the request
     */
    public Optional<Request> getRequestByIdOptional(int requestId) {
        Optional<Request> requestToGet = Optional.empty();
        for (Request request : requestsList) {
            if (request.getRequestId() == requestId) {
               requestToGet = Optional.of(request);
            }
        }
        return requestToGet;
    }

    /**
     * Gets request by id no optional.
     *
     * @param requestId the request id
     * @return the request by id no optional
     */
    public Request getRequestByIdNoOptional(int requestId) {
        Request requestToGet = null;
        for (Request request : requestsList) {
            if (request.getRequestId() == requestId) {
                requestToGet = request;
            }
        }
        return requestToGet;
    }

    /**
     * Gets the agent requests. This method was created as an alternative to the optional.
     *
     * @param agent the agent
     * @return agent requests
     */
    public List<Request> getAgentRequests(Agent agent) {
        List<Request> requestList = getRequestsList();
        List<Request> agentRequests = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getAgente().getMail().equals(agent.getMail())) {
                agentRequests.add(request);
            }
        }
        return agentRequests;
    }

    /**
     * Gets agent requests.
     *
     * @param mail the mail
     * @return the agent requests
     */
    public List<Request> getAgentRequests(String mail) {
        List<Request> requestList = getRequestsList();
        List<Request> agentRequests = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getAgente().getMail().equals(mail)) {
                agentRequests.add(request);
            }
        }
        return agentRequests;
    }

    /**
     * This method removes a request from the repository, this is used when an announcement is published the request
     * must be removed from the repository
     *
     * @param requestId the request id
     */
    public void removeRequestById(int requestId) {
        List<Request> requestList = getRequestsList();
        Iterator<Request> iterator = requestList.iterator();
        while (iterator.hasNext()) {
            Request request = iterator.next();
            if (request.getRequestId() == requestId) {
                iterator.remove();
            }
        }
    }

    /**
     * Create request optional.
     *
     * @param requestDto the request dto
     * @return the optional
     */
    public Optional<Request> createRequest(RequestDTO requestDto) {
        RequestMapper mapper = new RequestMapper();
        Request request = mapper.fromDTO(requestDto);
        request.setRequestId(requestsList.size());
        return add(request);
    }
}





