/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

/**
 *
 * @author Allan
 */
public interface Expressao<T>{

    public T ResolveExpressao(T operador1, String operando,T operador2);
    public T ResolveExpressao(T operador1, String operando);
            
}
