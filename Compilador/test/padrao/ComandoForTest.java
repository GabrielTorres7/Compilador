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
    public void test1() {
        System.out.println("Teste 1");
        
        Variavel i = new Variavel(new ExpressaoAritmetica("0"), "teste");
        ExpressaoAritmetica condicaoParada = new ExpressaoAritmetica("10");
        String tipo = "to";
        ArrayList<Comando> comandos = new ArrayList<>();
        Aplicacao.variaveis.put("teste", i);
        
        comandos.add(new ComandoAtribuicao("condicaoParada", condicaoParada));
        comandos.add(new ComandoPrint(Aplicacao.variaveis.get("condicaoParada").getExpressao()));
        comandos.add(new ComandoPrintln());
        comandos.add(new ComandoPrint(i.getExpressao()));
        comandos.add(new ComandoPrintln());
        
        /* 
                for (int i = 0; i < 5; i++){
                        System.out.println("Indice " + i);
                }
        */
        
        ComandoFor instance = new ComandoFor("teste", tipo, condicaoParada, comandos);
        instance.run();
    }
    
}
