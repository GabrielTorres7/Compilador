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
import KKKKK.*;

public class Aplicacao {

    public static Map<String, Variavel> variaveis = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String programa = null;

        ArrayList<Comando> comandos = new ArrayList();
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
        palavras_reservadas.add(":=");

        char caractere = '0';
        char caractereAux = '0';
        String palavraAux = "";
        String atribuicao = "";
        String expressao = "";
        String subExpressaoAtribuicao = "";
        int saldoParenteses = 0;
        Variavel variavel;
        ComandoPrint print;
        ComandoPrintln println;
        ComandoReadInt readInt;
        int i, a, inicioComando = 0;

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

        for (i = 0; i < programa.length(); i++) {
            caractere = programa.charAt(i);
            if (caractere != ' ' && caractere != ':' && caractere != '(') {
                palavraAux += caractere;
            } else {
                if (palavras_reservadas.contains(palavraAux)) {
                    expressao = "";
                    
                    if (palavraAux.equals("end")) {
                        for(Comando comando : comandos) {
                          comando.run();
                        }
                        System.out.println("Programa encerrado");
                        System.exit(0);
                    }

                    if (palavraAux.equals("print")) {
                        saldoParenteses=0;
                        a = i;
                        if(caractere == ' '){
                            while(caractere == ' '){
                            a++;
                            caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractere = programa.charAt(a);
                            if(caractere == ' '){
                            while(caractere == ' '){
                                a++;
                                caractere = programa.charAt(a);
                                }
                            }
                            
                            do{
                                expressao += caractere;
                                if (caractere == '(') {
                                    saldoParenteses++;
                                }else if (caractere == ')') {
                                    saldoParenteses--;
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if(caractere == ' '){
                                    while(caractere == ' '){
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                            }while (!(caractere == ')' && saldoParenteses == 0)); 
                        }
                        a++;
                        caractere = programa.charAt(a);
                        if(caractere == ' '){
                            while(caractere == ' '){
                                a++;
                                caractere = programa.charAt(a);
                                inicioComando = a-1;
                            }
                        }
                        print = new ComandoPrint(new ExpressaoAritmetica(expressao));
                        comandos.add(print);
                        i = inicioComando;
                    }

                    if (palavraAux.equals("readint")) {
                        a = i;
                        if(caractere == ' '){
                            while(caractere == ' '){
                            a++;
                            caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractereAux = programa.charAt(a);
                            if(caractereAux == ' '){
                                while(caractereAux == ' '){
                                    a++;
                                    caractereAux = programa.charAt(a);
                                }
                            }
                            while (caractereAux != ')') {
                                expressao += caractereAux;
                                a++;
                                caractereAux = programa.charAt(a);
                                
                                if(caractereAux == ' '){
                                    while(caractereAux == ' '){
                                        a++;
                                        caractereAux = programa.charAt(a);
                                    }
                                }
                            }
                        }
                        a++;
                        caractereAux = programa.charAt(a);
                        if(caractereAux == ' '){
                            while(caractereAux == ' '){
                                a++;
                                caractereAux = programa.charAt(a);
                                inicioComando = a-1;
                            }
                        }
                        if(variaveis.containsKey(expressao)){
                            readInt = new ComandoReadInt(expressao);
                            comandos.add(readInt);
                            i = inicioComando;
                        }else{
                            //Erro: variavel nao existe.
                        }
                        if(expressao.contains(" ")){
                            //Erro: conteudo do readInt deve ser uma variavel.
                        }
                    }
                    if (palavraAux.equals("while")) {

                    }
                    if (palavraAux.equals("if")) {

                    }
                    if (palavraAux.equals("println")) {
                        println = new ComandoPrintln();
                        comandos.add(println);
                        a = i;
                        caractere = programa.charAt(a);
                        if(caractere == ' '){
                             while(caractere == ' '){
                                a++;
                                caractere = programa.charAt(a);
                                inicioComando = a-1;
                            }
                        }
                        i = inicioComando;
                    }
                    if (palavraAux.equals("do")) {

                    }
                    if (palavraAux.equals("then")) {

                    }

                }

                if (!palavras_reservadas.contains(palavraAux)) {
                    a = i;
                    String palavraAuxComando = "";
                    expressao = "";

                    if (caractere == ' ') {
                        while(caractere == ' '){
                            a++;
                            caractere = programa.charAt(a);
                        }
                    }  
                    if (caractere == ':') {
                        a++;
                        atribuicao = Character.toString(caractere) + programa.charAt(a);
                        if (atribuicao.equals(":=")) {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while(caractere == ' '){
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            } 
                            while (!palavras_reservadas.contains(palavraAuxComando)) {
                                if (caractere == ' ' || caractere == ':') {
                                    while(caractere == ' '){
                                        a++;
                                        caractere = programa.charAt(a);
                                        
                                    }
                                    palavraAuxComando = "";
                                    inicioComando = a-1;
                                }
                                palavraAuxComando += caractere;
                                expressao += caractere;
                                a++;
                                caractere = programa.charAt(a);
                            }
                            if(palavraAuxComando.equals(":=")){
                                    String palavraAuxComando2 = "";
                                    a = a-3;
                                    caractere = programa.charAt(a);
                                    if (caractere == ' ') {
                                        while(caractere == ' '){
                                            a--;
                                            caractere = programa.charAt(a);
                                        }
                                    }
                                    while(caractere != ' '){
                                        a--;
                                        caractere = programa.charAt(a);
                                        inicioComando = a;
                                    }
                                    a = inicioComando+1;
                                    caractere = programa.charAt(a);
                                    while(caractere != ':'){
                                        palavraAuxComando2 += caractere;
                                        a++;
                                        caractere = programa.charAt(a);
                                        if(caractere == ' '){
                                            while(caractere == ' '){
                                                a++;
                                                caractere = programa.charAt(a);
                                            }
                                        }
                                    }
                                    palavraAuxComando = palavraAuxComando2 + palavraAuxComando;
                            }
                            int posicaoComandoTerminoExpressaoAtribuicao = expressao.indexOf(palavraAuxComando);
                            subExpressaoAtribuicao = expressao.substring(0, posicaoComandoTerminoExpressaoAtribuicao);
                            
                            if(!variaveis.containsKey(palavraAux)){
                                variavel = new Variavel(new ExpressaoAritmetica(subExpressaoAtribuicao), palavraAux);
                            }else if(variaveis.containsKey(palavraAux)){
                                variaveis.remove(palavraAux);
                                variavel = new Variavel(new ExpressaoAritmetica(subExpressaoAtribuicao), palavraAux);
                            }
                            i = inicioComando; //Pula o contandor para o ultimo caractere lido dentro do fluxo de atribuição.
                        } else {
                            //Erro: caractere(s) perdido(s) no programa!
                        }
                    }                    
                }
                palavraAux = "";
            }
        }
    }

}
