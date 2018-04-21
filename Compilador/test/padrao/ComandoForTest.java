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
        Variavel var = new Variavel(new ExpressaoAritmetica(0.0 , "+", 0.0), "teste");
        ArrayList<Comando> comandos = new ArrayList<>();
        Aplicacao.variaveis.put("teste", var);
        comandos.add(new ComandoPrint(new ExpressaoAritmetica(0.0 , "+", 1.0)));
        comandos.add(new ComandoPrint(new ExpressaoAritmetica(0.0 , "+", 2.0)));
        //comandos.add(new ComandoPrintln());
        
        var.setExpressao(new ExpressaoAritmetica(0.0 , "+", 0.0));
        ComandoFor instance = new ComandoFor(var, "to", new ExpressaoAritmetica(0.0 , "+", 10.0), comandos);
        System.out.println("Teste 1");
        instance.run();
        
        var.setExpressao(new ExpressaoAritmetica(0.0 , "+", 20.0));
        ComandoFor instance2 = new ComandoFor(var, "downto", new ExpressaoAritmetica(0.0 , "+", 10.0), comandos);
        System.out.println("Teste 2");
        instance2.run();

    }
    
}
