/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import Expressao.ExpressaoAritmetica;
import Expressao.ExpressaoLogica;
import java.util.ArrayList;
import static padrao.Aplicacao.variaveis;

/**
 *
 * @author Gabriel
 */
public class Compilador {
    String programa;
    ArrayList<String> palavras_reservadas = new ArrayList();
    ArrayList<Comando> comandos = new ArrayList();

    
    public Compilador(String programa){ //O compilador é construido com um programa a ser compilado em cefetiny.
        this.programa = programa;
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
    }
    
    public void analisaComandos() {
        
        Compilador compilador;
        Compilador compiladorBlocoElse;
        AnalisaExpressao analisaExpressao = new AnalisaExpressao();;
        ComandoAtribuicao comandoAtribuicao;
        ComandoPrint print;
        ComandoPrintln println;
        ComandoReadInt readInt;
        ComandoWhile comandoWhile;
        ComandoIf comandoIf;
        ComandoFor comandoFor;
        char caractere = '0';
        char caractereAux = '0';
        String palavraAux = "";
        String atribuicao = "";
        String atribuindo = "";
        String expressao = "";
        String subExpressaoAtribuicao = "";
        String subPrograma = "";
        String tipo = "";
        String subProgramaIf = "";
        String subProgramaElse = "";
        String subPalavraAux = "";
        int saldoParenteses = 0;
        int saldoWhile = 0;
        int saldoIf = 0;
        int saldoFor = 0;
        int i, a, inicioComando = 0;
        boolean temElse;
        
        for (i = 0; i < programa.length(); i++) {
            caractere = programa.charAt(i);
            if (caractere != ' ' && caractere != ':' && caractere != '(') {
                palavraAux += caractere;
            } else {
                if (palavras_reservadas.contains(palavraAux)) {
                    expressao = "";

                    if (palavraAux.equals("end")) {
                        return;
                    }

                    if (palavraAux.equals("print")) {
                        saldoParenteses = 0;
                        a = i;
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }

                            do {
                                expressao += caractere;
                                if (caractere == '(') {
                                    saldoParenteses++;
                                } else if (caractere == ')') {
                                    saldoParenteses--;
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                            } while (!(caractere == ')' && saldoParenteses == 0));
                        }
                        a++;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                                inicioComando = a - 1;
                            }
                        }
                        print = new ComandoPrint(expressao);
                        comandos.add(print);
                        i = inicioComando;
                    }

                    if (palavraAux.equals("readint")) {
                        a = i;
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractereAux = programa.charAt(a);
                            if (caractereAux == ' ') {
                                while (caractereAux == ' ') {
                                    a++;
                                    caractereAux = programa.charAt(a);
                                }
                            }
                            while (caractereAux != ')') {
                                expressao += caractereAux;
                                a++;
                                caractereAux = programa.charAt(a);

                                if (caractereAux == ' ') {
                                    while (caractereAux == ' ') {
                                        a++;
                                        caractereAux = programa.charAt(a);
                                    }
                                }
                            }
                        }
                        a++;
                        caractereAux = programa.charAt(a);
                        if (caractereAux == ' ') {
                            while (caractereAux == ' ') {
                                a++;
                                caractereAux = programa.charAt(a);
                                inicioComando = a - 1;
                            }
                        }
                        if (variaveis.containsKey(expressao)) {
                            readInt = new ComandoReadInt(expressao);
                            comandos.add(readInt);
                            i = inicioComando;
                        } else {
                            throw new VariavelNaoExisteException("Variavel no readInt não existe.");
                        }
                        if (expressao.contains(" ")) {
                            throw new ConteudoReadIntIncorretoException("Conteudo do readInt deve ser uma variavel existente.");
                        }
                    }
                    if (palavraAux.equals("while")) {
                        subPrograma = "";
                        subPalavraAux = "";
                        saldoParenteses = 0;
                        saldoWhile++;
                        a = i;
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }

                            do {
                                expressao += caractere;
                                if (caractere == '(') {
                                    saldoParenteses++;
                                } else if (caractere == ')') {
                                    saldoParenteses--;
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                            } while (!(caractere == ')' && saldoParenteses == 0));
                        }else {
                            throw new SintaxeWhileIncorretaException("Sintaxe do comando while incorreta.");
                        }
                        a++;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        a++;
                        caractereAux = programa.charAt(a);
                        if("do".equals(""+caractere+caractereAux)){
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }else{
                             throw new SintaxeWhileIncorretaException("Sintaxe do comando while incorreta.");   
                            }
                            while (saldoWhile != 0){
                                if (caractere != ' ') {
                                    subPalavraAux += caractere;

                                }else{
                                    if(subPalavraAux.equals("endwhile")){
                                        saldoWhile--;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(subPalavraAux.equals("while")){
                                        saldoWhile++;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(!subPalavraAux.equals("endwhile") && !subPalavraAux.endsWith("while")){
                                        subPrograma += subPalavraAux+" ";
                                    }
                                    subPalavraAux = "";
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if(a == programa.length()-1){
                                    throw new SintaxeWhileIncorretaException("Sintaxe do comando while incorreta. Endwhile não encontrado.");
                                }
                            }
                        inicioComando = a-1;
                            
                        }else{
                            throw new SintaxeWhileIncorretaException("Sintaxe do comando while incorreta.");
                        }
                        compilador = new Compilador(subPrograma);
                        compilador.analisaComandos();
                        comandoWhile = new ComandoWhile(expressao, compilador.getComandos());
                        comandos.add(comandoWhile);
                        i = inicioComando;
                    }
                    
                    if (palavraAux.equals("endwhile")) {
                        return;
                    }
                    
                    if (palavraAux.equals("println")) {
                        println = new ComandoPrintln();
                        comandos.add(println);
                        a = i;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                                inicioComando = a - 1;
                            }
                        }
                        i = inicioComando;
                    }
                    
                    if (palavraAux.equals("if")) {
                        subPrograma = "";
                        subProgramaIf = "";
                        subProgramaElse = "";
                        subPalavraAux = "";
                        saldoParenteses = 0;
                        saldoIf++;
                        temElse = false;
                        
                        a = i;
                        String then = "";
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if (caractere == '(') {
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }

                            do {
                                expressao += caractere;
                                if (caractere == '(') {
                                    saldoParenteses++;
                                } else if (caractere == ')') {
                                    saldoParenteses--;
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                            } while (!(caractere == ')' && saldoParenteses == 0));
                        }else {
                            throw new SintaxeIfIncorretaException("Sintaxe do comando if incorreta.");
                        }
                        a++;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        then += caractere;
                        for(int b=0; b<3; b++){
                            a++;
                            caractere = programa.charAt(a);
                            then += caractere;
                        }
                        if("then".equals(then)){
                            a++;
                            caractere = programa.charAt(a);
                            if (caractere == ' ') {
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }else{
                             throw new SintaxeIfIncorretaException("Sintaxe do comando if incorreta."); 
                            }
                            while (saldoIf != 0){
                                if (caractere != ' ') {
                                    subPalavraAux += caractere;
   
                                }else{
                                    if(subPalavraAux.equals("endif")){
                                        saldoIf--;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(subPalavraAux.equals("if")){
                                        saldoIf++;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(!subPalavraAux.equals("endif") && !subPalavraAux.equals("if") && !subPalavraAux.equals("else")){
                                        subPrograma += subPalavraAux+" ";
                                    }else if(subPalavraAux.equals("else")){
                                        subPrograma += subPalavraAux+" ";
                                        subProgramaIf = subPrograma;
                                        temElse = true;
                                        subPrograma = "";
                                        subPalavraAux = "";
                                        a++;
                                        caractere = programa.charAt(a);
                                        break;
                                    }
                                    subPalavraAux = "";
                                }
                                a++;
                                caractere = programa.charAt(a);
                            }
                           
                            
                            if(temElse){
                                while (saldoIf != 0){
                                    if (caractere != ' ') {
                                        subPalavraAux += caractere;
                                    }else{
                                        if(subPalavraAux.equals("endif")){
                                            saldoIf--;
                                            subPrograma += subPalavraAux+" ";
                                        }else if(subPalavraAux.equals("if")){
                                            saldoIf++;
                                            subPrograma += subPalavraAux+" ";
                                        }else if(!subPalavraAux.equals("endif") && !subPalavraAux.equals("if")){
                                            subPrograma += subPalavraAux+" ";
                                        }
                                        subPalavraAux = "";
                                    }
                                    a++;
                                    caractere = programa.charAt(a);
                                    if(a == programa.length()-1){
                                        throw new SintaxeIfIncorretaException("Sintaxe do comando if incorreta. Endif não encontrado.");
                                    }
                                }
                                subProgramaElse = subPrograma;
                            }else{
                                subProgramaIf = subPrograma;
                            }
                            inicioComando = a-1;
                        }else{
                            throw new SintaxeIfIncorretaException("Sintaxe do comando if incorreta.");
                        }
                        compilador = new Compilador(subProgramaIf);
                        compilador.analisaComandos();
                        compiladorBlocoElse = new Compilador(subProgramaElse);
                        compiladorBlocoElse.analisaComandos();
                        comandoIf = new ComandoIf(expressao, compilador.getComandos(), temElse, compiladorBlocoElse.getComandos());
                        comandos.add(comandoIf);
                        i = inicioComando;
                    }
                    
                    if (palavraAux.equals("endif")) {
                        if(saldoIf == 0){
                            return;
                        }else{
                         throw new SintaxeIfIncorretaException("Sintaxe do comando if incorreta.");   
                        }
                    }
                    
                     if (palavraAux.equals("endfor")) {
                        if(saldoFor == 0){
                            return;
                        }else{
                         throw new SintaxeForIncorretaException("Sintaxe do comando for incorreta.");   
                        }
                    }
                    
                    if (palavraAux.equals("else")) {
                        return;
                    }
                    
                    if (palavraAux.equals("do")) {
                        throw new CaracterSemSemanticaException("Do sem semantica no codigo.");
                    }
                    
                    if (palavraAux.equals("then")) {
                        throw new CaracterSemSemanticaException("Then sem semantica no codigo.");
                    }
                    
                    if (palavraAux.equals("for")) {
                        subPrograma = "";
                        tipo = "";
                        atribuindo = "";
                        saldoFor++;
                        String variavel = "";
                        palavraAux = "";
                        subPalavraAux = "";
                        
                        a = i;
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        
                        while(caractere != ' ' && caractere != ':'){
                            variavel += caractere;
                            a++;
                            caractere = programa.charAt(a);
                        }                        
                        if (caractere == ' ') {
                            while (caractere == ' ') {
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
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                }
                                while(caractere != ' '){
                                    atribuindo += caractere;
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }
                        }
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        if(caractere == 't'){
                            tipo += caractere;
                            a++;
                            caractere = programa.charAt(a);
                            tipo += caractere;
                            if(tipo.equals("to")){
                                a++;
                                caractere = programa.charAt(a); 
                            }else{
                                throw new SintaxeForIncorretaException("Sintaxe do comando for incorreta.");
                            }
                        }else if(caractere == 'd'){
                            while(caractere != ' '){
                                tipo += caractere;
                                a++;
                                caractere = programa.charAt(a);
                            }
                            if(!tipo.equals("downto")){
                                throw new SintaxeForIncorretaException("Sintaxe do comando for incorreta.");
                            }   
                        }else{
                            throw new SintaxeForIncorretaException("Sintaxe do comando for incorreta.");
                        }
                        
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }else{
                            throw new SintaxeForIncorretaException("Sintaxe do comando for incorreta.");
                        }
                        
                        while (!palavraAux.equals("do")) {
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);
                                    }
                                    palavraAux = "";
                                    inicioComando = a - 1;
                                }
                                palavraAux += caractere;
                                expressao += caractere;
                                a++;
                                caractere = programa.charAt(a);
                        }
                        int posicaoComandoTerminoExpressaoAtribuicao = expressao.indexOf(palavraAux);
                        expressao = expressao.substring(0, posicaoComandoTerminoExpressaoAtribuicao);

                        a++;
                        caractere = programa.charAt(a);
                        if (caractere == ' ') {
                            while (caractere == ' ') {
                                a++;
                                caractere = programa.charAt(a);
                            }
                        }
                        
                            while (saldoFor != 0){
                                if (caractere != ' ') {
                                    subPalavraAux += caractere;

                                }else{
                                    if(subPalavraAux.equals("endfor")){
                                        saldoFor--;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(subPalavraAux.equals("for")){
                                        saldoFor++;
                                        subPrograma += subPalavraAux+" ";
                                    }else if(!subPalavraAux.equals("endfor") && !subPalavraAux.endsWith("for")){
                                        subPrograma += subPalavraAux+" ";
                                    }
                                    subPalavraAux = "";
                                }
                                a++;
                                caractere = programa.charAt(a);
                                if(a == programa.length()-1){
                                        throw new SintaxeForIncorretaException("Sintaxe do comando for incorreta. Endfor não encontrado.");
                                }
                            }
                        inicioComando = a-1;
                            
                        
                        compilador = new Compilador(subPrograma);
                        compilador.analisaComandos();
                        comandoAtribuicao = new ComandoAtribuicao(variavel, atribuindo);
                        comandoFor = new ComandoFor(comandoAtribuicao, tipo, (ExpressaoAritmetica)analisaExpressao.getResultado(expressao), compilador.getComandos());
                        comandos.add(comandoFor);
                        i = inicioComando;
                    }

                }

                if (!palavras_reservadas.contains(palavraAux)) {
                    a = i;
                    String palavraAuxComando = "";
                    expressao = "";

                    if (caractere == ' ') {
                        while (caractere == ' ') {
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
                                while (caractere == ' ') {
                                    a++;
                                    caractere = programa.charAt(a);
                                }
                            }
                            while (!palavras_reservadas.contains(palavraAuxComando)) {
                                if (caractere == ' ' || caractere == ':') {
                                    while (caractere == ' ') {
                                        a++;
                                        caractere = programa.charAt(a);

                                    }
                                    palavraAuxComando = "";
                                    inicioComando = a - 1;
                                }
                                palavraAuxComando += caractere;
                                expressao += caractere;
                                a++;
                                caractere = programa.charAt(a);
                            }
                            if (palavraAuxComando.equals(":=")) {
                                String palavraAuxComando2 = "";
                                a = a - 3;
                                caractere = programa.charAt(a);
                                if (caractere == ' ') {
                                    while (caractere == ' ') {
                                        a--;
                                        caractere = programa.charAt(a);
                                    }
                                }
                                while (caractere != ' ') {
                                    a--;
                                    caractere = programa.charAt(a);
                                    inicioComando = a;
                                }
                                a = inicioComando + 1;
                                caractere = programa.charAt(a);
                                while (caractere != ':') {
                                    palavraAuxComando2 += caractere;
                                    a++;
                                    caractere = programa.charAt(a);
                                    if (caractere == ' ') {
                                        while (caractere == ' ') {
                                            a++;
                                            caractere = programa.charAt(a);
                                        }
                                    }
                                }
                                palavraAuxComando = palavraAuxComando2 + palavraAuxComando;
                            }
                            int posicaoComandoTerminoExpressaoAtribuicao = expressao.indexOf(palavraAuxComando);
                            subExpressaoAtribuicao = expressao.substring(0, posicaoComandoTerminoExpressaoAtribuicao);

                            if (!variaveis.containsKey(palavraAux)) {
                                comandoAtribuicao = new ComandoAtribuicao(palavraAux, subExpressaoAtribuicao );
                                comandos.add(comandoAtribuicao);
                            } else if (variaveis.containsKey(palavraAux)) {
                                variaveis.remove(palavraAux);
                                comandoAtribuicao = new ComandoAtribuicao(palavraAux, subExpressaoAtribuicao);
                                comandos.add(comandoAtribuicao);
                            }
                            i = inicioComando; //Pula o contandor para o ultimo caractere lido dentro do fluxo de atribuição.
                        } else {
                            throw new CaracterSemSemanticaException("Caracter sem sentido no programa.");
                        }
                    }else {
                        if(!palavraAux.equals(""))
                            throw new CaracterSemSemanticaException("Caracter sem sentido no programa.");
                        }
                }
                palavraAux = "";
            }
            if(i == programa.length()-1){
                    throw new CaracterSemSemanticaException("Programa não foi finalizado."); 
                }
        }
    }
    
    public void executaComandos(){
        for (Comando comando : comandos) {
            comando.run();
        }
    }
    
    public ArrayList getComandos(){
        return comandos;
    }
    
    public String getPrograma(){
        return programa;
    }
}
