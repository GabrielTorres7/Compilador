/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;
import Expressao.ExpressaoAritmetica;
import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class ComandoReadInt implements Comando{
    
    private final String nomeVariavel;
    AnalisaExpressao analisaExpressao;
    
    public ComandoReadInt(String nomeVariavel){
        this.nomeVariavel = nomeVariavel;
        analisaExpressao = new AnalisaExpressao();
    }
    
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        Aplicacao.variaveis.get(this.nomeVariavel).setExpressao(  analisaExpressao.getResultado(Integer.toString(sc.nextInt())));
    }
    
}
