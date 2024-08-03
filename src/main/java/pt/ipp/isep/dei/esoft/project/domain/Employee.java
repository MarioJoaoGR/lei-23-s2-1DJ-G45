package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Employee.
 */
public class Employee implements Serializable {

    private int id;
    private static int idCount = 1;
    private String name;
    private long phoneNumber;
    private int taxNumber;
    private String password;
    private int civilNumber;
    private String address;
    private String mail;
    private final String ADDRESS_BY_OMISSION = "No address";

    /**
     * Instantiates a new Employee.
     */
    public Employee() {
    }

    /**
     * Instantiates a new Employee.
     *
     * @param email the email
     * @param name  the name
     */
    public Employee(String email, String name) {
        this.id = idCount++;
        this.mail = email;
        this.name = name;
    }

    /**
     * Instantiates a new Employee.
     *
     * @param id          id do Employee
     * @param name        nome do Employee
     * @param phoneNumber numero de telemovel do Employee
     * @param taxNumber   taxnumber do Employee
     * @param password    password do Employee
     * @param civilNumber civilnumber do Employee
     * @param address     address do Employee
     * @param mail        email do Employee
     */
    public Employee(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.password = password;
        this.civilNumber = civilNumber;
        this.address = address;
        this.mail = mail;
    }

    /**
     * Instantiates a new Employee.
     *
     * @param id          id do Employee
     * @param name        nome do Employee
     * @param phoneNumber numero de telemovel do Employee
     * @param taxNumber   taxnumber do Employee
     * @param password    password do Employee
     * @param civilNumber civilnumber do Employee
     * @param mail        email do Employee
     */
    public Employee(int id, String name, long phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.password = password;
        this.civilNumber = civilNumber;
        this.address = ADDRESS_BY_OMISSION;
        this.mail = mail;
    }

    /**
     * Instantiates a new Employee.
     *
     * @param name        nome do Employee
     * @param phoneNumber numero de telemovel do Employee
     * @param taxNumber   taxnumber do Employee
     * @param password    password do Employee
     * @param civilNumber civilnumber do Employee
     * @param address     address do Employee
     * @param mail        email do Employee
     */
    public Employee(String name, long phoneNumber, int taxNumber, String password, int civilNumber, String address, String mail) {
        this.id = idCount++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.password = password;
        this.civilNumber = civilNumber;
        this.address = address;
        this.mail = mail;
    }

    /**
     * Instantiates a new Employee.
     *
     * @param name        nome do Employee
     * @param phoneNumber numero de telemovel do Employee
     * @param taxNumber   taxnumber do Employee
     * @param password    password do Employee
     * @param civilNumber civilnumber do Employee
     * @param mail        email do Employee
     */
    public Employee(String name, long phoneNumber, int taxNumber, String password, int civilNumber, String mail) {
        this.id = idCount++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.password = password;
        this.civilNumber = civilNumber;
        this.mail = mail;
    }

    /**
     * compara os objetos se são do msm tipo e depois se têm características/atributos iguais
     *
     * @param o
     * @return true se os objetos forem iguais
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        if (password==null && name==null){
            return phoneNumber == employee.phoneNumber && taxNumber == employee.taxNumber && civilNumber == employee.civilNumber && Objects.equals(address, employee.address) && mail.equals(employee.mail);

        }else if(password==null) {
            return phoneNumber == employee.phoneNumber && taxNumber == employee.taxNumber && civilNumber == employee.civilNumber && name.equals(employee.name)  && Objects.equals(address, employee.address) && mail.equals(employee.mail);

        }else if(name==null) {
            return phoneNumber == employee.phoneNumber && taxNumber == employee.taxNumber && civilNumber == employee.civilNumber  && password.equals(employee.password) && Objects.equals(address, employee.address) && mail.equals(employee.mail);

        }else {
            return phoneNumber == employee.phoneNumber && taxNumber == employee.taxNumber && civilNumber == employee.civilNumber &&name.equals(employee.name) && password.equals(employee.password) && Objects.equals(address, employee.address) && mail.equals(employee.mail);

        }

    }

    /**
     * values – the values to be hashed
     *
     * @return a hash value of the sequence of input values
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, taxNumber, password, civilNumber, address, mail);
    }

    /**
     * Gets id.
     *
     * @return Employye 's id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets mail.
     *
     * @return Empoloyee 's email
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Sets id.
     *
     * @param id Employee's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return Employee 's name
     */
    public String getName() {
        return name;
    }

    /**
     * changes Employee's name
     *
     * @param name Employee's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone number.
     *
     * @return Employee 's phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * changes Employee's phone number
     *
     * @param phoneNumber Employee's phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets tax number.
     *
     * @return Employee 's taxnumber
     */
    public int getTaxNumber() {
        return taxNumber;
    }

    /**
     * * changes Employee's taxnumber
     *
     * @param taxNumber Employee's taxnumber
     */
    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * Gets password.
     *
     * @return Employee 's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * * changes Employee's password
     *
     * @param password Employee's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets civil number.
     *
     * @return Employee 's civil number
     */
    public int getCivilNumber() {
        return civilNumber;
    }

    /**
     * * changes Employee's civil number
     *
     * @param civilNumber Employee's civil number
     */
    public void setCivilNumber(int civilNumber) {
        this.civilNumber = civilNumber;
    }

    /**
     * Gets address.
     *
     * @return EMployee 's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * * changes Employee's address
     *
     * @param address Employee's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * checks if Employee has a name
     *
     * @param name Employee's name
     * @return true if Employee has a name
     */
    public boolean hasName(String name) {
        return this.name.equals(name);
    }


    // public abstract String getRoleName();


    /**
     * Gets id count.
     *
     * @return the id count
     */
    public static int getIdCount() {
        return idCount;
    }

    /**
     * Sets id count.
     *
     * @param idCount the id count
     */
    public static void setIdCount(int idCount) {
        Employee.idCount = idCount;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets address by omission.
     *
     * @return the address by omission
     */
    public String getADDRESS_BY_OMISSION() {
        return ADDRESS_BY_OMISSION;
    }
}