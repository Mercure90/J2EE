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
public class Address implements Serializable{

    private String street;
    private Integer zipcode;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }



}
