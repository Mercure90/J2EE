/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cesi
 */
public class HelloBeanTest {
    private static EJBContainer container = EJBContainer.createEJBContainer();
    public HelloBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sayHello method, of class HelloBean.
     */
    
    
    @Test
    public void testSayHello() throws NamingException {
        Context context = container.getContext();
        String globalJNDIName = "java:global/classes/HelloBean";
        HelloService ejbService =(HelloService) context.lookup(globalJNDIName);
        assertNotNull(ejbService);
        String name = "Cesar";
        String returnMessage = "La chaîne retournée est incorrecte";
        String expResult = "Hello " + name;
        assertEquals(returnMessage,expResult, ejbService.sayHello(name));
        context.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
