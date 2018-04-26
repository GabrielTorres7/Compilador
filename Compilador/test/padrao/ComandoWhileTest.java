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
import KKKKK.*;
/**
 *
 * @author Arthur
 */
public class ComandoWhileTest {
    
    public ComandoWhileTest() {
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
     * Test of executa method, of class ComandoWhile.
     */
    @Test
    public void testExecuta() {
        ComandoPrint cmdP = new ComandoPrint(new ExpressaoLogica("true"));
        ComandoPrint cmdP2 = new ComandoPrint(new ExpressaoLogica("false"));
        ArrayList<Comando> comandos = new ArrayList<>();
        comandos.add(cmdP);
        comandos.add(cmdP2);
        
        ComandoWhile instance = new ComandoWhile(new ExpressaoLogica("true"), comandos);
        instance.run();
    }
    
}
