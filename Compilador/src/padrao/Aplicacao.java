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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Aplicacao {

    /**
     *
     */
    public static Map<String, Variavel> variaveis = new HashMap<>();
    
    public static void main(String[] args) {
        String programa = null;
        
        int a=0;  
        int n=0;
        
        ArrayList<String> palavras_reservadas = new ArrayList();
        ArrayList<String> comandos = new ArrayList();        
        
        palavras_reservadas.add("print");
        palavras_reservadas.add("println");
        palavras_reservadas.add("readint");
        palavras_reservadas.add("if");
        palavras_reservadas.add("while");
        palavras_reservadas.add("for");
        palavras_reservadas.add("then");
        palavras_reservadas.add("else");
        palavras_reservadas.add("endif");
        palavras_reservadas.add("do");
        palavras_reservadas.add("endwhile");
        palavras_reservadas.add("to");
        palavras_reservadas.add("downto");
        palavras_reservadas.add("endfor");
        palavras_reservadas.add("sqrt");

        try {
            InputStream is = new FileInputStream("programa.txt"); //Especificar diretorio do arquivo .tiny a ser lido.
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linha = br.readLine(); //primeira linha
            programa = linha + "\n";

            while (linha != null) {
                linha = br.readLine();
                if (linha != null) {
                    programa += linha + "\n";
                }
            }
            br.close();
            
            String[] partes = programa.split("\\s+");
           
            // essa parte divide todos os comandos do programa.txt e coloca na arraylist comandos pode testar alterando o programa.txt, 
            // se vc executar o projeto desse jeito vai imprimir exatamente os comandos do programa.txt lido
            for(int i=0; i < partes.length; i++){
                 for(a = palavras_reservadas.size(); n<a; n++){                      
                    if(partes[i].equals(palavras_reservadas.get(n))){                        
                    comandos.add(partes[i]);                        
                    }
                }
            n=0;
            }            
            n = 0;
            for(a = comandos.size(); n<a; n++){ 
               System.out.println(comandos.get(n));                   
            }             
            // aqui termina minha linda parte ass: Pedro     
            
            
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }
}
