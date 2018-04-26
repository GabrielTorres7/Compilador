/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;
import KKKKK.*;
import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class ComandoReadInt implements Comando{
    
    private final String nomeVariavel;
    
    public ComandoReadInt(String nomeVariavel){
        this.nomeVariavel = nomeVariavel;
    }
    
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        Aplicacao.variaveis.get(this.nomeVariavel).setExpressao( new ExpressaoAritmetica( ( (Integer)sc.nextInt()).toString() ) );
    }
    
}
