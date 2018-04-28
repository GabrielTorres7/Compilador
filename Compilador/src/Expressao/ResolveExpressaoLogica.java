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
                int inicioOperador, finalOperador, inicioOperando1, finalOperando1, inicioOperando2, finalOperando2;
                String operando1 = null, operando2 = null;
                String conta;
                Boolean resultado;
                
                inicioOperador = expressao.indexOf(operador);
                finalOperador = expressao.indexOf(operador)+ operador.length();
                
                //seta o operando1 dos operadores unarios
                if(operador.equals("not")){
                    inicioOperando1 = finalOperando1 = inicioOperador;
                }else{
                    inicioOperando1 = this.getInicioOperando1(expressao, inicioOperador);
                    finalOperando1 = inicioOperador;
                    
                    operando1 = (expressao.substring(inicioOperando1, finalOperando1));
                }
                
                inicioOperando2 = finalOperador;
                finalOperando2 = this.getFinalOperando2(expressao, inicioOperando2 - 1);
                operando2 = (expressao.substring( inicioOperando2 , finalOperando2));
                
                switch (operador) {
                    case "not":
                        conta = expressao.substring(inicioOperando1, finalOperando2);
                        resultado = !Boolean.parseBoolean(operando2);
                        expressao = expressao.replace(conta, " "+resultado.toString()+" ");
                        break;
                //expressao = expressao.replace(conta, resultado.toString());
                    case "or":
                    case "and":
                        conta= expressao.substring( inicioOperando1, finalOperando2);
                        resultado = this.conta( Boolean.parseBoolean(operando1), operador, Boolean.parseBoolean(operando2) );
                        System.out.println("1:"+Boolean.parseBoolean(operando1));
                        System.out.println("2:"+Boolean.parseBoolean(operando2));
                        expressao = expressao.replace(conta, " "+resultado.toString()+" ");
                        break;
                    case "<=":
                    case ">=":
                    case "<":
                    case ">":
                        conta= expressao.substring( inicioOperando1, finalOperando2); 
                        resultado = this.conta(Double.parseDouble(operando1), operador, Double.parseDouble(operando2));
                        expressao = expressao.replace(conta, " "+resultado.toString()+" ");
                        break;
                    case "=":
                        conta= expressao.substring( inicioOperando1, finalOperando2);
                        resultado = this.conta(operando1, operador, operando2);
                        expressao = expressao.replace(conta, " "+resultado.toString()+" ");
                        break;
                    default:
                        break;
                }
            }
        }
        return Boolean.parseBoolean(expressao);
    }
    
    private int getInicioOperando1(String expressao, int posicaoOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList('>', '=', '<', ' '));
        Character operadorAnterior;
            for (int i = posicaoOperador; i > 1; i--) {  //
                operadorAnterior = expressao.charAt(i - 1);
                if (naoPermitidos.contains(operadorAnterior)) {
                    return i;
                }
            }
        return 0;
    }
    
    private int getFinalOperando2(String expressao, int posicaoOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList('>', '=', '<', ' ', ')'));
        Character operadorPosterior;
            for (int i = posicaoOperador+1 ; i < (expressao.length()-1); i++) {
                operadorPosterior = expressao.charAt(i);
                if (naoPermitidos.contains(operadorPosterior)) {
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
