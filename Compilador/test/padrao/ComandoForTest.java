/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import Expressao.ExpressaoAritmetica;
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
    public void test1() {
        System.out.println("Teste 1");
        /* 
                for (int i = 0; i < 5; i++){
                        System.out.println("Indice " + i);
                }
        */
        ComandoAtribuicao i = new ComandoAtribuicao("teste", "0");
        ExpressaoAritmetica condicaoParada = new ExpressaoAritmetica("5");
        String tipo = "to";
        ArrayList<Comando> comandos = new ArrayList<>();
        
        comandos.add(new ComandoPrint(i.getExpressao().getExpressao()));
        comandos.add(new ComandoPrintln());
        
        
        ComandoFor instance = new ComandoFor(i, tipo, condicaoParada, comandos);
        instance.run();
    }
    
    @Test
    public void test2(){
        /* 
                for (int i = 0; i < 5; i++){
                        System.out.println("Indice i: " + i );
                        for(int j = 10; j > 5; j--){
                            System.out.println("Indice j: " + j );
                        }
                }
        */
        System.out.println("Teste 2");
        
        ComandoAtribuicao i = new ComandoAtribuicao("teste", "0");
        ComandoAtribuicao j = new ComandoAtribuicao("teste2", "15");
        
        String tipo1 = "to";
        String tipo2 = "downto";
        
        ExpressaoAritmetica condicaoParada = new ExpressaoAritmetica("5");
        ExpressaoAritmetica condicaoParada2 = new ExpressaoAritmetica("10");

        ArrayList<Comando> comandos2 = new ArrayList<>();
        comandos2.add(new ComandoPrint(j.getExpressao().getExpressao()));
        comandos2.add(new ComandoPrintln());
        ComandoFor teste2 = new ComandoFor(j, tipo2, condicaoParada2, comandos2);   
        
        ArrayList<Comando> comandos = new ArrayList<>();
        comandos.add(new ComandoPrint(i.getExpressao().getExpressao()));
        comandos.add(new ComandoPrintln());
        comandos.add(teste2);
        
        
        ComandoFor teste1 = new ComandoFor(i, tipo1, condicaoParada, comandos);
        teste1.run();
    }
    
}
