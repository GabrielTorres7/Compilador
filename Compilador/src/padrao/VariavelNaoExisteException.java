/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

/**
 *
 * @author Gabriel
 */
public class VariavelNaoExisteException extends RuntimeException{
    
    public VariavelNaoExisteException(String mensagem){
        super(mensagem);
    }
}
