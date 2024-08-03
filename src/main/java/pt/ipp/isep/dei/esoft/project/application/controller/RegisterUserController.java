package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.UserLogin;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.UserLoginRepository;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * The type Register user controller.
 */
public class RegisterUserController {

    private AuthFacade authFacade =new AuthFacade();


    /**
     * Add user boolean.
     *
     * @param name  the name
     * @param email the email
     * @param pwd   the pwd
     * @return the boolean
     */
    public boolean addUser(String name, String email, String pwd) {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        UserLoginRepository userLoginRepository = Repositories.getInstance().getUserRepository();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_USER,
                AuthenticationController.ROLE_USER);
        UserLogin user = new UserLogin(name,email,pwd,AuthenticationController.ROLE_USER);
        userLoginRepository.add(user);
        return authenticationRepository.addUserWithRole(name,email,pwd,
                AuthenticationController.ROLE_USER);


    }





}

