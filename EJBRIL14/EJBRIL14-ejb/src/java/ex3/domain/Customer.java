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
public class Customer implements Serializable{

    private Long id;
    private String firstname;
    private String lastname;
    private Login login;
    private Address address;
    
    

    //constructeur sans arguments.
    public Customer() {

       
    }

    //la clé primaire correspondante est générée automatiquement dans la base sous-jacente
    //la propriété est donc en lecture seule.
    public Long getId() {
        return id;
    }

    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    
     
    
}
