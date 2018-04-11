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
    private Expressao expressao;
    private ArrayList<Comando> blocoComandosIf;
    private ArrayList<Comando> blocoComandosElse;

    public ComandoIf(Expressao expressao, ArrayList<Comando> blocoIf, Boolean temElse, ArrayList<Comando> blocoElse){ 
        this.expressao = expressao;
        this.blocoComandosIf = blocoIf;
        this.temElse = temElse;
        this.blocoComandosElse = blocoElse;
    }
    
    @Override
    public void run() {
        if((Boolean)expressao.ResolveExpressao()){
            blocoComandosIf.forEach( (cmd) -> cmd.run());
            System.out.println("Teste if");
        }else if(temElse){
            blocoComandosElse.forEach( (cmd) -> cmd.run());
        }
    }
    
}
