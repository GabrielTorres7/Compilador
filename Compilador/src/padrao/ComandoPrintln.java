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

    @Override
    public void run() {
        System.out.println();
    }
    
}
