/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;
import KKKKK.*;
/**
 *
 * @author Arthur
 */
public class Variavel {
    
    private Expressao expressao;
    
    public Variavel(Expressao expressao, String nome){
        this.expressao = expressao;        
    }
    
    public Expressao getExpressao() {
        return expressao;
    }

    public void setExpressao(Expressao expressao) {
        this.expressao = expressao;
    }
    
    public Object getValor(){
        
        return expressao.getResultado();
    }
    
}
