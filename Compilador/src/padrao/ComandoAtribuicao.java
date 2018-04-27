/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;
import Expressao.Expressao;

/**
 *
 * @author Arthur
 */
public class ComandoAtribuicao implements Comando{
    
    private final String nomeVariavel;

    private final Expressao expressao;
    
    public ComandoAtribuicao(String nome, Expressao exp){
        this.nomeVariavel = nome;
        this.expressao = exp;
         if(!Aplicacao.variaveis.containsKey(nomeVariavel)){
             Aplicacao.variaveis.put(this.nomeVariavel, new Variavel(this.expressao, nomeVariavel));
         }
        
    }
    
    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public Expressao getExpressao() {
        return expressao;
    }

    @Override
    public void run() {
            Aplicacao.variaveis.get(this.nomeVariavel).setExpressao(this.expressao);
            System.out.println("VALOR EXPRESSAO ATRIBUICAOOOOO:" + this.expressao.getResultado());
    }
    
}
