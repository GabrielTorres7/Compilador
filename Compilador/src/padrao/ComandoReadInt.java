/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class ComandoReadInt implements Comando{
    
    private Variavel variavel;
    
    public ComandoReadInt(Variavel var){
        this.variavel = var;
    }
    
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
       // variavel.setExpressao(new ExpressaoAritmetica(sc.nextDouble(), "+", 0.0));
    }
    
}
