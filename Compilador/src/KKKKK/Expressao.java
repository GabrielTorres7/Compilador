/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KKKKK;

/**
 *
 * @author Arthur
 * @param <T>
 */
public abstract class Expressao<T> {
    
    protected ResolveExpressao resolve;
    private String expressao;

    protected Expressao(ResolveExpressao resolve, String expressao) {
        this.resolve = resolve;
        this.expressao = expressao;
    }
    
    public abstract T getResultado();

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }
    
    
}
