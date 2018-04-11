/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

/**
 *
 * @author Arthur
 */
public class ComandoAtribuicao implements Comando{
    
    private Variavel var;
    private Expressao expressao;
    
    public ComandoAtribuicao(Variavel var, Expressao exp){
        this.expressao = exp;
    }
    
    @Override
    public void run() {
        var.setExpressao(expressao);
    }
    
}
