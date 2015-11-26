/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ex3;

import ex3.domain.*;
import javax.ejb.Remote;


/**
 *
 * @author asbriglio
 */
@Remote
public interface AccountCreatorServiceRemote {

    public void addLoginInfos(Login login);
    public void addIdentityInfos(Customer customer);
    public void addAddress(Address address);
    public void createAccount();

}
