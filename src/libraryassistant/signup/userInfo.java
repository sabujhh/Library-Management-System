/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryassistant.signup;

/**
 *
 * @author Hasan Sabuj
 */
public class userInfo {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String confirmpass;
    
    public userInfo() {
    }

    public userInfo(int id, String name, String email, String password, String confirmpass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmpass = confirmpass;
    }
    
    public userInfo(String email, String password, String confirmpass) {
        this.email = email;
        this.password = password;
        this.confirmpass = confirmpass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    @Override
    public String toString() {
        return "userInfo{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", confirmpass=" + confirmpass + '}';
    }
    
    
}
