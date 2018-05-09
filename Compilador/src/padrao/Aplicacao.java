/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

/**
 *
 * @author Gabriel
 */
import java.util.HashMap;
import java.util.Map;

public class Aplicacao {

    public static Map<String, Variavel> variaveis = new HashMap<>();

    public static void main(String[] args) throws Exception {
        
        LeArquivo arquivo;
        String programa;
        Compilador compilador;
        
        arquivo = new LeArquivo("programa.tiny"); //Diretorio onde está o arquivo do programa.
        programa = arquivo.getArquivo();
        
        compilador = new Compilador(programa);
        compilador.analisaComandos();
        compilador.executaComandos();
        System.out.println("\nPrograma encerrado");
    }

}
