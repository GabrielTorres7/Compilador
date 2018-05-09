/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressao;

/**
 *
 * @author Arthur
 */
public class ExpressaoConstante extends Expressao{
    
    public ExpressaoConstante(ResolveExpressao resolve, String expressao) {
        super(null, expressao);
    }

    public ExpressaoConstante(String expressao) {
        super(null, expressao);
    }
    
    @Override
    public Object getResultado(){
        return super.getExpressao();
    }
}
