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
import KKKKK.ResolveExpressaoAritmetica;
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

        char caractere = '0';
        char caractereAux = '0';
        String palavraAux = "";
        String atribuicao = "";
        String expressao = "";
        String subExpressaoAtribuicao = "";
        int saldoParenteses = 0;
        Variavel variavel;
        ComandoPrint print;
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

        for (i = 0; i < programa.length(); i++) {
            caractere = programa.charAt(i);
            if (caractere != ' ' && caractere != ':' && caractere != '(') {
                palavraAux += caractere;
            } else {
                if (palavras_reservadas.contains(palavraAux)) {
                    if (palavraAux.equals("end")) {
                        //for(Comando comando : comandos) {
                        //  comando.run();
                        //}
                        System.out.println("Programa encerrado");
                        System.exit(0);
                    }

                    if (palavraAux.equals("print")) {
                        if (caractere == '(') {
                            saldoParenteses++;
                            int a = i;
                            a++;
                            caractereAux = programa.charAt(a);
                            while (caractereAux != ')' && saldoParenteses != 0) {
                                expressao += caractereAux;
                                a++;
                                caractereAux = programa.charAt(a);
                                if (caractereAux == '(') {
                                    saldoParenteses++;
                                } else if (caractereAux == ')') {
                                    saldoParenteses--;
                                }
                            }
                        }
                        print = new ComandoPrint(new ExpressaoAritmetica(expressao));
                        comandos.add(print);
                    }

                    if (palavraAux.equals("for")) {

                    }
                    if (palavraAux.equals("while")) {

                    }
                    if (palavraAux.equals("if")) {

                    }
                    if (palavraAux.equals("do")) {

                    }
                    if (palavraAux.equals("do")) {

                    }
                    if (palavraAux.equals("then")) {

                    }

                }

                if (!palavras_reservadas.contains(palavraAux)) {
                    int a = i;
                    String palavraAuxComando = "";

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
                                if (caractere == ' ') {
                                    while(caractere == ' '){
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                    palavraAuxComando = "";
                                } 
                                palavraAuxComando += caractere;
                                expressao += caractere;
                                a++;
                                caractere = programa.charAt(a);
                            }
                            int posicaoComandoTerminoExpressaoAtribuicao = expressao.indexOf(palavraAuxComando);
                            subExpressaoAtribuicao = expressao.substring(0, posicaoComandoTerminoExpressaoAtribuicao);
                            //System.out.println(subExpressaoAtribuicao);
                            
                            variavel = new Variavel(new ExpressaoAritmetica(subExpressaoAtribuicao), palavraAux);
                            i = a; //Pula o contandor para o ultimo caractere lido dentro do fluxo de atribuição.
                        } else {
                            //erro
                        }
                    }
                }
                palavraAux = "";
            }
        }
        ResolveExpressaoAritmetica ab = new ResolveExpressaoAritmetica();
        System.out.println(ab.resolveExpressao("1/4"));
        //System.out.println(palavraAux);
    }

}
