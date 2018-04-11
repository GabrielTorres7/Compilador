/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arthur
 */
public class ComandoIfTest {
    
    public ComandoIfTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of executa method, of class ComandoIf.
     */
    @Test
    public void testExecuta() {
        System.out.println("run/ComandoIf");
        ComandoIf instance = new ComandoIf(new ExpressaoLogica(true,"and",true), new ArrayList<Comando>(), false, null);
        ComandoIf instance2 = new ComandoIf(new ExpressaoLogica(false,"and", false), new ArrayList<Comando>(), false, null);
        instance.run();
        instance2.run();
    }
    
}
