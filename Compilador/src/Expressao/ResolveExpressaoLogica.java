/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expressao;

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
                String operando1 = null, operando2 = null;
                String conta;
                Boolean resultado;
                
                posicaoOperador = expressao.indexOf(operador);
                
                //seta o operando1 dos operadores unarios
                if(operador.equals("not")){
                    inicioOperando1 = expressao.indexOf(operador);
                    finalOperando1 = posicaoOperador;
                }
                //seta o operadorando1 dos operadores binarios
                else{
                    inicioOperando1 = this.getInicioOperando1(expressao, posicaoOperador);
                    finalOperando1 = posicaoOperador;
                    
                    operando1 = (expressao.substring(inicioOperando1, finalOperando1));
                }
                
                //seta o 2 operando dos operadores que tem mais de 1 caracter
                if(operador.equals("or") || operador.equals("and") || operador.equals("not") || operador.equals("<=") || operador.equals(">=") || operador.equals("<>")){
                    inicioOperando2 = posicaoOperador + operador.length();
                }else{
                    inicioOperando2 = posicaoOperador + 1;
                }
                finalOperando2 = this.getFinalOperando2(expressao, inicioOperando2 - 1);
                operando2 = (expressao.substring( inicioOperando2 , finalOperando2));
                
                if(operador.equals("or") || operador.equals("and") || operador.equals("not")){
                    conta= expressao.substring( inicioOperando1, finalOperando2);
                    resultado = this.conta( Boolean.parseBoolean(operando1), operador, Boolean.parseBoolean(operando2) );
                    System.out.println("1:"+Boolean.parseBoolean(operando1));
                    System.out.println("2:"+Boolean.parseBoolean(operando2));
                    expressao = expressao.replace(conta, resultado.toString()); 
                 
                }else if( (operador.equals("<=") || operador.equals(">=") || operador.equals("<") || operador.equals(">")) ){
                    conta= expressao.substring( inicioOperando1, finalOperando2);
                    resultado = this.conta(Double.parseDouble(operando1), operador, Double.parseDouble(operando2));
                    expressao = expressao.replace(conta, resultado.toString()); 
                    
                }else if(operador.equals("=")){
                    conta= expressao.substring( inicioOperando1, finalOperando2);
                    resultado = this.conta(operando1, operador, operando2);
                    expressao = expressao.replace(conta, resultado.toString()); 
                }
                //expressao = expressao.replace(conta, resultado.toString()); 
            }
        }
        return Boolean.parseBoolean(expressao);
    }
    
    private int getInicioOperando1(String expressao, int posicaoOperador) {
        List<Character> numerosELetras = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'));
        Character operadorAnterior;
            for (int i = posicaoOperador; i > 1; i--) {  //
                operadorAnterior = expressao.charAt(i - 1);
                if (!numerosELetras.contains(operadorAnterior)) {
                    return i;
                }
            }
        return 0;
    }
    
    private int getFinalOperando2(String expressao, int posicaoOperador) {
        List<Character> numerosELetras = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'));
        Character operadorPosterior;
            for (int i = posicaoOperador+1 ; i < (expressao.length()-1); i++) {
                operadorPosterior = expressao.charAt(i);
                if (!numerosELetras.contains(operadorPosterior)) {
                    return i;
                }
            }
        return expressao.length();
    }
    
    public Boolean conta(Boolean operando1, String operador, Boolean operando2) {
        switch(operador){
            case "not":
                return !operando2;
            case "or":
                return operando1 || operando2;
            case "and":
                return operando1 && operando2;  
            default:
                return null;
        }
    }
    
    public Boolean conta(Double operando1, String operador, Double operando2) {
        switch(operador){
            case ">":
                return operando1 > operando2;
            case "<":
                return operando1 < operando2;
            case "<=":
                return operando1 <= operando2;
            case ">=":
                return operando1 >= operando2;     
            default:
                return null;
        }
    }
    
    public Boolean conta(String operando1, String operador, String operando2) {
        switch(operador){
            case "=":
                return operando1.equals(operando2);
            default:
                return null;
        }
    }
    
}
