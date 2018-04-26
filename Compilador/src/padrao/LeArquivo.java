/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Gabriel
 */
public class LeArquivo {
    String programa;
    String diretorioArquivo;
    
    public LeArquivo(String diretorioArquivo){
        this.diretorioArquivo = diretorioArquivo;
        
        try {
            InputStream is = new FileInputStream(diretorioArquivo); //Especificar diretorio do arquivo .tiny a ser lido.
            InputStreamReader isr = new InputStreamReader(is);
            try (BufferedReader br = new BufferedReader(isr)) {
                String linha = br.readLine(); //primeira linha
                programa = linha + " ";

                while (linha != null) {
                    linha = br.readLine();
                    if (linha != null) {
                        programa += linha + " ";
                    }
                }
            } //primeira linha
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }
    
    public String getArquivo(){
        return programa;
    }
    
}
