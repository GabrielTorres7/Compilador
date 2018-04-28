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
import Expressao.ExpressaoLogica;
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
        ComandoPrint cmdP = new ComandoPrint(new ExpressaoLogica("true"));
        ComandoPrint cmdP2 = new ComandoPrint(new ExpressaoLogica("false"));
        ArrayList<Comando> cmdif = new ArrayList<>();
        ArrayList<Comando> cmdelse = new ArrayList<>();
        cmdif.add(cmdP);
        cmdelse.add(cmdP2);

        System.out.println("run/ComandoIf");
        ComandoIf instance = new ComandoIf(new ExpressaoLogica("true"), cmdif, false, cmdif);
        ComandoIf instance2 = new ComandoIf(new ExpressaoLogica("false"), cmdif, true, cmdelse);
        ComandoIf instance3 = new ComandoIf(new ExpressaoLogica("false"), cmdif, false, cmdelse);
        
        instance.run();
        System.out.println();
        instance2.run();
        System.out.println();
        instance3.run();
    }
    
}