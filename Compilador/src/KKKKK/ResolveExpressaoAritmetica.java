/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KKKKK;

import padrao.ErroSintaxe;

/**
 *
 * @author Arthur
 */
public class ResolveExpressaoAritmetica implements ResolveExpressao{

    
    public void ResolveExpressao() {
        
    }

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
        String aux = null;
        String numeroAnteriorAoOperador;
        String numeroPosteriorAoOperador;
        int inicNumero1=0;
        int finalNumero2=0;
        double numero1;
        double numero2; 
        
        while (expressao.contains("^") || expressao.contains("sqrt")) {
            int posicaoOperandoPotencia = expressao.indexOf("^");
            int posicaoOperandoRaiz = expressao.indexOf("sqrt");
            
            if (posicaoOperandoPotencia < posicaoOperandoRaiz && posicaoOperandoPotencia > 0 || posicaoOperandoRaiz == -1) {
                inicNumero1 = getOperando1(expressao, posicaoOperandoPotencia);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoPotencia);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = getOperando2(expressao, posicaoOperandoPotencia);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoPotencia+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(Math.pow(numero1, numero2));
                
                expressao = expressao.replace(expressao.substring((inicNumero1), (finalNumero2)), aux);
                
            }else if(posicaoOperandoRaiz>0) {
                inicNumero1 = getOperando1(expressao, posicaoOperandoRaiz);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoRaiz);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = getOperando2(expressao, posicaoOperandoRaiz);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoRaiz+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 - numero2);
            }
            expressao = expressao.replace(expressao.substring((inicNumero1), (finalNumero2)), aux);
        }
        
        while (expressao.contains("*") || expressao.contains("/")) {
            int posicaoOperandoMultiplica = expressao.indexOf("*");
            int posicaoOperandoDivide = expressao.indexOf("/");
            
            if (posicaoOperandoMultiplica < posicaoOperandoDivide && posicaoOperandoMultiplica > 0 || posicaoOperandoDivide == -1) {
                inicNumero1 = getOperando1(expressao, posicaoOperandoMultiplica);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoMultiplica);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = getOperando2(expressao, posicaoOperandoMultiplica);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoMultiplica+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 * numero2);
            }else if(posicaoOperandoDivide>0) {
                inicNumero1 = getOperando1(expressao, posicaoOperandoDivide);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoDivide);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = getOperando2(expressao, posicaoOperandoDivide);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoDivide+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 / numero2); 
            }
            expressao = expressao.replace(expressao.substring((inicNumero1), (finalNumero2)), aux);
        }
        
        while (expressao.contains("+") || expressao.contains("-")) {
            int posicaoOperandoMais = expressao.indexOf("+");
            int posicaoOperandoMenos = expressao.indexOf("-");
            
            if (posicaoOperandoMais < posicaoOperandoMenos && posicaoOperandoMais > 0 || posicaoOperandoMenos == -1) {
                inicNumero1 = getOperando1(expressao, posicaoOperandoMais);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoMais);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = getOperando2(expressao, posicaoOperandoMais);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoMais+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 + numero2);
            }else if(posicaoOperandoMenos>0) {
                inicNumero1 = getOperando1(expressao, posicaoOperandoMenos);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoMenos);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = getOperando2(expressao, posicaoOperandoMenos);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoMenos+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 - numero2);
            }
            expressao = expressao.replace(expressao.substring((inicNumero1), (finalNumero2)), aux);
        }
        
        return Double.parseDouble(expressao);
    }
    
    public int getOperando1(String expressao, int posicaoOperando) {
        int inicNumero1 = 0;
        int i;
        char operadorAnterior;
       
            for (i = posicaoOperando-1; i > 0; i--) {
                operadorAnterior = expressao.charAt(i);
                if (operadorAnterior=='^' || operadorAnterior=='*' || operadorAnterior=='/' 
                        || operadorAnterior=='+' || operadorAnterior=='-' 
                        || operadorAnterior=='(' || operadorAnterior==')') {
                    inicNumero1 = i+1;
                    break;
                }
            }
        return inicNumero1;
    }
    
    public int getOperando2(String expressao, int posicaoOperando) {
        int finalNumero2;
        int i;
        char operadorPosterior;
        
        finalNumero2=expressao.length();
            for (i = posicaoOperando + 1; i < expressao.length(); i++) {
                operadorPosterior = expressao.charAt(i);
                if (operadorPosterior == '^' || operadorPosterior == '/' || operadorPosterior == '*' 
                        || operadorPosterior == '+' || operadorPosterior == '-' 
                        || operadorPosterior == '(' || operadorPosterior == ')') {
                    finalNumero2 = i;
                    break;
                }
            }
        return finalNumero2;
    }
    
    public Double conta(Double operando1, String operador, Double operando2) throws ErroSintaxe{
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
            case "+":
                return operando1+operando2;
            case "-":
                return operando1-operando2;
            default:
                throw new ErroSintaxe("Sem operador");
        }
    }
    
}
