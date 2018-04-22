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
    
    private ExpressaoLogica expressao;
    private ArrayList<Comando> blocoComandosWhile;
    
    public ComandoWhile(ExpressaoLogica expressao, ArrayList<Comando> comandos){
        this.expressao = expressao;
        this.blocoComandosWhile = comandos;
    }

    @Override
    public void run() {
        while((boolean)expressao.resolveExpressao()){
            blocoComandosWhile.forEach( (cmd) -> cmd.run() );
        }
    }
    
}
