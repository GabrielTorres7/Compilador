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
 * @author Allan
 */
public class ResolveExpressaoLogica implements ResolveExpressao{
    
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
                }else{//seta o operando1 dos operadores binarios
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
                switch (operador) {
                    case "not":
                        conta = expressao.substring(inicioOperando1, finalOperando2);
                        resultado = !Boolean.parseBoolean(operando2);
                        expressao = expressao.replace(conta,resultado.toString());
                        break;
                    case "or":
                    case "and":
                        conta= expressao.substring( inicioOperando1, finalOperando2);
                        //System.out.println("BB op1:"+Boolean.parseBoolean(operando1));
                        //System.out.println("BB op2:"+Boolean.parseBoolean(operando2));
                        resultado = this.conta( Boolean.parseBoolean(operando1), operador, Boolean.parseBoolean(operando2) );
                        //System.out.println("operando1:"+Boolean.parseBoolean(operando1));
                        //System.out.println("operando2:"+Boolean.parseBoolean(operando2));
                        expressao = expressao.replace(conta,resultado.toString());
                        break;
                    case "<=":
                    case ">=":
                    case "<":
                    case ">":
                        conta= expressao.substring( inicioOperando1, finalOperando2); 
                        //System.out.println("DD op1:"+Double.parseDouble(operando1));
                        //System.out.println("DD op2:"+Double.parseDouble(operando2));
                        resultado = this.conta(Double.parseDouble(operando1), operador, Double.parseDouble(operando2));
                        //System.out.println("operando1:"+Double.parseDouble(operando1));
                        //System.out.println("operando2:"+Double.parseDouble(operando2));
                        expressao = expressao.replace(conta,resultado.toString());
                        break;
                    case "=":
                    case "<>":
                        conta= expressao.substring( inicioOperando1, finalOperando2);
                        //System.out.println("");
                        //System.out.println("conta:"+conta);
                        //System.out.println("operando1:"+operando1);
                        //System.out.println("operando2:"+operando2);
                        resultado = this.conta(operando1, operador, operando2);
                        expressao = expressao.replace(conta,resultado.toString());
                        break;
                    default:
                        break;
                }
            }
        }
        return Boolean.valueOf(expressao);
    }
    
    private int getInicioOperando1(String expressao, int inicioOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList('<','>','=',')', ' '));
        Character charPosterior;
            for (int i = 0; i > inicioOperador; i++) {
                charPosterior = expressao.charAt(i+1);
                if (!naoPermitidos.contains(charPosterior)) {// verifica até não achar um operador permitido
                    return i;
                }
            }
        return 0;
    }
    
     private int getInicioOperando2(String expressao, int finalOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList('<','>','=',')', ' '));
        Character atual;
            for (int i = finalOperador ; i < (expressao.length()-1); i++) {
                atual = expressao.charAt(i);
                if (!naoPermitidos.contains(atual)) {
                    return i;
                }
            }
        return expressao.length()-1;
    }
    
    
    private int getFinalOperando1(String expressao, int inicioOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList('<','>','=',')', ' '));
        Character charAnterior;
            for (int i = inicioOperador; i > 1; i--) {  //
                charAnterior = expressao.charAt(i-1);
                if (!naoPermitidos.contains(charAnterior)) {
                    return i;
                }
            }
        return 1;
    }
   
    private int getFinalOperando2(String expressao, int finalOperador) {
        List<Character> naoPermitidos = new ArrayList<>(Arrays.asList('<','>','=',')', ' '));
        Character atual;
            for (int i = expressao.length() ; i < finalOperador + 1; i--) {
                atual = expressao.charAt(i);
                if (!naoPermitidos.contains(atual)) {
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
            case "<>":
                return !operando1.equals(operando2);
            case "=":
                return operando1.equals(operando2);
            default:
                return null;
        }
    }
    
}
