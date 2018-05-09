package padrao;
import Expressao.Expressao;
import Expressao.ExpressaoAritmetica;
import Expressao.ExpressaoLogica;
import Expressao.ResolveExpressaoAritmetica;
import Expressao.ResolveExpressaoLogica;
import java.util.ArrayList;
import java.lang.Math;

/**
 *
 * @author Hiago
 */
public class AnalisaExpressao {
        
    private String expressao= "";
    private String expressaoFinal = "";
    private ArrayList<String> letras = new ArrayList();
    private ArrayList <String>numeros = new ArrayList();
    private ArrayList <String>mulop = new ArrayList();
    private ArrayList <String>relop = new ArrayList();
    private ArrayList <String>unop = new ArrayList();
    private ArrayList <String>reservadas = new ArrayList();


  
    int i = 0;
    char caractere = '0';
    private Expressao teste1;
    private Expressao teste2;
    public AnalisaExpressao() {
        this.expressao = "";
    }
    public AnalisaExpressao(String expressao) {
        this.expressao = expressao;      
    }
    private boolean testaMulop(String exp){
        if(mulop.contains(exp)){
            return true;
        }else{
            return false;
        }
    }
    
    private void testaParenteses(String exp){
        int abertos = 0;
        int fechados = 0;
        for (int j = 0; j <exp.length(); j++) {
            if(String.valueOf(exp.charAt(j)).equals("(")){
                abertos++;
                if(String.valueOf(exp.charAt(j+1)).equals(")"))
                    throw new RuntimeException("Expressao invalida! (parenteses)");
            }
            if(String.valueOf(exp.charAt(j)).equals(")")){
                if(abertos>fechados)
                    fechados++;
                else{
                    throw new RuntimeException("Expressao invalida! (parenteses)");
                }
            }
        }
        if(abertos!=fechados)
            throw new RuntimeException("Expressao invalida! (parenteses)");
        }

   
    public Expressao getResultado (String expressao){
        //VARIAVEIS PARA TESTE
        /*ComandoAtribuicao a  = new ComandoAtribuicao("a",new ExpressaoAritmetica("50"));
        ComandoAtribuicao b =  new ComandoAtribuicao("b",new ExpressaoAritmetica("98"));
        ComandoAtribuicao feijao =  new ComandoAtribuicao("feijao",new ExpressaoAritmetica("30"));*/
        //APAGAR
      //  System.out.println("Inicial="+expressao);
        
        // Preenchendo lista com letras de a-z
        letras.add("a"); letras.add("b"); letras.add("c"); letras.add("d"); letras.add("e"); letras.add("f"); letras.add("g");
        letras.add("h"); letras.add("i"); letras.add("j"); letras.add("k"); letras.add("l"); letras.add("m"); letras.add("n");
        letras.add("o"); letras.add("p"); letras.add("q"); letras.add("r"); letras.add("s"); letras.add("t"); letras.add("u");
        letras.add("v"); letras.add("w"); letras.add("x"); letras.add("y"); letras.add("z");
        // Preenchendo lista com algarismos de 0-9
        numeros.add("0"); numeros.add("1"); numeros.add("2"); numeros.add("3"); numeros.add("4");
        numeros.add("5"); numeros.add("6"); numeros.add("7"); numeros.add("8"); numeros.add("9"); 
        //Preenchendo Mulop
        mulop.add("*"); mulop.add("/"); mulop.add(" mod "); mulop.add("div"); mulop.add("and");
        //Preenchendo relop
        relop.add("=");relop.add("<");relop.add("<=");relop.add(">");relop.add(">=");relop.add("<>");
        //preenchendo reservadas
        reservadas.add(" mod "); reservadas.add(" div "); reservadas.add("and"); reservadas.add("or"); 
        reservadas.add("true"); reservadas.add("false"); reservadas.add("mod"); reservadas.add("div");
        //TESTE
//        System.out.println("Inicial = "+expressao);
        testaParenteses(expressao);
        if(expressao.charAt(0)=='"' && expressao.charAt(expressao.length()-1)=='"'){
            //APAGAR
      //      System.out.println("FinalString="+expressao);
            return ((Expressao) new ExpressaoLogica(expressao));
        }
        for(i=0; i<expressao.length(); i++){
            String aux = String.valueOf(expressao.charAt(i)); //Aux é uma String de somente um char, que no caso o sendo avaliado nomomento
            //Testa se é variavel
            if(letras.contains(aux)){
              
               int contAux;
                String palavraAux = "";
                String caractere ;
                for(contAux = i; contAux < expressao.length(); contAux++){
                    caractere = Character.toString(expressao.charAt(contAux));
                   if(!caractere.equals("(")&&
                  //      !numeros.contains(caractere)&&
                        !caractere.equals("+")&&!caractere.equals("-")&&
                        !mulop.contains(caractere)&&
                        !relop.contains(caractere)&&
                        !caractere.equals(")") &&
                        !caractere.equals(".")   )
                   {
                        palavraAux+=caractere;
                   } else{
                   
                            if(!palavraAux.equals("")){
             //                   System.out.println("palavra1="+palavraAux);
                                if(Aplicacao.variaveis.containsKey(palavraAux)){
                                    //VALOR VARIAVEL
                                    expressao = expressao.replace(palavraAux, String.valueOf(Aplicacao.variaveis.get(palavraAux).getValor()));
                                    break;
                                }// se nao é variavel E nao é reservada
                //                System.out.println("auxiliar="+palavraAux);
                                if (reservadas.contains(palavraAux)) {
                //                    System.out.println("palavr="+palavraAux);
                                    break;
                                }
                                if(!Aplicacao.variaveis.containsKey(palavraAux) && !reservadas.contains(palavraAux)){
                               //     System.out.println("alavraux="+palavraAux);
                                    throw new RuntimeException("Expressao invalida! Palavra não existe!");
                                }
                                
                            }
                           palavraAux=  "";
                         }
                   if(caractere.equals("("))
                       break;
                }
                if(!palavraAux.equals("")){
                //    System.out.println("palvra2="+palavraAux);
                    if(Aplicacao.variaveis.containsKey(palavraAux)){
                        //VALOR VARIAVEL
                        expressao = expressao.replace(palavraAux, String.valueOf(Aplicacao.variaveis.get(palavraAux).getValor()));
                       
                    }
                    // se e reservada
                     if(reservadas.contains(palavraAux)){
                    //   System.out.println("contem "+palavraAux);
                        break;
                    }                
                    //Testa se nao e variavel E nao e reservada
                    if(!Aplicacao.variaveis.containsKey(palavraAux) && !reservadas.contains(palavraAux)){
                        System.out.println("palv="+palavraAux);
                        throw new RuntimeException("Expressao invalida! Palavra n existe!");
                    }
               }
            }     
            if( i==0 ) { //Primeiro elemento da exp.
                 int contAux;
                 String palavraAux = "";
                 String caractere ;
                 caractere = String.valueOf(expressao.charAt(i));
                 if(caractere.equals("/")||caractere.equals("*")||caractere.equals(",")){
                        throw new RuntimeException("Expressao invalida!");
                    }
                 /*for( contAux = i; contAux<expressao.length();contAux++) {
                 caractere = String.valueOf(expressao.charAt(contAux));
                 // System.out.println("caractere="+caractere);
                 
                 if( !caractere.equals("(")&&
                 !numeros.contains(caractere)&&
                 !caractere.equals("+")&&
                 !caractere.equals("-")&&
                 !relop.contains(caractere)&&
                 !caractere.equals(".") &&
                 !caractere.equals("/") &&
                 !caractere.equals("*")  &&
                 !caractere.equals(")")  )
                 {
                 if(caractere.equals(',') ){
                 throw new RuntimeException("Expressao invalida!");
                 }
                 palavraAux+=String.valueOf(expressao.charAt(contAux));
                 }
                 }
                 if(reservadas.contains(palavraAux)){
                 break;
                 }
                 if(contAux==expressao.length())
                 continue;
                 if(Aplicacao.variaveis.containsKey(palavraAux)){
                 //VALOR VARIAVEL
                 expressao = expressao.replace(palavraAux, String.valueOf(Aplicacao.variaveis.get(palavraAux).getValor()));
                 break;
                 }
                 if(mulop.contains(palavraAux)||palavraAux.equals("") ||
                 (!Aplicacao.variaveis.containsKey(palavraAux) && !reservadas.contains(palavraAux) ))
                 throw new RuntimeException("Expressao invalida!");
                 i=contAux;*/
            }        
            if(i<expressao.length()){
                if(expressao.charAt(i)=='('){
                    if(i!=0){
                        if(!mulop.contains(String.valueOf(expressao.charAt(i-1))) && !relop.contains(String.valueOf(expressao.charAt(i-1))) &&
                            expressao.charAt(i-1)!='+'&&expressao.charAt(i-1)!='-'  ){
               //             System.out.println("charat="+expressao.charAt(i-1)    );
                            throw new RuntimeException("Expressao invalida! (Sem operador antes de parenteses)");
                        }
                    }
                    int contAux;
                     String palavraAux = "";
                     String caractere ;
                     String caracAux = "";
                     for( contAux = i; contAux<expressao.length();contAux++) {
                         caractere = String.valueOf(expressao.charAt(contAux));
                         if(contAux!=0){
                            caracAux = String.valueOf(expressao.charAt(contAux-1));
                         }
                        if(caracAux.equals("(")&&caractere.equals("/")||caracAux.equals("(")&&caractere.equals("*")){
                            throw new RuntimeException("Expressao invalida! (Caractere invalido após parenteses)");
                        }
                        if( !caractere.equals("(")&&
                            !numeros.contains(caractere)&&
                            !caractere.equals("+")&&
                            !caractere.equals("-")&&
                            !mulop.contains(caractere)&& 
                            !relop.contains(caractere)&& 
                            !caractere.equals(")")&&
                            !caractere.equals(".")){
                            if(caractere.equals(',') ){
                                throw new RuntimeException("Expressao invalida!");
                            }
                            palavraAux+=String.valueOf(expressao.charAt(contAux));
                        }                      
                     } // a > b+5
                }
            }
    }//for
        //Verifica se a expressao contem algum elemento de relop(logico)
            int contem = 0;
            //contador auxiliar
            int k = 0;
            for(int i=0;i<expressao.length();i++){
                if(relop.contains(String.valueOf(expressao.charAt(i)))){
          //          System.out.println("contem");
                    contem++;
                    k=i-1;
                }
            }

            if(contem!=0){
                // Se operador logico possuir dois caracteres, ex >=
                if(relop.contains(String.valueOf(expressao.charAt(k)))){
                      //Antes do operador
                    if(expressao.substring(0,k).contains("+") || expressao.substring(0,k).contains("-") || 
                       expressao.substring(0,k).contains("/") || expressao.substring(0,k).contains("*") ||
                       expressao.substring(0,k).contains(" mod ") )
                    {
                            String substring = expressao.substring(0,k);
                            String resultado= String.valueOf(new ResolveExpressaoAritmetica().resolveExpressao(substring));
                            expressao = expressao.replace(substring,resultado);
                    }
                    //Percorrer novamente para achar o operador, uma vez que o resultado alterou a string inicial
                    for(int i=0;i<expressao.length();i++){
                        if(relop.contains(String.valueOf(expressao.charAt(i)))){
                            contem++;
                            k=i;
                        }
                    }
                    //Depois do operador
                    if(expressao.substring(k+1,expressao.length()).contains("+") || expressao.substring(k+1,expressao.length()).contains("-") || 
                       expressao.substring(k+1,expressao.length()).contains("/") || expressao.substring(k+1,expressao.length()).contains("*") ||
                       expressao.substring(k+1,expressao.length()).contains(" mod ") )
                    {
                        String substring = expressao.substring(k+1,expressao.length());
                        String resultado= String.valueOf(new ResolveExpressaoAritmetica().resolveExpressao(substring));
                        expressao = expressao.replace(substring,resultado);
                    }
                }else{
                    //Antes do operador
                    if(expressao.substring(0,k+1).contains("+") || expressao.substring(0,k+1).contains("-") || 
                       expressao.substring(0,k+1).contains("/") || expressao.substring(0,k+1).contains("*") ||
                       expressao.substring(0,k+1).contains(" mod ") )
                    {
                        String substring = expressao.substring(0,k+1);
                        String resultado= String.valueOf(new ResolveExpressaoAritmetica().resolveExpressao(substring));
                        expressao = expressao.replace(substring,resultado);
                    }
                    //Percorrer novamente para achar o operador, uma vez que o resultado alterou a string inicial
                    for(int i=0;i<expressao.length();i++){
                        if(relop.contains(String.valueOf(expressao.charAt(i)))){
                            contem++;
                            k=i;
                        }
                    }
                     //Depois do operador
                     if(expressao.substring(k+1,expressao.length()).contains("+") || expressao.substring(k+1,expressao.length()).contains("-") || 
                       expressao.substring(k+1,expressao.length()).contains("/") || expressao.substring(k+1,expressao.length()).contains("*") ||
                       expressao.substring(k+1,expressao.length()).contains(" mod ") )
                    {
                       String substring = expressao.substring(k+1,expressao.length());
                       String resultado= String.valueOf(new ResolveExpressaoAritmetica().resolveExpressao(substring));
                       expressao = expressao.replace(substring,resultado);
                    }
                }
                //Resolve e retorna
                //APAGAR
           //     System.out.println("FinalLogic="+expressao);
           //     System.out.println("ResultLogic="+new ExpressaoLogica(expressao).getResultado());
                return (Expressao)new ExpressaoLogica(expressao);

            }
            //Nao contem operador logico , é aritmetica
           // System.out.println("final="+expressao);
                //APAGAR
             //   System.out.println("FinalArit="+expressao);
            //    System.out.println("ResultArit="+new ExpressaoAritmetica(expressao).getResultado());
                return (Expressao)new ExpressaoAritmetica(expressao);
    }    
}

   