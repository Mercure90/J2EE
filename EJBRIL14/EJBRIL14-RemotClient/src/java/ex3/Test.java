/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex3;

import ex3.domain.*;
import javax.ejb.EJB;

/**
 *
 * @author cesi
 */
public class Test {
    
    @EJB
    private static AccountCreatorServiceRemote accountCreatorService;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //simulation d'un workflow de création d'un compte utilisateur en plusieurs étapes

        // étape 1 : création de l'utilisateur (nom et prénom renseignés)
        Customer cust = new Customer();
        cust.setFirstname("Jean");
        cust.setLastname("Doe");
        accountCreatorService.addIdentityInfos(cust);
        
        //étape 2 : création des informations d'authentification
        Login log = new Login();
        log.setUsername("jojo123");
        log.setPassword("azerty");//mdp respectant parfaitement les règles de sécurité :)
        accountCreatorService.addLoginInfos(log);
               
        //étape 3 : création de l'adresse
        Address adr = new Address();
        adr.setStreet("43 rue ...");
        adr.setZipcode(13004);
        adr.setCity("Marseille");
        accountCreatorService.addAddress(adr);

        //étape 4 : création du compte
        accountCreatorService.createAccount();

    }

    
}
