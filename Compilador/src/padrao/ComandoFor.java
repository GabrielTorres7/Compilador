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
    
    private final String iterador;
    private final String tipo;
    private final Integer fim;
    private final ArrayList<Comando> blocoComandosFor;
    
    public ComandoFor(String nomeVariavel, String tipo, ExpressaoAritmetica expressao, ArrayList<Comando> comandos){
        this.iterador = nomeVariavel;
        this.tipo = tipo;
        this.fim = ((Double)expressao.getResultado()).intValue();
        this.blocoComandosFor = comandos;
    }

    @Override
    public void run() {
        if(tipo.equals("to")){
            for(    ( (Double) Aplicacao.variaveis.get(iterador).getExpressao().getResultado() ).intValue();
                    ( (Double) Aplicacao.variaveis.get(iterador).getExpressao().getResultado() ).intValue() < fim;
                    Aplicacao.variaveis.get(iterador).getExpressao().setExpressao( ((Double)((Double)Aplicacao.variaveis.get(iterador).getExpressao().getResultado() + 1.0)).toString() )){
                blocoComandosFor.forEach((cmd) -> cmd.run());
            }
        }else if(tipo.equals("downto")){
            for(    ( (Double) Aplicacao.variaveis.get(iterador).getExpressao().getResultado() ).intValue();
                    ( (Double) Aplicacao.variaveis.get(iterador).getExpressao().getResultado() ).intValue() > fim;
                    Aplicacao.variaveis.get(iterador).getExpressao().setExpressao( ((Double)((Double)Aplicacao.variaveis.get(iterador).getExpressao().getResultado() - 1.0)).toString() )){
                blocoComandosFor.forEach( (cmd) -> cmd.run() );
            }
        }
    }
    
}
