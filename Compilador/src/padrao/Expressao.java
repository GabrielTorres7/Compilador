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
    
    public T resolveExpressao();
    public T resolveExpressao(String expressao);
            
}
