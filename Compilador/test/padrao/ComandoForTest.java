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
public class ComandoForTest {
    
    public ComandoForTest() {
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
     * Test of executa method, of class ComandoFor.
     */
    @Test
    public void testExecuta() {
        System.out.println("run/ComandoFor");
        Variavel var = new Variavel();
        var.setExpressao(new ExpressaoAritmetica(0.0 , "+", 0.0));
        ComandoFor instance = new ComandoFor(var, "to", new ExpressaoAritmetica(0.0 , "+", 10.0), new ArrayList<>());
        var.setExpressao(new ExpressaoAritmetica(0.0 , "+", 20.0));
        ComandoFor instance2 = new ComandoFor(var, "downto", new ExpressaoAritmetica(0.0 , "+", 10.0), new ArrayList<>());
        System.out.println("Teste 1");
        instance.run();
        System.out.println("Teste 2");
        instance2.run();

    }
    
}
