package padrao;

import java.util.ArrayList;
import java.lang.Math;

/**
 *
 * @author Hiago
 */
public class AnalisaExpressao {
        
    private String expressao;
    private String expressaoFinal;
    private ArrayList<String> letras = new ArrayList();
    private ArrayList <String>numeros = new ArrayList();


  
    int i = 0;
    char caractere = '0';
    private Expressao teste1;
    private Expressao teste2;
    public AnalisaExpressao() {
        this.expressao = "";
    }
    public AnalisaExpressao(String expressao) {
        this.expressao = expressao;
        this.analisa(expressao);       
    }
    private int testaSqrt(int i,String exp,String aux){
        if( aux.equals("s") && !Aplicacao.variaveis.containsKey(aux) ){                 
            if(expressao.length()>=5){ //Caso seja menor q a palavra sqrt(
                if( expressao.charAt(i+1)=='q' && expressao.charAt(i+2)=='r' && expressao.charAt(i+3)=='t' && expressao.charAt(i+4)=='(' ){
                    //Sqrt( correto          
                    return 1;
                } else {
                     //Invalido Exception
                    System.out.println("Inválida sqrt1");
                    System.exit(0);
                }
            }else {
                 //Invalido Exception
                    System.out.println("Inválida sqrt2");
                    System.exit(0);
            }
        }
        if( aux.equals("q") && !Aplicacao.variaveis.containsKey(aux) ){                 
            if(expressao.length()>=4){ //Caso seja menor q a palavra sqrt(
                if( expressao.charAt(i+1)=='r' && expressao.charAt(i+2)=='t' && expressao.charAt(i+3)=='(' ){
                    //Sqrt( correto
                    return 1;
                } else {
                     //Invalido Exception
                    System.out.println("Inválida sqrt1");
                    System.exit(0);
                }
            }else {
                 //Invalido Exception
                    System.out.println("Inválida sqrt2");
                    System.exit(0);
            }
        }
        if( aux.equals("r") && !Aplicacao.variaveis.containsKey(aux) ){                 
            if(expressao.length()>=3){ //Caso seja menor q a palavra sqrt(
                if( expressao.charAt(i+1)=='t' && expressao.charAt(i+2)=='(' ){
                    //Sqrt( correto
                    return 1;
                } else {
                     //Invalido Exception
                    System.out.println("Inválida sqrt1");
                    System.exit(0);
                }
            }else {
                 //Invalido Exception
                    System.out.println("Inválida sqrt2");
                    System.exit(0);
            }
        }
        if( aux.equals("t") && !Aplicacao.variaveis.containsKey(aux) ){                 
            if(expressao.length()>=2){ //Caso seja menor q a palavra sqrt(
                if(  expressao.charAt(i+1)=='(' ){
                    //Sqrt( correto
                    return 1;
                } else {
                     //Invalido Exception
                    System.out.println("Inválida sqrt1");
                    System.exit(0);
                }
            }else {
                 //Invalido Exception
                    System.out.println("Inválida sqrt2");
                    System.exit(0);
            }
        }
        
        //Nao e sqrt
        return 0;
    }
    private int testaNot(int i,String exp,String aux){
        if( aux.equals("n") && !Aplicacao.variaveis.containsKey(aux) ){
            if(expressao.length()>=4){ // Caso seja menor q a palavra not(
                if( expressao.charAt(i+1)=='o' && expressao.charAt(i+2)=='t' && expressao.charAt(i+3)=='(' ){
                //Not correto
               return 1;
                } else {
                     //Invalido Exception
                    System.out.println("Inválido not1");
                    System.exit(0);
                }
            }else {
               //Invalido Exception
               System.out.println("Inválido not2");
               System.exit(0);
            }    
        }
    // Nao é not
    return 0;
    }
    private int testaMod(int i,String exp,String aux){
        if( aux.equals("m") && !Aplicacao.variaveis.containsKey(aux) ){
            if(expressao.length()>=4){ // Caso seja menor q a palavra mod(
                if( expressao.charAt(i+1)=='o' && expressao.charAt(i+2)=='d' && expressao.charAt(i+3)=='(' ){
                    return 1;
                } else {
                     //Invalido Exception
                    System.out.println("Inválido mod1");
                    System.exit(0);
                }
            } else{
                   //Invalido Exception
                   System.out.println("Inválido mod2");
                   System.exit(0);
            }
        }         
    //Nao e mod    
    return 0;
    }
    private int testaOr(int i,String exp,String aux){
       if( aux.equals("o") && !Aplicacao.variaveis.containsKey(aux) ){
            if(expressao.length()>=2){ // Caso seja menor q a palavra mod(
                if( expressao.charAt(i+1)=='r' ){
                    return 1;
                } else {
                     //Invalido Exception
                    System.out.println("Inválido or1");
                    System.exit(0);
                }
            } else{
                   //Invalido Exception
                   System.out.println("Inválido or2");
                   System.exit(0);
            }
        } 
      //Nao e or
      return 0;
    }

    private boolean testaSeVariavel(String aux){
        if (letras.contains(aux) &&!Aplicacao.variaveis.containsKey(aux)){
            //Invalido Exception
            System.out.println("Nao e variavel!!1");
            System.out.println(aux);
            System.exit(0);
        }
    return true;
    }
    
    
    public Object analisa (String expressao){
        Variavel a  = new Variavel(teste1,"a");
        Variavel b =  new Variavel(teste2,"b");
        Variavel c =  new Variavel(teste2,"c");
        
        
        // Preenchendo lista com letras de a-z
        letras.add("a"); letras.add("b"); letras.add("c"); letras.add("d"); letras.add("e"); letras.add("f"); letras.add("g");
        letras.add("h"); letras.add("i"); letras.add("j"); letras.add("k"); letras.add("l"); letras.add("m"); letras.add("n");
        letras.add("o"); letras.add("p"); letras.add("q"); letras.add("r"); letras.add("s"); letras.add("t"); letras.add("u");
        letras.add("v"); letras.add("w"); letras.add("x"); letras.add("y"); letras.add("z");
        // Preenchendo lista com algarismos de 0-9
        numeros.add("0"); numeros.add("1"); numeros.add("2"); numeros.add("3"); numeros.add("4");
        numeros.add("5"); numeros.add("6"); numeros.add("7"); numeros.add("8"); numeros.add("9"); 
        
        //TESTE
        System.out.println("Inicial = "+expressao);
        
        for(i=0; i<expressao.length(); i++){
            String aux  = "";
            aux += expressao.charAt(i); //Aux é uma String de somente um char, que no caso o sendo avaliado nomomento
            
            if(i==0) { //Primeiro elemento da exp.
                if(letras.contains(aux) || numeros.contains(aux) || aux.equals("+") || aux.equals("-") || aux.equals("(") ){
                    //Se Nao for Sqrt ou not, testa se é variavel
                  
                    if(testaSqrt(i, expressao, aux)==0 && testaNot(i, expressao, aux) ==0){
                       if( testaSeVariavel(aux)){
                           
                       }
                    }
                }else {
                    //INVALIDO EXCEPTION
                    System.out.println("Expressao INVALIDA (1ºelemento)");
                    System.exit(0);
                }                                
            }else // Fim 1º elemento
            if(letras.contains(aux) || numeros.contains(aux) || aux.equals("+") || aux.equals("-") || aux.equals("*")
                || aux.equals("/") || aux.equals("=") || aux.equals("<") || aux.equals(">") || aux.equals("(") || aux.equals(")") ){
                //Se valor anterior foi variavel                
                if(testaSeVariavel(String.valueOf(expressao.charAt(i-1))) && 
                   testaSqrt(i, expressao, aux)==0 && testaMod(i, expressao, aux)==0   ) {
                    
                    
                 }
              
                if(testaSqrt(i, expressao, aux)==0 && testaNot(i, expressao, aux) ==0){
                    if(testaSeVariavel(aux)){
                    }
                }
                    //mod
                              
            }else{
                //Exception
                System.out.println("Expressao invalida!! ");
                System.exit(0);
            }
    }//for
        //TESTE expfinal
        System.out.println("final = "+expressao);
        return null;
    }    
}


/* Impedimentos Atuais 
-N sei como fazer logica dos parenteses () 
-Quando se tem exemplo: sqrt , 0. MAS DA ERRO NO T
    

*/
