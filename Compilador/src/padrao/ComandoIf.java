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
public class ComandoIf implements Comando{

    private final boolean temElse;
    private final String expressao;
    AnalisaExpressao analisaExpressao;
    private final ArrayList<Comando> blocoComandosIf;
    private final ArrayList<Comando> blocoComandosElse;

    public ComandoIf(String expressao, ArrayList<Comando> blocoIf, Boolean temElse, ArrayList<Comando> blocoElse){ 
        analisaExpressao = new AnalisaExpressao();
        this.expressao = expressao;
        this.blocoComandosIf = blocoIf;
        this.temElse = temElse;
        this.blocoComandosElse = blocoElse;
    }
    
    @Override
    public void run() {
        if((Boolean)analisaExpressao.getResultado(expressao).getResultado()){
            blocoComandosIf.forEach( (cmd) -> cmd.run());
        }else if(temElse){
            blocoComandosElse.forEach( (cmd) -> cmd.run());
        }
    }
    
}
