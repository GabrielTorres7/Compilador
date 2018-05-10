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
 */
public class ResolveExpressaoAritmetica implements ResolveExpressao{

    private static final String[] OPERADORES = {"^", "sqrt", "*", "/", "mod", "div", "+", "-"};

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

    private Double executaOperacoes(String expressao) {
        
        for(String operador: OPERADORES){
            while(expressao.contains(operador)){
                int inicioOperador, finalOperador, inicioOperando1, finalOperando1, inicioOperando2, finalOperando2;
                String operando1 = null, operando2 = null;
                String conta;
                Double resultado;
                
                inicioOperador = expressao.indexOf(operador);
                finalOperador = inicioOperador + operador.length();
                
                if(operador.equals("sqrt")){
                   inicioOperando1 = finalOperando1 = inicioOperador;
                   operando1 = "0";
                    //System.out.println("operando1:"+operando1);
                }else{
                    inicioOperando1 = this.getInicioOperando1(expressao, inicioOperador);
                    finalOperando1 = this.getFinalOperando1(expressao, inicioOperador);
                    
                    operando1 = (expressao.substring(inicioOperando1, finalOperando1));
                  /**************************************************************
                     * TESTE PARA VERIFICAR O OPERANDO1
                    System.out.println("inicioOperando1:"+inicioOperando1);
                    System.out.println("finalOperando1:"+finalOperando1);
                    System.out.println("operando1:"+operando1);
                    *************************************************************/
                }
                inicioOperando2 = this.getInicioOperando2(expressao, finalOperador);
                finalOperando2 = this.getFinalOperando2(expressao, finalOperador);
                operando2 = (expressao.substring( inicioOperando2 , finalOperando2));
                /******************************************************************
                 * TESTE PARA VERIFICAR O OPERANDO2
                System.out.println("");
                System.out.println("inicioOperando2:"+inicioOperando2);
                System.out.println("finalOperando2:"+finalOperando2);
                System.out.println("operando2:"+operando2);
                ******************************************************************/
                
                /******************************************************************
                 * TESTE PARA VERIFICAR O OPERADOR
                System.out.println("operador:"+operador);
                ******************************************************************/
                conta = expressao.substring(inicioOperando1, finalOperando2);
                resultado = this.conta(Double.parseDouble(operando1), operador, Double.parseDouble(operando2));
                expressao = expressao.replace(conta,resultado.toString());
            }
        }
        return Double.parseDouble(expressao);
    }
    
     private int getInicioOperando1(String expressao, int inicioOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList(' ', '+', 's', 'm', 'd','-','^','*', '/'));
        Character charPosterior;
            for (int i = 0; i > inicioOperador; i++) {
                charPosterior = expressao.charAt(i+1);
                if (!naoPermitidos.equals(charPosterior)) {// verifica até não achar um operador permitido
                    return i;
                }
            }
        return 0;
    }
    
    private int getInicioOperando2(String expressao, int finalOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList(' ', '+', 's', 'm', 'd','-','^','*', '/'));
        Character atual;
            for (int i = finalOperador ; i < (expressao.length()-1); i++) {
                atual = expressao.charAt(i);
                if (!naoPermitidos.equals(atual)) {
                    return i;
                }
            }
        return expressao.length()-1;
    }
    
    
   private int getFinalOperando1(String expressao, int inicioOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList(' ', '+', 's', 'm', 'd','-','^','*', '/'));
        Character charAnterior;
            for (int i = inicioOperador; i > 1; i--) {  //
                charAnterior = expressao.charAt(i-1);
                if (!naoPermitidos.equals(charAnterior)) {
                    return i;
                }
            }
        return 1;
    }
   
    private int getFinalOperando2(String expressao, int finalOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList(' ', '+', 's', 'm', 'd','-','^','*', '/'));
        Character atual;
            for (int i = expressao.length() ; i < finalOperador + 1; i--) {
                atual = expressao.charAt(i);
                if (!naoPermitidos.equals(atual)) {
                    return i;
                }
            }
        return expressao.length();
    }
    
    public Double conta(Double operando1, String operador, Double operando2) {
        switch(operador){
            case "^":
                return Math.pow(operando1, operando2);
            case "sqrt":
                return Math.sqrt(operando2);
            case "*":
                return operando1*operando2;
            case "/":
                return operando1/operando2;
            case "mod":
                return operando1%operando2;
            case "div":
                return (operando1-(operando1%operando2))/operando2;
            case "+":
                return operando1+operando2;
            case "-":
                return operando1-operando2;
            default:
                return null;
        }
    }
    
}
