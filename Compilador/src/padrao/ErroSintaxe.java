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
public class ErroSintaxe extends Exception {

   
    public ErroSintaxe() {
    }

    
    public ErroSintaxe(String msg) {
        super(msg);
    }

    ErroSintaxe(String erro_de_sintaxe, Throwable e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
