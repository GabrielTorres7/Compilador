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
    AnalisaExpressao analisaExpressao;
    private final String expressao;
    
    public ComandoAtribuicao(String nome, String exp){
        this.nomeVariavel = nome;
        this.expressao = exp;
        analisaExpressao = new AnalisaExpressao();
         if(!Aplicacao.variaveis.containsKey(nomeVariavel)){
             Aplicacao.variaveis.put(this.nomeVariavel, new Variavel(analisaExpressao.getResultado(expressao), nomeVariavel));
         }
        
    }
    
    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public Expressao getExpressao() {
        return analisaExpressao.getResultado(expressao);
    }

    @Override
    public void run() {
            Aplicacao.variaveis.get(this.nomeVariavel).setExpressao(analisaExpressao.getResultado(expressao));
    }
    
}
