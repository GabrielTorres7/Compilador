/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KKKKK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import padrao.ErroSintaxe;

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
                int inicioOperando1 = this.getInicioOperando1(expressao, expressao.indexOf(operador));
                int finalOperando2 = this.getFinalOperando2(expressao, expressao.indexOf(operador));
                Double operando1 = Double.parseDouble(expressao.substring(inicioOperando1, expressao.indexOf(operador)));
                Double operando2 = Double.parseDouble(expressao.substring(expressao.indexOf(operador), finalOperando2));
                Double resultado = this.conta(operando1, operador, operando2);
                expressao = expressao.replace( expressao.substring( inicioOperando1, finalOperando2), resultado.toString() );
                
            }
        }
        return Double.parseDouble(expressao);
    }
    
    private int getInicioOperando1(String expressao, int posicaoOperador) {
        List<Character> numeros = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        Character operadorAnterior;
            for (int i = posicaoOperador -1; i > 0; i--) {
                operadorAnterior = expressao.charAt(i);
                if (!numeros.contains(operadorAnterior)) {
                    return i;
                }
            }
        return 0;
    }
    
    private int getFinalOperando2(String expressao, int posicaoOperador) {
        List<Character> numeros = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        Character operadorPosterior;
            for (int i = posicaoOperador+1 ; i < expressao.length(); i++) {
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
                return Math.sqrt(operando1);
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
