/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import java.util.ArrayList;
import static sun.security.krb5.Confounder.intValue;

/**
 *
 * @author Arthur
 */
public class ComandoFor implements Comando{
    
    private final String iterador;
    private final String tipo;
    private final Integer fim;
    private final ArrayList<Comando> blocoComandosFor;
    
    public ComandoFor(String nomeVariavel, String tipo, ExpressaoAritmetica expressao, ArrayList<Comando> comandos){
        this.iterador = nomeVariavel;
        this.tipo = tipo;
        this.fim = ((Double)expressao.resolveExpressao()).intValue();
        this.blocoComandosFor = comandos;
    }

    @Override
    public void run() {
        if(tipo.equals("to")){
            for(    ( (Double) Aplicacao.variaveis.get(iterador).getExpressao().resolveExpressao() ).intValue();
                    ( (ExpressaoAritmetica) Aplicacao.variaveis.get(iterador).getExpressao() ).getResultado() < fim;
                    ( (ExpressaoAritmetica) Aplicacao.variaveis.get(iterador).getExpressao()).setResultado( ( (ExpressaoAritmetica) Aplicacao.variaveis.get(iterador).getExpressao() ).getResultado()+1)){
                blocoComandosFor.forEach((cmd) -> cmd.run());
            }
        }else if(tipo.equals("downto")){
            for(    ( (Double) Aplicacao.variaveis.get(iterador).getExpressao().resolveExpressao() ).intValue();
                    ( (ExpressaoAritmetica) Aplicacao.variaveis.get(iterador).getExpressao() ).getResultado() > fim;
                    ( (ExpressaoAritmetica) Aplicacao.variaveis.get(iterador).getExpressao()).setResultado( ( (ExpressaoAritmetica) Aplicacao.variaveis.get(iterador).getExpressao() ).getResultado()-1)){
                blocoComandosFor.forEach( (cmd) -> cmd.run() );
            }
        }
    }
    
}
