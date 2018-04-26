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
                int posicaoOperador, inicioOperando1, finalOperando1, inicioOperando2, finalOperando2;
                double operando1 = 0, operando2 = 0;
                String conta;
                
                posicaoOperador = expressao.indexOf(operador);
                
                if(operador.equals("sqrt")){
                    inicioOperando1 = expressao.indexOf(operador);
                    finalOperando1 = posicaoOperador;
                }else{
                    inicioOperando1 = this.getInicioOperando1(expressao, posicaoOperador);
                    finalOperando1 = posicaoOperador;
                    
                    operando1 = Double.parseDouble(expressao.substring(inicioOperando1, finalOperando1));
                }
                
                if(operador.equals("sqrt") || operador.equals("mod") || operador.equals("div")){
                    inicioOperando2 = posicaoOperador + operador.length();
                }else{
                    inicioOperando2 = posicaoOperador + 1;
                }finalOperando2 = this.getFinalOperando2(expressao, inicioOperando2 - 1);
                
                System.out.println(expressao+"Posicao operador: "+posicaoOperador+" I2: "+inicioOperando2+" F2: "+finalOperando2);
                operando2 = Double.parseDouble(expressao.substring( inicioOperando2 , finalOperando2));
                    
                conta= expressao.substring( inicioOperando1, finalOperando2);
                Double resultado = this.conta(operando1, operador, operando2);
                expressao = expressao.replace(conta, resultado.toString());
            }
        }
        return Double.parseDouble(expressao);
    }
    
    private int getInicioOperando1(String expressao, int posicaoOperador) {
        List<Character> numeros = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'));
        Character operadorAnterior;
            for (int i = posicaoOperador; i > 1; i--) {  //5+41+32*31
                operadorAnterior = expressao.charAt(i - 1);
                if (!numeros.contains(operadorAnterior)) {
                    return i;
                }
            }
        return 0;
    }
    
    private int getFinalOperando2(String expressao, int posicaoOperador) {
        List<Character> numeros = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'));
        Character operadorPosterior;
            for (int i = posicaoOperador+1 ; i < (expressao.length()-1); i++) {
                operadorPosterior = expressao.charAt(i);
                if (!numeros.contains(operadorPosterior)) {
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
