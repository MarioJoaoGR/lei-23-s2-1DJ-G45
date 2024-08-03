package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterUserController;

import java.util.Scanner;

/**
 * The type Register user ui.
 */
public class RegisterUserUI implements Runnable {




    //----------------


    //user search/
    private String userName;
    private String userEmail;
    private  String userPassword;

    private final RegisterUserController controller = new RegisterUserController();


    public void run() {

        boolean result;
        System.out.println("Register user");
        requestSearchData();


        result=controller.addUser(userName,userEmail,userPassword);

        if(!result){
            System.out.println("This email is already registered");
        }else{
            System.out.println("User successfully registered");
        }


    }



    private void requestSearchData() {

        userName= requestUserName();
        userEmail= requestUserEmail();
        userPassword = requestUserPassword();
    }

    private String requestUserName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type your name");
        do {
            userName = input.nextLine().trim();
        } while (userName.isEmpty());
        return userName;

    }



    private String requestUserEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type your email");
        boolean flag = false;
        String mail;
        do {
            mail = input.nextLine();
            if (isValidEmail(mail)) {
                flag = true;
            } else {
                System.out.println("Invalid mail");
            }
        } while (!flag);

        return mail;
    }

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        int atSymbolIndex = email.indexOf('@');
        boolean hasAtSymbol = atSymbolIndex >= 0;
        boolean hasValidDomain = email.endsWith(".com") || email.endsWith(".org") || email.endsWith(".net");

        return hasAtSymbol && hasValidDomain;
    }


    private String requestUserPassword() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type your Password");
        boolean flag = false;
        String password;
        do {
            password = input.nextLine();
            if (isValidPassword(password)) {
                flag = true;
            } else {
                System.out.println("Invalid password");
            }
        } while (!flag);

        return password;
    }


    /**
     * Is valid password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isValidPassword(String password) {
        boolean lenght=false;
        boolean upperCase=false;
        boolean numDigits=false;
        int upper=0,number=0;


        for(int i = 0; i < password.length(); i++)
        {
            char ch = password.charAt(i);
            if (ch >= 'A' && ch <= 'Z')
                upper++;
            else if (ch >= '0' && ch <= '9')
                number++;
        }

        lenght= password.length() >= 8;
        upperCase = upper>0;
        numDigits = number>0;

        return lenght && upperCase && numDigits;
    }

}
