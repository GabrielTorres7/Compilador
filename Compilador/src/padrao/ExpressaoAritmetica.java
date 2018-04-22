/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

/**
 *
 * @author User
 */
public class ExpressaoAritmetica implements Expressao {
    private String expressao;
    private Double resultado;
    

    public ExpressaoAritmetica() {
    }
    
    public ExpressaoAritmetica(String expressao) {
        this.expressao = expressao;
    }
  
    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
    
    @Override
    public Object resolveExpressao() {
       return resolveExpressao(this.expressao);
    }
    
    public void imprime(){
         System.out.println(resolveExpressao("1+2(3*4)"));
    }

    @Override
    public Object resolveExpressao(String expressao) {
        if (expressao.contains("(")) {
            String aux;
            
            int inicioSubstring = expressao.indexOf("(");
            int finalSubstring = expressao.lastIndexOf(")");
            
            String substring = expressao.substring(inicioSubstring, finalSubstring+1);
            String subExpressao = substring.substring(1,substring.length()-1);
   
            aux = resolveExpressao(subExpressao).toString();
            
            expressao = expressao.replace(substring, aux);
        }
            if (expressao.contains("sqrt(")) {
                String aux;

                int inicioSubstring = expressao.indexOf("sqrt(");
                int finalSubstring = expressao.lastIndexOf(")");

                String substring = expressao.substring(inicioSubstring, finalSubstring+1);
                String subExpressao = substring.substring(1,substring.length()-1);

                aux = resolveExpressao(subExpressao).toString();
                Math.sqrt( Double.parseDouble(aux));

                expressao = expressao.replace(substring, aux);
            }
        
        return this.executaOperacoes(expressao);
    }
    
    public double executaOperacoes(String expressao) {
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
                inicNumero1 = inicioPrimeiroNumero(expressao, posicaoOperandoPotencia);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoPotencia);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = finalSegundoNumero(expressao, posicaoOperandoPotencia);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoPotencia+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(Math.pow(numero1, numero2));
                
                expressao = expressao.replace(expressao.substring((inicNumero1), (finalNumero2)), aux);
                
            }else if(posicaoOperandoRaiz>0) {
                inicNumero1 = inicioPrimeiroNumero(expressao, posicaoOperandoRaiz);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoRaiz);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = finalSegundoNumero(expressao, posicaoOperandoRaiz);
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
                inicNumero1 = inicioPrimeiroNumero(expressao, posicaoOperandoMultiplica);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoMultiplica);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = finalSegundoNumero(expressao, posicaoOperandoMultiplica);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoMultiplica+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 * numero2);
            }else if(posicaoOperandoDivide>0) {
                inicNumero1 = inicioPrimeiroNumero(expressao, posicaoOperandoDivide);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoDivide);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = finalSegundoNumero(expressao, posicaoOperandoDivide);
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
                inicNumero1 = inicioPrimeiroNumero(expressao, posicaoOperandoMais);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoMais);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = finalSegundoNumero(expressao, posicaoOperandoMais);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoMais+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 + numero2);
            }else if(posicaoOperandoMenos>0) {
                inicNumero1 = inicioPrimeiroNumero(expressao, posicaoOperandoMenos);
                numeroAnteriorAoOperador = expressao.substring(inicNumero1, posicaoOperandoMenos);
                numero1 = Double.parseDouble(numeroAnteriorAoOperador);
                
                finalNumero2 = finalSegundoNumero(expressao, posicaoOperandoMenos);
                numeroPosteriorAoOperador = expressao.substring(posicaoOperandoMenos+1, finalNumero2);
                numero2 = Double.parseDouble(numeroPosteriorAoOperador);
                
                aux = Double.toString(numero1 - numero2);
            }
            expressao = expressao.replace(expressao.substring((inicNumero1), (finalNumero2)), aux);
        }
        resultado = Double.parseDouble(expressao);
        
        return resultado;
    }
    
    public int inicioPrimeiroNumero(String expressao, int posicaoOperando) {
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
    
    public int finalSegundoNumero(String expressao, int posicaoOperando) {
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
    
}
