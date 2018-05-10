/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padrao;
import Expressao.ExpressaoAritmetica;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class ComandoFor implements Comando{
    
    private final ComandoAtribuicao atribuicao;
    private final Integer valorAtribuicao;
    private final String tipo;
    private final String fim;
    private final ArrayList<Comando> blocoComandosFor;
    AnalisaExpressao analisaExpressao;
    
    public ComandoFor(ComandoAtribuicao atribuicao, String tipo, String expressao, ArrayList<Comando> comandos){
        this.atribuicao = atribuicao;
        this.valorAtribuicao = ((Double)((ExpressaoAritmetica)atribuicao.getExpressao()).getResultado()).intValue();
        this.tipo = tipo;
        this.fim = expressao;
        this.blocoComandosFor = comandos;
        analisaExpressao = new AnalisaExpressao();
    }

    @Override
    public void run() {
        Integer fimAux;
        ExpressaoAritmetica expArit = (ExpressaoAritmetica) analisaExpressao.getResultado(fim);
        fimAux = ((Double)expArit.getResultado()).intValue();
        if(tipo.equals("to")){
            for(Integer iterador=valorAtribuicao; iterador <= fimAux; iterador++){
                Aplicacao.variaveis.get(atribuicao.getNomeVariavel()).getExpressao().setExpressao(iterador.toString());
                blocoComandosFor.forEach((cmd) -> {
                    cmd.run();
                });
            }
        }else if(tipo.equals("downto")){
            for(Integer iterador=valorAtribuicao; iterador >= fimAux; iterador--){
                Aplicacao.variaveis.get(atribuicao.getNomeVariavel()).getExpressao().setExpressao(iterador.toString());

                blocoComandosFor.forEach((cmd) -> {
                    cmd.run();
                });
            }
        }
    }
    
}
