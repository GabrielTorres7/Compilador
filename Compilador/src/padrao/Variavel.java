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
public class Variavel {
    
    private Expressao expressao;
    
    public Variavel(Expressao expressao, String nome){
        this.expressao = expressao;        
        this.colocaNoMap(nome);
    }
    
    private void colocaNoMap(String nome){
        Aplicacao.variaveis.put(nome, this);
    }
    
    public Expressao getExpressao() {
        return expressao;
    }

    public void setExpressao(Expressao expressao) {
        this.expressao = expressao;
    }
    
    public Object getValor(){
        expressao.ResolveExpressao();
        if(expressao instanceof ExpressaoAritmetica){
            expressao.ResolveExpressao();
            return ((ExpressaoAritmetica)expressao).getResultado();
        }else if(expressao instanceof ExpressaoLogica){
            expressao.ResolveExpressao();
            return ((ExpressaoLogica)expressao).getResultado();
        }
        return null;
    }
    
}
