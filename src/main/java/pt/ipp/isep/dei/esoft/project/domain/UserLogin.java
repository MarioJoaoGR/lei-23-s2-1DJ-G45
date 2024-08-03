package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type User login.
 */
public class UserLogin implements Serializable {
    private String name;
    private String email;
    private String pwd;
    private String role;

    /**
     * Instantiates a new User login.
     *
     * @param name  the name
     * @param email the email
     * @param pwd   the pwd
     * @param role  the role
     */
    public UserLogin(String name, String email, String pwd, String role) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.role = role;
    }

    /**
     * Instantiates a new User login.
     */
    public UserLogin() {
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets pwd.
     *
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Sets pwd.
     *
     * @param pwd the pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogin userLogin = (UserLogin) o;
        return Objects.equals(name, userLogin.name) && Objects.equals(email, userLogin.email) && Objects.equals(pwd, userLogin.pwd) && Objects.equals(role, userLogin.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, pwd, role);
    }
}
