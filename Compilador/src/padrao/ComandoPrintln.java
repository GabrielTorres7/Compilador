/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

/**
 *
 * @author Arthur
 */
public class ComandoPrintln implements Comando{
    
    private String imprime;
    
    public ComandoPrintln(String imprime){
        this.imprime = imprime;
        
    }

    @Override
    public void run() {
        System.out.println(getImprime());
    }

    /**
     * @return the imprime
     */
    public String getImprime() {
        return imprime;
    }

    /**
     * @param imprime the imprime to set
     */
    public void setImprime(String imprime) {
        this.imprime = imprime;
    }
    
}
