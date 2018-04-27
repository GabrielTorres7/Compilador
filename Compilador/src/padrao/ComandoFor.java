/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;
import Expressao.ExpressaoAritmetica;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class ComandoFor implements Comando{
    
    private final ComandoAtribuicao atribuicao;
    private final Integer valorAtribuicao;
    private final String tipo;
    private final Integer fim;
    private final ArrayList<Comando> blocoComandosFor;
    
    public ComandoFor(ComandoAtribuicao atribuicao, String tipo, ExpressaoAritmetica expressao, ArrayList<Comando> comandos){
        this.atribuicao = atribuicao;
        this.valorAtribuicao = ((Double)((ExpressaoAritmetica)atribuicao.getExpressao()).getResultado()).intValue();
        this.tipo = tipo;
        this.fim = ((Double)expressao.getResultado()).intValue();
        this.blocoComandosFor = comandos;
    }

    @Override
    public void run() {
        
        Aplicacao.variaveis.get(atribuicao.getNomeVariavel()).getExpressao().setExpressao(valorAtribuicao.toString());
        
        if(tipo.equals("to")){
            for(Integer iterador=0; iterador < fim; iterador++){
                Aplicacao.variaveis.get(atribuicao.getNomeVariavel()).getExpressao().setExpressao(iterador.toString());
                Aplicacao.variaveis.forEach((k, v)->System.out.println("Variavel: "+k+" Valor : "+v.getExpressao().getResultado()));
                blocoComandosFor.forEach((cmd) -> cmd.run());
            }
        }else if(tipo.equals("downto")){
            for(Integer iterador=0; iterador > fim; iterador--){
                Aplicacao.variaveis.get(atribuicao.getNomeVariavel()).getExpressao().setExpressao(iterador.toString());
                Aplicacao.variaveis.forEach((k, v)->System.out.println("Variavel: "+k+" Valor : "+v.getExpressao().getResultado()));
                blocoComandosFor.forEach((cmd) -> cmd.run());
            }
        }
    }
    
}
