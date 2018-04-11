/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class ComandoWhile implements Comando{
    
    private Expressao expressao;
    private ArrayList<Comando> blocoComandosWhile;
    
    public ComandoWhile(Expressao expressao, ArrayList<Comando> comandos){
        this.expressao = expressao;
        this.blocoComandosWhile = comandos;
    }

    @Override
    public void run() {
        while((boolean)expressao.ResolveExpressao()){
            blocoComandosWhile.forEach( (cmd) -> cmd.run() );
            System.out.println("While");
            ((ExpressaoLogica)expressao).setOperador1(false);
        }
    }
    
}
