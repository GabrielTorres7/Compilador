/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class ComandoAtribuicao implements Comando{
    
    private String nomeVariavel;
    private Expressao expressao;
    
    public ComandoAtribuicao(String nome, Expressao exp){
        this.nomeVariavel = nome;
        this.expressao = exp;
    }

    @Override
    public void run() {
       Aplicacao.variaveis.get(this.nomeVariavel).setExpressao(this.expressao);
    }
    
}
