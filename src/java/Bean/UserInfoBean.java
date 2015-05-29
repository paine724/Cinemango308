/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Andrew
 */
public class UserInfoBean {

    private int userid;
    private String email;
    private String role;
    private String firstName;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private int password;
    private String address;

    /**
     * Creates a new instance of UserInfoBean
     */
    public UserInfoBean() {
    }

    public String getEmail() {
        return email;
    }

    public int getUserId() {
        return userid;
    }

    public void setEmail(String s) {
        email = s;
    }

    public void setUserId(int s) {
        userid = s;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setRole(String s) {
        role = s;
    }

    public void setFirstName(String s) {
        firstName = s;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
