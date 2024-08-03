package pt.ipp.isep.dei.esoft.project.repository;

import com.sun.tools.jconsole.JConsoleContext;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RequestRepositoryTest {


    @Test
    void ensureAddRequestWorks() {
        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","asdas@aa");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, "afaf@");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, owner,photos);
        requestRepository.add(request);

        Optional<Request> returnRequest = requestRepository.getRequestByOwnerName("aaa");

        //Assert
        //Make sure both represents the same object
        assertEquals(request, returnRequest.get());
        //Make sure it is a clone (different memory addresses)
        assertNotSame(request, returnRequest.get());
    }

    @Test
    void ensureGetRequestByOwnerNameWorks(){
        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","fafa@");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, "dsadas@");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, owner,photos);
        requestRepository.add(request);

        Optional<Request> result =
                requestRepository.getRequestByOwnerName("aaa");





        assertEquals(request, result.get());
    }

    @Test
    void ensureGetRequestByDifferenteOwnerNameDoesntWork(){
        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","gadga@");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, "aaa");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);

        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, owner,photos);
        requestRepository.add(request);
        Optional<Request> result =
                requestRepository.getRequestByOwnerName("fafafeaw");

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureAddRequestWork() {
        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","aasfa");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, "aga");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, owner,photos);
        Optional<Request> result = requestRepository.add(request);
        assertTrue(result.isPresent());
        requestRepository.add(request);
        assertTrue(requestRepository.checkIfRequestExists(request));
    }

    @Test
    void ensureCantAddDuplicate() {
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","aasfa");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, "aga");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, owner,photos);
        requestRepository.add(request);

        Optional<Request> result = requestRepository.add(request);

        assertTrue(result.isEmpty());

    }

    @Test
    void ensureGetRequestsForAgentIsWorking(){
        RequestRepository requestRepository = new RequestRepository();
        StoreManager storeManager = new StoreManager(321, "aaa", 31, 414, "storepwd", 111, "aaaa","fafa@");
        Agency agency = new Agency(11, "test", "testAddress", 123456789, "teste@mail", storeManager);
        Agent agent = new Agent( 222, "aaa", 123444444, 111111111, "agentpwd", 111, "dsadas@");
        Agent agent2 = new Agent(21122, "adaa", 123444444, 111111111, "agentpwd", 111,"dsadas@");
        Owner owner = new Owner("aaa", 222, 444, "aaa", "sda", 222);
        Agent agent3 = new Agent(222, "aaBBa", 123444444, 111111111, "agentpwd", 111,"dsadas@");
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request = new Request("sell", agent, 13123.3, "aaaa", 431241, 13312, owner,photos);
        requestRepository.add(request);

        Request request3 = new Request("sell", agent3, 13123.3, "aaaa", 431241, 13334112, owner,photos);
        Request request2 = new Request("rent", agent, 13123.3, "aaaa", 431241, 13343212, owner,photos);
        requestRepository.add(request2);
        requestRepository.add(request3);
        // Test the method for an agent with multiple requests
        Optional<List<Request>> matchingRequests1 = requestRepository.getRequestsForAgent(agent);
        assertTrue(matchingRequests1.isPresent());
        assertTrue(requestRepository.checkIfRequestExists(request2));
        List<Request> requestsForAgent1 = matchingRequests1.get();
        assertEquals(2, requestsForAgent1.size());
        assertTrue(requestsForAgent1.contains(request));
        assertTrue(requestsForAgent1.contains(request2));

        // Test the method for an agent with no requests
        Optional<List<Request>> matchingRequests2 = requestRepository.getRequestsForAgent(agent2);
        assertFalse(matchingRequests2.isPresent());

        // Test the method for an agent with one request
        Optional<List<Request>> matchingRequests3 = requestRepository.getRequestsForAgent(agent3);
        assertTrue(matchingRequests3.isPresent());
        List<Request> requestsForAgent3 = matchingRequests3.get();
        assertEquals(1, requestsForAgent3.size());
        assertTrue(requestsForAgent3.contains(request3));
    }

    // This is the method to obtain the agent requests without optional
    @Test
    void ensuregetAgentRequests() {
        RequestRepository requestRepository = new RequestRepository();
        Agent agentAndre = new Agent("andre@gmail.com", "Andre");
        Owner ownerZe = new Owner("Ze", 1, 1, "rua", "ze@gmail.com", 91);

        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request2 = new Request("sale", agentAndre, 1, "Porto", 1, 1, ownerZe,photos);
        Request request3 = new Request("sale", agentAndre, 1, "Porto", 1, 1, ownerZe,photos);
        requestRepository.getRequestsList().add(request2);
        requestRepository.getRequestsList().add(request3);

        // Asserting
        List<Request> agentAndreRequests = requestRepository.getAgentRequests(agentAndre);
        assertEquals(request2.getRequestId(), agentAndreRequests.get(0).getRequestId());
        assertEquals(request3.getRequestId(), agentAndreRequests.get(1).getRequestId());
    }

    @Test
    void ensureCheckRequestById() {
        RequestRepository requestRepository = new RequestRepository();
        Agent agentJoao = new Agent("joaozinho@gmail.com", "Joao");
        Agent agentAndre = new Agent("andre@gmail.com", "Andre");
        Owner ownerZe = new Owner("Ze", 1, 1, "rua", "ze@gmail.com", 91);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request1 = new Request("sale", agentJoao, 1, "Porto", 1, 1, ownerZe,photos);
        Request request2 = new Request("sale", agentAndre, 1, "Porto", 1, 1, ownerZe,photos);
        // we don't add request 3 to the repository
        Request request3 = new Request("sale", agentAndre, 1, "Porto", 1, 1, ownerZe,photos);
        requestRepository.add(request1);
        requestRepository.add(request2);

        // Asserting true
        assertTrue(requestRepository.checkRequestId(request1.getRequestId()));

        // Asserting false, notice the request3 was not added to the repository
        assertFalse(requestRepository.checkRequestId(request3.getRequestId()));
    }

    @Test
    void ensureRequestIsRemoved() {
        RequestRepository requestRepository = new RequestRepository();
        Agent agentJoao = new Agent("joaozinho@gmail.com", "Joao");
        Agent agentAndre = new Agent("andre@gmail.com", "Andre");
        Owner ownerZe = new Owner("Ze", 1, 1, "rua", "ze@gmail.com", 91);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request1 = new Request("sale", agentJoao, 1, "Porto", 1, 1, ownerZe,photos);
        Request request2 = new Request("sale", agentAndre, 1, "Porto", 1, 1, ownerZe,photos);
        requestRepository.add(request1);
        requestRepository.add(request2);

        // First asserting if the requests were created proprely
        assertTrue(requestRepository.checkIfRequestExists(request1));
        assertTrue(requestRepository.checkIfRequestExists(request2));

        // Removing request1
        requestRepository.removeRequestById(request1.getRequestId());

        // Asserting that the request 1 was proprely removed, i.e, asserting false
        assertFalse(requestRepository.checkIfRequestExists(request1));
    }

    @Test
    void ensureRequestByIdNoOptional() {
        RequestRepository requestRepository = new RequestRepository();
        Agent agentJoao = new Agent("joaozinho@gmail.com", "Joao");
        Agent agentAndre = new Agent("andre@gmail.com", "Andre");
        Owner ownerZe = new Owner("Ze", 1, 1, "rua", "ze@gmail.com", 91);
        List<String> photos = new ArrayList<>();
        photos.add("aaa.jpg");
        Request request1 = new Request("sale", agentJoao, 1, "Porto", 1, 1, ownerZe,photos);
        requestRepository.add(request1);
        Request request1ById = requestRepository.getRequestByIdNoOptional(request1.getRequestId());

        // Asserting
        assertEquals(request1, request1ById);

    }



}




