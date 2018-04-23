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
    
    
    public static Map<String, Variavel> variaveis = new HashMap<>();
    
    public static void main(String[] args) throws Exception {
        String programa = null;   
        
        ArrayList<String> comandos = new ArrayList(); 
        // antes de vc apagar essa array list torres, deixa de ser burro e olha a documentação dos métodos.otario.
        ArrayList<String> palavras_reservadas = new ArrayList();       
        
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
        palavras_reservadas.add("end");
        
        String print = "";
        char caractere = '0';
        String palavraAux = "";
        String atribuicao = "";
        String expressao = "";
        Variavel variavel;
        int i;
        
        try {
            InputStream is = new FileInputStream("programa.txt"); //Especificar diretorio do arquivo .tiny a ser lido.
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            String linha = br.readLine(); //primeira linha
            programa = linha + " ";

            while (linha != null) {
                linha = br.readLine();
                if (linha != null) {
                    programa += linha + " ";
                }
            }
            br.close();
            } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
            }

            for(i=0; i<programa.length(); i++){
                caractere = programa.charAt(i);
                if(caractere != ' ' && caractere != ':'){
                    palavraAux += caractere;
                }else{
                    if(palavras_reservadas.contains(palavraAux)){
                        if(palavraAux.equals("end")){
                            System.out.println("Programa encerrado");
                            System.exit(0);
                        }
                        
                        // aqui onde as palavras vao ser encontradas e os objetos instanciados
                        
                        if(palavraAux.equals("print")){
                            
                        }
                        if(palavraAux.equals("for")){
                            
                        }
                        if(palavraAux.equals("while")){
                            
                        }
                        if(palavraAux.equals("if")){
                            
                        }
                        if(palavraAux.equals("do")){
                            
                        }
                        if(palavraAux.equals("do")){
                            
                        }
                        if(palavraAux.equals("then")){
                            
                            caractere = programa.charAt(i+1);
                            try {
                               if(caractere != '('){
                                   throw new ErroSintaxe("( expected after println");                                    
                                }
                                while(caractere != ')'){
                                print += caractere;
                                }
                                /*ComandoPrintln p1 = new ComandoPrintln(print);
                                p1.run();*/
                            }
                            
                            catch(ErroSintaxe e){
                                System.out.println(e);
                            }                                                                                                                                               
                        }
                           
                    }
                    
                    if(!palavras_reservadas.contains(palavraAux)){
                        int a = i;
                        if(caractere == ' '){
                            
                        }
                        if(caractere == ':'){
                            a++;
                            atribuicao = Character.toString(caractere)+programa.charAt(a);
                            if(atribuicao.equals(":=")){
                                a++;
                                caractere = programa.charAt(a);
                                while(caractere != ' ' && caractere != ';'){
                                    expressao += caractere;
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }else{
                                //erro
                            }
                        }
                    }
                    palavraAux += " ";
                }
            }
            System.out.println(palavraAux);
    }
            
}

