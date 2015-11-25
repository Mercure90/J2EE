/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author mpastore
 */
@Stateless(name = "HelloManager")
@LocalBean
public class HelloBean implements Hello {

    
    /**
     *
     * @param name
     * @return
     */
    @Override
    public String sayHello(String name) {
        System.out.println("Serveur>>sayHello("+name+")");
        return "Hello " + name;
    }
   
}
