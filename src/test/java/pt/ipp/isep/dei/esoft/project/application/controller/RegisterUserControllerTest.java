package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserControllerTest {

        RegisterUserController controller =
                new RegisterUserController();


    @Test
    void addUser() {
        boolean flag =controller.addUser("user1","email1@gmail.com","password");
        assertTrue(flag);
    }

    @Test
    void isAccountAlreadyRegisteredTrue() {
        controller.addUser("user1","email1@gmail.com","password");
        boolean flag = controller.addUser("user2","email1@gmail.com","pasord");

        assertFalse(flag);
    }

    @Test
    void isAccountAlreadyRegisteredFalse() {
        controller.addUser("user1","email1@gmail.com","password");
        boolean flag = controller.addUser("user2","email2@gmail.com","pasord");

        assertTrue(flag);
    }



}