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
public class ExpressaoLogica implements Expressao{
    
    private Boolean operador1; 
    private Boolean operador2;
    private String operando;
    private Boolean resultado; 
    
    ExpressaoLogica(Boolean operador1, String operando, Boolean operador2){
        this.operador1 = operador1;
        this.operador2 = operador2;
        this.operando = operando;
    }
    ExpressaoLogica(Boolean operador1, String operando){
        this.operador1 = operador1;
        this.operando = operando;
    }

    public Boolean getOperador1() {
        return operador1;
    }

    public void setOperador1(Boolean operador1) {
        this.operador1 = operador1;
    }

    public Boolean getOperador2() {
        return operador2;
    }

    public void setOperador2(Boolean operador2) {
        this.operador2 = operador2;
    }

    public String getOperando() {
        return operando;
    }

    public void setOperando(String operando) {
        this.operando = operando;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public Object ResolveExpressao(Object operador1, String Operando, Object operador2) {
        this.operador1 = (Boolean)operador1;
        this.operador1 = (Boolean)operador2;
        this.operando = operando;
        
        if(operando.equals("and")){
            this.resultado = this.operador1 && this.operador2;
        }
        if(operando.equals("or")){
            this.resultado = this.operador1 || this.operador2;
        }
        return this.resultado;
    }

    @Override
    public Object ResolveExpressao(Object operador1, String operando) {
        this.operador1 = (Boolean)operador1;
        this.operando = operando;
        
        if(operando.equals("not")){
            this.resultado = !this.operador1 ;
        }
        return this.resultado;
    }

}
