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
public class ComandoFor implements Comando{
    
    private ExpressaoAritmetica iterador;
    private String tipo;
    private Integer fim;
    private ArrayList<Comando> blocoComandosFor;
    
    public ComandoFor(Variavel var, String tipo, ExpressaoAritmetica expressao, ArrayList<Comando> comandos){
        this.iterador = (ExpressaoAritmetica)var.getExpressao();
        this.tipo = tipo;
        this.fim = ((Double)expressao.ResolveExpressao()).intValue();
        this.blocoComandosFor = comandos;
    }

    @Override
    public void run() {
        if(tipo.equals("to")){
            for(((Double)iterador.ResolveExpressao()).intValue(); iterador.getResultado()<fim; iterador.setResultado(iterador.getResultado()+1)){
                blocoComandosFor.forEach((cmd) -> cmd.run());
            }
        }else if(tipo.equals("downto")){
            for(((Double)iterador.ResolveExpressao()).intValue(); iterador.getResultado()<fim; iterador.setResultado(iterador.getResultado()-1)){
                blocoComandosFor.forEach( (cmd) -> cmd.run() );
            }
        }
    }
    
}
