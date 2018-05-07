/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import Expressao.Expressao;
/**
 *
 * @author Arthur
 */
public class ComandoPrint implements Comando{
    
    private final String expressao;
    AnalisaExpressao analisaExpressao;
    
    public ComandoPrint(String expressao){
        this.expressao = expressao;
        analisaExpressao = new AnalisaExpressao(expressao);
    }

    @Override
    public void run() {
        System.out.print(analisaExpressao.getResultado(expressao).getResultado());  //Metodo que imprime o valor da mensagem
    }
    
}
