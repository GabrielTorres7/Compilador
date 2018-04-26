/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KKKKK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Arthur
 * @param <T>
 */
public class ResolveExpressaoLogica <T> implements ResolveExpressao{
    
    private static final String[] OPERADORES = {"or", "and", "not", "=", "<", ">", "<=", ">=", "<>"};

    @Override
    public Object resolveExpressao(String expressao) {
        String aux = this.executaParenteses(expressao);
        return this.executaOperacoes(aux);
        
    }
    
    private String executaParenteses(String expressao){
        
        if (expressao.contains("(")) {
            int inicioSubstring = expressao.indexOf("(");
            int finalSubstring = expressao.lastIndexOf(")");
            String subExpressao = expressao.substring(inicioSubstring + 1, finalSubstring);
            expressao = expressao.replace("(" + subExpressao + ")", resolveExpressao(subExpressao).toString());
        }
        return expressao;
    }

    private Boolean executaOperacoes(String expressao) {
        
        for(String operador: OPERADORES){
            //executa enquanto na expressão estiver operadores
            while(expressao.contains(operador)){
                int posicaoOperador, inicioOperando1, finalOperando1, inicioOperando2, finalOperando2;
                T operando1 = null, operando2 = null;
                String conta;
                
                posicaoOperador = expressao.indexOf(operador);
                
                //seta o operando dos operadores unarios
                if(operador.equals("not")){
                    inicioOperando1 = expressao.indexOf(operador);
                    finalOperando1 = posicaoOperador;
                }else{
                    inicioOperando1 = this.getInicioOperando1(expressao, posicaoOperador);
                    finalOperando1 = posicaoOperador;
                    
                    operando1 = (T) (expressao.substring(inicioOperando1, finalOperando1));
                }
                
                //seta o 2 operando dos operadores que tem mais de 1 caracter
                if(operador.equals("or") || operador.equals("and") || operador.equals("not") || operador.equals("<=") || operador.equals(">=") || operador.equals("<>")){
                    inicioOperando2 = posicaoOperador + operador.length();
                }else{
                    inicioOperando2 = posicaoOperador + 1;
                }finalOperando2 = this.getFinalOperando2(expressao, inicioOperando2 - 1);
                
                System.out.println(expressao+"Posicao operador: "+posicaoOperador+" I2: "+inicioOperando2+" F2: "+finalOperando2);
                operando2 = (T) (expressao.substring( inicioOperando2 , finalOperando2));
                    
                conta= expressao.substring( inicioOperando1, finalOperando2);
                Boolean resultado = this.conta(operando1, operador, operando2);
                expressao = expressao.replace(conta, resultado.toString());
            }
        }
        return Boolean.parseBoolean(expressao);
    }
    
    private int getInicioOperando1(String expressao, int posicaoOperador) {
                List<String> numerosELetras = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." , "true", "false" ));
        Character operadorAnterior;
            for (int i = posicaoOperador; i > 1; i--) {  //5+41+32*31
                operadorAnterior = expressao.charAt(i - 1);
                if (!numerosELetras.contains(operadorAnterior)) {
                    return i;
                }
            }
        return 0;
    }
    
    private int getFinalOperando2(String expressao, int posicaoOperador) {
        List<Character> numerosELetras = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' , 't', 'f'));
        Character operadorPosterior;
            for (int i = posicaoOperador+1 ; i < (expressao.length()-1); i++) {
                operadorPosterior = expressao.charAt(i);
                if (!numerosELetras.contains(operadorPosterior)) {
                    return i;
                }
            }
        return expressao.length();
    }
    
    public Boolean conta(T operando1, String operador, T operando2) {
        /*switch(operador){
            case "not":
                return !operando1;
            case ">":
                return operando1 > operando2;
            case  "<=":
                return operando2 =< operando1;
            case  ">=":
                return operando1 =>operando2;
            case "=":
                return operando2 == operando1;
            case "and":
                return operando1 && operando2;
            case "or":
                return operando1 || operando2;
                
            default:
                return null;
        }*/ return null;
    }
    
}