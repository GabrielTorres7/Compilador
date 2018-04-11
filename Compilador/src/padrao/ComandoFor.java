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
    
    public ComandoFor(Variavel var, String tipo, Expressao expressao, ArrayList<Comando> comandos){
        this.iterador = (ExpressaoAritmetica)var.getExpressao();
        this.tipo = tipo;
        this.fim = ((Double)expressao.ResolveExpressao()).intValue();
        this.blocoComandosFor = comandos;
    }

    @Override
    public void run() {
        if(tipo.equals("to")){
            for(int i = ((Double)iterador.ResolveExpressao()).intValue();i<fim; i++){
                blocoComandosFor.forEach((cmd) -> cmd.run());
                System.out.println("teste to");
                iterador.setResultado(iterador.getResultado()+1);
            }
        }else if(tipo.equals("downto")){
            for(int i = ((Double)iterador.ResolveExpressao()).intValue();i>fim; i--){
                blocoComandosFor.forEach( (cmd) -> cmd.run() );
                System.out.println("teste downto");
                iterador.setResultado(iterador.getResultado()-1);
            }
        }
    }
    
}
