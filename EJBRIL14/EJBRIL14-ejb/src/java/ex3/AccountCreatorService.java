/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ex3;


import ex3.domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;


/**
 *
 * @author asbriglio
 * note : l'utilisation de SFSB peut entraîner des réductions de performance.
 * Malgré l'optimisation des conteneurs EJB, Il est conseillé de minimiser l'utilisation de composants
 * à état. Lors de vos conceptions demandez-vous, s'il n'y a pas une alternative stateless envisageable
 *
 * rappel du cycle de vie:
 *       - le client invoque une méthode du Bean
 *       - une instance est créée
 *       - les références aux ressources sont injectées(ici, datasource)
 *       - les méthodes annotées @PostConstruct sont déclenchées. ici init() chargée d'établir la connexion avec la base
 *       - l'instance est stockée en mémoire et "liée" au client
 *       - la méthode métier invoquée s'exécute.
     *       * Si un certain laps de temps s'écoule l'instance est passivée
     *                         -- les méthodes PrePassivate (si existantes) sont déclenchées : ici on ferme la connexion qui est "lourde" et non sérialisable
     *                         -- les variables d'état sont sérialisées (si implémentent Serializable)
     *                         -- le bean est stocké sur le disque
     *        * si le client invoque son bean alors qu'il est passivé, l'instance associée est activée (retour en mémoire tas)
     *                         -- les var d'état sont désérialisées
     *                         -- les méthodes PostActivate sont déclenchées : ici on rétablit la connexion
     *                         la méthode invoquée s'exécute.
     *       *Si un délai est dépassé l'instance Stateful passivée est détruite.
 *       - ... invocations d'autres méthode métier
 *       - la méthode métier annotée @Remove est invoquée, l'instance est supprimée par le container (quand la méthode se termine)
 *                         -- la méthode PreDestroy est déclenchée au préalable
 * Note : il peut y avoir plusieurs méthodes métier annotées @Remove
 */

public class AccountCreatorService implements AccountCreatorServiceRemote {

    
    //injection de la source de données
    @Resource(lookup="jdbc/custDS")
    private DataSource ds;

    private Connection cn;

    //représente l'état du SFSB conservé tout au long du dialogue avec le client
    private Customer customer;

    public AccountCreatorService() {
        
    }

    //méthodes de callback
    @PostConstruct
    void init(){
        
        try {
              System.out.println("init_***************************");//cf console Glassfish 
            cn = ds.getConnection();//une instance Connection est assignée au stateful
        } catch (SQLException ex) {
            //cf. les journaux de log dans la console d'admin GF
            Logger.getLogger(AccountCreatorService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EJBException(ex.getMessage()); //on relance une exception système - Destruction de l'instance sans invocation de PreDestroy
            
        }

    }

    @PreDestroy
    void clear() {
        try {
              System.out.println("clear_***************************");
            cn.close();//libération de la connection
            cn=null;
        } catch (SQLException ex) {
            Logger.getLogger(AccountCreatorService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EJBException(ex.getMessage()); //on relance une exception système 
        }
    }

    //méthodes métier
    @Override
    public void addIdentityInfos(Customer customer) {
          System.out.println("addIdentity_***************************");
       this.customer=customer;
      
    }

    @Override
    public void addLoginInfos(Login login) {
          System.out.println("addLogin_***************************");
       customer.setLogin(login);
    }

    @Override
    public void addAddress(Address address) {
          System.out.println("addAddress_***************************");
        customer.setAddress(address);
    }

    @Override
    public void createAccount() {
          System.out.println("createAccount_***************************");
        try {

            //requête d'insertion.
            String sql = "INSERT INTO customers (firstname,lastname,username,password,street,zipcode,city)"+
             " VALUES(?,?,?,?,?,?,?)";

              try (PreparedStatement stmt = cn.prepareStatement(sql)) {
                  stmt.setString(1, customer.getFirstname());
                  stmt.setString(2, customer.getLastname());
                  stmt.setString(3, customer.getLogin().getUsername());
                  stmt.setString(4, customer.getLogin().getPassword());
                  stmt.setString(5, customer.getAddress().getStreet());
                  stmt.setInt(6, customer.getAddress().getZipcode());
                  stmt.setString(7, customer.getAddress().getCity());
                  
                  stmt.executeUpdate();
              }
        } catch (SQLException ex) {
            Logger.getLogger(AccountCreatorService.class.getName()).log(Level.SEVERE, null, ex);
            throw new EJBException(ex.getMessage()); //on relance une exception système - PreDestroy n'est pas invoquée
        }
    }

}
