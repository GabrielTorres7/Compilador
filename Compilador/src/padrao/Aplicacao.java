/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;

/**
 *
 * @author Gabriel
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Aplicacao {

    public static void main(String[] args) {
        String programa = null;

        try {
            InputStream is = new FileInputStream("programa.tiny"); //Especificar diretorio do arquivo .tiny a ser lido.
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linha = br.readLine(); //primeira linha
            programa = linha + "\n";

            while (linha != null) {
                linha = br.readLine();
                if (linha != null) {
                    programa += linha + "\n";
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

    }
}
