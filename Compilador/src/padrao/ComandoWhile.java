/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import Expressao.ExpressaoLogica;
import java.util.ArrayList;
/**
 *
 * @author Arthur
 */
public class ComandoWhile implements Comando{
    
    private final String expressao;
    private final ArrayList<Comando> blocoComandosWhile;
    AnalisaExpressao analisaExpressao;
    
    public ComandoWhile(String expressao, ArrayList<Comando> comandos){
        analisaExpressao = new AnalisaExpressao();
        this.expressao = expressao;
        this.blocoComandosWhile = comandos;
    }

    @Override
    public void run() {
        while((boolean)analisaExpressao.getResultado(expressao).getResultado()){
            blocoComandosWhile.forEach( (cmd) -> cmd.run() );
        }
    }
    
}
