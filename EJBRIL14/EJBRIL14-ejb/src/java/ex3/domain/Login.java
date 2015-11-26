/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ex3.domain;

import java.io.Serializable;

/**
 *
 * @author asbriglio
 */
public class Login implements Serializable{

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
