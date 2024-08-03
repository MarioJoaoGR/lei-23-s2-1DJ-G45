/*
package pt.ipp.isep.dei.esoft.project.application.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;

import javax.imageio.spi.RegisterableService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

    public class RegisterEmployeeControllerTest {

        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        AuthenticationRepository authenticationRepository= new AuthenticationRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        RegisterEmployeeController controller = new RegisterEmployeeController(agencyRepository, authenticationRepository);

        @Test
        public void testCreateEmployeeAgent() {
            StoreManager storeManager1 = new StoreManager(111, "saa", 423, 222, "pwd", 13123, "Aaa", "gsdgsdg@mail");
            Agency agency1 = new Agency(111, "aaa", "bbb", 123456755, "acaca", storeManager1);
            String name = "John Doe";
            int phoneNumber = 123456789;
            int taxNumber = 987654321;
            String password = "123456";
            int civilNumber = 123456;
            String address = "123 Main St";
            Agency agencia = agency1;
            String mail = "johndoe@example.com";

            Agent agent = controller.createEmployeeAgent(name, phoneNumber, taxNumber, password, civilNumber, address, agencia, mail);

            Assertions.assertEquals(name, agent.getName());
            Assertions.assertEquals(phoneNumber, agent.getPhoneNumber());
            Assertions.assertEquals(taxNumber, agent.getTaxNumber());
            Assertions.assertEquals(password, agent.getPassword());
            Assertions.assertEquals(civilNumber, agent.getCivilNumber());
            Assertions.assertEquals(address, agent.getAddress());
            Assertions.assertEquals(agencia, agent.getAgencia());
            Assertions.assertEquals(mail, agent.getMail());
        }

        @Test
        public void testCreateEmployeeStoreManager() {
            String name = "Jane Doe";
            int phoneNumber = 987654321;
            int taxNumber = 123456789;
            String password = "654321";
            int civilNumber = 654321;
            String address = "456 Main St";
            String mail = "janedoe@example.com";

            StoreManager storeManager = controller.createEmployeeStoreManager(name, phoneNumber, taxNumber, password, civilNumber, address, mail);

            Assertions.assertEquals(name, storeManager.getName());
            Assertions.assertEquals(phoneNumber, storeManager.getPhoneNumber());
            Assertions.assertEquals(taxNumber, storeManager.getTaxNumber());
            Assertions.assertEquals(password, storeManager.getPassword());
            Assertions.assertEquals(civilNumber, storeManager.getCivilNumber());
            Assertions.assertEquals(address, storeManager.getAddress());
            Assertions.assertEquals(mail, storeManager.getMail());
        }

        @Test
        public void testCreateEmployeeStoreNetworkManager() {
            String name = "Bob Smith";
            int phoneNumber = 555555555;
            int taxNumber = 111111111;
            String password = "password";
            int civilNumber = 333333;
            String address = "789 Main St";
            String mail = "bobsmith@example.com";

            StoreNetworkManager storeNetworkManager = controller.createEmployeeStoreNetworkManager(name, phoneNumber, taxNumber, password, civilNumber, address, mail);

            Assertions.assertEquals(name, storeNetworkManager.getName());
            Assertions.assertEquals(phoneNumber, storeNetworkManager.getPhoneNumber());
            Assertions.assertEquals(taxNumber, storeNetworkManager.getTaxNumber());
            Assertions.assertEquals(password, storeNetworkManager.getPassword());
            Assertions.assertEquals(civilNumber, storeNetworkManager.getCivilNumber());
            Assertions.assertEquals(address, storeNetworkManager.getAddress());
            Assertions.assertEquals(mail, storeNetworkManager.getMail());
        }

        @Test
        public void testCreateEmployeeSystemAdministrator() {
            String name = "Mary Smith";
            int phoneNumber = 444444444;
            int taxNumber = 222222222;
            String password = "password123";
            int civilNumber = 444444;
            String address = "123 Park Ave";
            String mail = "marysmith@example.com";

            SystemAdministrator systemAdministrator = controller.createEmployeeSystemAdministrator(name, phoneNumber, taxNumber, password, civilNumber, address, mail);

            Assertions.assertEquals(name, systemAdministrator.getName());
            Assertions.assertEquals(phoneNumber, systemAdministrator.getPhoneNumber());
            Assertions.assertEquals(taxNumber, systemAdministrator.getTaxNumber());
            Assertions.assertEquals(password, systemAdministrator.getPassword());
            Assertions.assertEquals(civilNumber, systemAdministrator.getCivilNumber());
            Assertions.assertEquals(address, systemAdministrator.getAddress());
            Assertions.assertEquals(mail, systemAdministrator.getMail());

        }

        @Test
        public void testSendEmployeesCredentialsViaEmailSuccess() {
            String password = "mypassword";
            String mail = "employee@example.com";
            String name = "John Doe";

            boolean result = controller.sendEmployeesCredentialsViaEmail(password, mail, name);

            assertTrue(result);
        }

//        @Test
//        public void testSendEmployeesCredentialsViaEmailFileNotFound() {
//            String password = "mypassword";
//            String mail = "employee@example.com";
//            String name = "John Doe";
//
//            controller.setFile("aaa"); // Modifique o valor do arquivo para um caminho inválido.
//            boolean result = controller.sendEmployeesCredentialsViaEmail(password, mail, name);
//
//            assertFalse(result);
//        }
    }

*/
