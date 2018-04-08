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
    
    private Integer inicio;
    private String tipo;
    private Integer fim;
    private ArrayList<Comando> comandos;
    
    public ComandoFor(Variavel var, String tipo, Expressao expressao, String comandos){
        //this.inicio = var.getValue();
        this.tipo = tipo;
        //this.lenght = expressao.solve();
        //comandos (Separar em comandos atraves de uma funcao)
    }

    @Override
    public void run() {
        if(tipo == "to"){
            for(int i = inicio;i<fim; i++){
                for(Comando cmd: comandos){
                    cmd.run();
                }
            }
        }else if(tipo == "downto"){
            for(int i = inicio;i>fim; i--){
                for(Comando cmd: comandos){
                    cmd.run();
                }
            }
        }
    }
    
}
