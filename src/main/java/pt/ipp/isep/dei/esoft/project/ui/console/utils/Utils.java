package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AnnouncementDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.RequestDTO;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Utils {

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public long readLongFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);
                return parseLong(input);

            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);

    }


    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid value.");
            }
        } while (true);
    }

    static public String readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

                Date date = inputFormat.parse(strDate);
                String formattedDate = outputFormat.format(date);

                return formattedDate;

            } catch (ParseException ex) {
                System.out.println("Invalid input. Please enter a valid date.");
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public int showAgentsAndSelectIndex(List list, String header) {
        showAgentList(list, header);
        return selectsIndex(list);
    }

    static public int showRolesAndSelectIndex(List<UserRoleDTO> list, String header) {
        showEmployeeRoles(list, header);
        return selectsIndex(list);
    }

    static public void showAgentList(List<AgentDTO> list, String header) {
        System.out.println(header);

        int index = 0;

        for (AgentDTO o : list) {
            index++;

            System.out.println(index + ". " + o.getName());
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;

        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }

    static public void showEmployeeRoles(List<UserRoleDTO> list, String header) {
        System.out.println(header);

        int index = 0;

        for (UserRoleDTO o : list) {
            index++;

            if (!Objects.equals(o.getDescription(), AuthenticationController.ROLE_USER)){
                System.out.println(index + ". " + o.getDescription());
            }
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }
    static public void showAnnouncements(List<AnnouncementDTO> list, String header) {
        System.out.println(header);

        int index = 0;

        for (AnnouncementDTO o : list) {
            index++;

            System.out.println(index + ". " + o.getProperty().getLocation() + " T" + o.getProperty().getNumberRooms());
        }
        System.out.println();
        System.out.println("0 - Cancel");
    }

    static public AnnouncementDTO showAnnouncementsAndSelectObject(List<AnnouncementDTO> list, String header) {
        System.out.println(header);

        int index = 0;

        for (AnnouncementDTO o : list) {
            index++;

            System.out.println(index + ". " + o.getProperty().getLocation() + " T" + o.getProperty().getNumberRooms());
        }
        System.out.println();
        System.out.println("0 - Cancel");
        return (AnnouncementDTO) selectsObject(list);
    }


    static public void showAnnouncementsWithoutLastOption(List<AnnouncementDTO> list, String header) {
        System.out.println(header);

        int index = 0;

        for (AnnouncementDTO o : list) {
            index++;

            System.out.println(index + ". " + o.getProperty().getLocation() + " T" + o.getProperty().getNumberRooms());
        }
        System.out.println();
    }
    static public Object showRequestAndSelectOne(List<Request> list, String header) {
        System.out.println(header);


        int index = 0;

        for (Request o : list) {
            index++;

            System.out.println(index +" Date: " + o.getDate() + " Location: " + o.getLocation() + " Type: " + o.getRequestType());
        }
        System.out.println();
        System.out.println("0 - Cancel");
        return selectsObject(list);

    }

    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    static public List<String> requestTimeSlot() {
        List<String> timeSlots = new ArrayList<>();
        boolean flag;
        do {
            flag = true;
            if (timeSlots.isEmpty()) {
                System.out.println("Do you want to add a prefered time slot-->from x hour to y hour(24 hour available)");
            } else {
                System.out.println("Do you want to add any more prefered date time slot??");
                System.out.println("1-Yes");
                System.out.println("2-No");
                int option = readIntegerFromConsole("option:");
                if (option == 2) {
                    flag = false;
                }
            }
            if (flag) {
                int x;
                int y;
                do {
                    x = Utils.readIntegerFromConsole("X:");
                    if (x > 24 || x < 0) {
                        System.out.println("Invalid hour try again");
                    }
                } while (x > 24 || x < 0);

                do {
                    y = Utils.readIntegerFromConsole("Y:");
                    if (y > 24 || y <= x) {
                        System.out.println("Invalid hour try again");
                    }
                } while (y > 24 || y <= x);
                //clean buffer
                String message = "from " + x + " hour to " + y;
                if (!timeSlots.contains(message)) {
                    timeSlots.add(message);
                } else {
                    System.out.println();
                    System.out.println("That time schedule was already chosed");
                }
            }

        } while (flag);
        return timeSlots;
    }

    public static long requestPhoneNumber() {
        String phoneNumber;
        long result = 0;
        boolean flag = false;
        do {
            phoneNumber = readLineFromConsole("Please enter a phone number:");
            if (isPhoneNumber(phoneNumber)) {
                flag = true;
                result = parseLong(phoneNumber);
            } else {
                System.out.println("The phone number is invalid.");
            }
        } while (!flag);

        return result;
    }

    public static long requestPhoneNumber(String header) {
        String phoneNumber;
        long result = 0;
        boolean flag = false;
        do {
            phoneNumber = readLineFromConsole(header);
            if (isPhoneNumber(phoneNumber)) {
                flag = true;
                result = parseLong(phoneNumber);
            } else {
                System.out.println("The phone number is invalid.");
            }
        } while (!flag);

        return result;
    }

    public static String requestAddress() {

        String address = null;
        int choice;
        do {
            System.out.println("Do you want to write the address?");
            System.out.println("1-Yes");
            System.out.println("2-No");
            choice = readIntegerFromConsole("Choice");

            if (choice > 2 || choice <= 0) {
                System.out.println("Invalid choice, try again");
            }
        } while (choice > 2 || choice <= 0);
        switch (choice) {
            case 1:
                do {
                    address = readLineFromConsole("Type the Employee's address");
                } while (address.isEmpty());
                break;
            case 2:
                address = "No Adress";
                break;
        }
        return address;
    }

    public static boolean booleanMethodsReturn(int option) {
        boolean returnBoolean;
        if (option == 1) {
            returnBoolean = true;
        } else {
            returnBoolean = false;
        }
        return returnBoolean;
    }


    public static String requestName() {
        String name;
        do {
            name = readLineFromConsole("Type your name:");
        } while (name.isEmpty());
        return name;
    }

    public static String requestName(String header) {
        String name;
        do {
            name = readLineFromConsole(header);
        } while (name.isEmpty());
        return name;
    }

    static boolean isPhoneNumber(String input) {
        // Remove all non-digit characters from the input string
        String digitsOnly = input.replaceAll("\\D", "");
        // Check if the resulting string has exactly 10 digits
        return digitsOnly.length() == 10 && digitsOnly.matches("\\d{10}");
    }

    static boolean isNineDigitsNumber(long number) {
        String stringNum = Long.toString(number);
        if (stringNum.length() == 9) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isValidTaxAndCitizenNumber(int taxNumber) {
        // US tax number should be a positive 9-digit integer
        if (taxNumber < 0 || taxNumber > 999999999) {
            return false;
        }
        int digits = 0;
        long temp = taxNumber;
        while (temp > 0) {
            digits++;
            temp /= 10;
        }
        return digits == 9;
    }


    public static int requestCardNumber() {
        boolean flag;
        int number = 0;
        do {
            flag = false;
            number = Utils.readIntegerFromConsole("Type your citizen`s card number:");
            if (isValidTaxAndCitizenNumber(number)) {
                flag = true;
            } else {
                System.out.println("Invalid Citizen Number");
            }

        } while (!flag);

        return number;
    }

    public static int requestCardNumber(String header) {
        boolean flag;
        int number = 0;
        do {
            flag = false;
            number = Utils.readIntegerFromConsole(header);
            if (isValidTaxAndCitizenNumber(number)) {
                flag = true;
            } else {
                System.out.println("Invalid Citizen Number");
            }

        } while (!flag);

        return number;
    }

    public static boolean isValidEmail(String email) {
        int atSymbolIndex = email.indexOf('@');
        boolean hasAtSymbol = atSymbolIndex >= 0;
        boolean hasValidDomain = email.endsWith(".com") || email.endsWith(".org") || email.endsWith(".net");

        return hasAtSymbol && hasValidDomain;
    }

    public static String requestEmail() {
        boolean flag = false;
        String mail;
        do {
            mail = readLineFromConsole("Type your email:");
            if (isValidEmail(mail)) {
                flag = true;
            } else {
                System.out.println("Invalid mail");
            }
        } while (!flag);

        return mail;
    }

    public static String requestEmail(String header) {
        boolean flag = false;
        String mail;
        do {
            mail = readLineFromConsole(header);
            if (isValidEmail(mail)) {
                flag = true;
            } else {
                System.out.println("Invalid mail");
            }
        } while (!flag);

        return mail;
    }

    public static int requestTaxNumber() {
        boolean flag;
        int number;
        do {
            flag = false;
            number = Utils.readIntegerFromConsole("Type your tax number:");
            if (isValidTaxAndCitizenNumber(number)) {
                flag = true;
            } else {
                System.out.println("Invalid Tax Number");
            }

        } while (!flag);


        return number;
    }

    public static int requestTaxNumber(String header) {
        boolean flag;
        int number;
        do {
            flag = false;
            number = Utils.readIntegerFromConsole(header);
            if (isValidTaxAndCitizenNumber(number)) {
                flag = true;
            } else {
                System.out.println("Invalid Tax Number");
            }

        } while (!flag);


        return number;
    }
}
