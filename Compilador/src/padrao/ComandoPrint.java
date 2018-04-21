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
public class ComandoPrint implements Comando{
    
    private final Expressao mensagem;
    
    public ComandoPrint(Expressao mensagem){
        this.mensagem = mensagem;
    }

    @Override
    public void run() {
        System.out.print(mensagem.ResolveExpressao());  //Metodo que imprime o valor da mensagem
    }
    
}
