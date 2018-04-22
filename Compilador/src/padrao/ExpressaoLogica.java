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
    private String expressao;
    private Boolean resultado; 

    public ExpressaoLogica(String expressao) {
        this.expressao = expressao;
    }

    public ExpressaoLogica() {
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }
    
    @Override
    public Object resolveExpressao() {
       return resolveExpressao(this.expressao);
    }

    @Override
    public Object resolveExpressao(String expressao) {
        //método que executará qualquer expressão a ser passada, só pode ter and, or e not além dos ().
        return resultado;
    }
    

}
