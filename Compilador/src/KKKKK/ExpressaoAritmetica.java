/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KKKKK;

/**
 *
 * @author Arthur
 */
public class ExpressaoAritmetica extends Expressao{
    
    public ExpressaoAritmetica(String expressao){
        super(new ResolveExpressaoAritmetica(), expressao);
    }

    @Override
    public Object getResultado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
