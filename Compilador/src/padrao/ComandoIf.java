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
public class ComandoIf implements Comando{

    private boolean temElse;
    private ExpressaoLogica expressao;
    private ArrayList<Comando> blocoComandosIf;
    private ArrayList<Comando> blocoComandosElse;

    public ComandoIf(ExpressaoLogica expressao, ArrayList<Comando> blocoIf, Boolean temElse, ArrayList<Comando> blocoElse){ 
        this.expressao = expressao;
        this.blocoComandosIf = blocoIf;
        this.temElse = temElse;
        this.blocoComandosElse = blocoElse;
    }
    
    @Override
    public void run() {
        if((Boolean)expressao.resolveExpressao()){
            blocoComandosIf.forEach( (cmd) -> cmd.run());
        }else if(temElse){
            blocoComandosElse.forEach( (cmd) -> cmd.run());
        }
    }
    
}
