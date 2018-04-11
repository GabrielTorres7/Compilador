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
public class ExpressaoAritmetica implements Expressao{
    
    private Double operador1; 
    private Double operador2;
    private String operando;
    private Double resultado; 
    
    ExpressaoAritmetica(Double operador1, String operando, Double operador2){
        this.operador1 = operador1;
        this.operador2 = operador2;
        this.operando = operando;
    }
    ExpressaoAritmetica(Double operador1, String operando){
        this.operador1 = operador1;
        this.operando = operando;
    }

    public Double getOperador1() {
        return operador1;
    }

    public void setOperador1(Double operador1) {
        this.operador1 = operador1;
    }

    public Double getOperador2() {
        return operador2;
    }

    public void setOperador2(Double operador2) {
        this.operador2 = operador2;
    }

    public String getOperando() {
        return operando;
    }

    public void setOperando(String operando) {
        this.operando = operando;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
    

    @Override
    public Object ResolveExpressao(Object operador1, String Operando, Object operador2) {
        this.operador1 = (Double)operador1;
        this.operador2 = (Double)operador2;
        this.operando = operando;
        
        if(operando.equals("+")){
            this.resultado = this.operador1 + this.operador2;
        }
        if(operando.equals("-")){
            this.resultado = this.operador1 - this.operador2;
        }
        if(operando.equals("*")){
            this.resultado = this.operador1 * this.operador2;
        }
        if(operando.equals("/")){
            this.resultado = this.operador1 / this.operador2;
        }
        if(operando.equals("mod")){
            this.resultado = this.operador1 % this.operador2;
        }
        return this.resultado;
    }

    @Override
    public Object ResolveExpressao(Object operador1, String operando) {
        this.operador1 = (Double)operador1;
        this.operando = operando;
        
        if(operando.equals("sqrt")){
            this.resultado = Math.sqrt(this.operador1);
        }
        return this.resultado;
    }
}
