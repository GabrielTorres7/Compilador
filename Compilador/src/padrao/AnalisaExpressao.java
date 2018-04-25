package padrao;

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
        Variavel variav =  new Variavel(teste2,"variav");
        
        
        // Preenchendo lista com letras de a-z
        letras.add("a"); letras.add("b"); letras.add("c"); letras.add("d"); letras.add("e"); letras.add("f"); letras.add("g");
        letras.add("h"); letras.add("i"); letras.add("j"); letras.add("k"); letras.add("l"); letras.add("m"); letras.add("n");
        letras.add("o"); letras.add("p"); letras.add("q"); letras.add("r"); letras.add("s"); letras.add("t"); letras.add("u");
        letras.add("v"); letras.add("w"); letras.add("x"); letras.add("y"); letras.add("z");
        // Preenchendo lista com algarismos de 0-9
        numeros.add("0"); numeros.add("1"); numeros.add("2"); numeros.add("3"); numeros.add("4");
        numeros.add("5"); numeros.add("6"); numeros.add("7"); numeros.add("8"); numeros.add("9"); 
        //Preenchendo Mulop
        mulop.add("*"); mulop.add("/"); mulop.add("mod"); mulop.add("div"); mulop.add("and");
        //Preenchendo relop
        relop.add("=");relop.add("<");relop.add("<=");relop.add(">");relop.add(">=");relop.add("<>");
        
        //TESTE
        System.out.println("Inicial = "+expressao);
        testaParenteses(expressao);
        
        for(i=0; i<expressao.length(); i++){
            String aux = String.valueOf(expressao.charAt(i)); //Aux é uma String de somente um char, que no caso o sendo avaliado nomomento
            //Testa se é variavel
            if(letras.contains(aux)){
                int contAux = i;
                String palavraAux = "";
                String caractere = palavraAux;
                do{
                    caractere = String.valueOf(expressao.charAt(contAux));
                    palavraAux+=caractere;
                    contAux++;
                  //  System.out.println("cont="+contAux+"  carac="+caractere);
                    //Enquanto n for "(" ou ")", n for numero, n for + ou - , n for mulop ou relop, e n for maior
                } while (!caractere.equals("(")&&!numeros.contains(caractere)&&!caractere.equals("+")&&!caractere.equals("-")&&!mulop.contains(caractere)
                 && !relop.contains(caractere) && !caractere.equals(")") && contAux<expressao.length());
                System.out.println("teste="+palavraAux.charAt(2));
                System.out.println("palvra="+palavraAux);
                
                i=contAux;
                if(Aplicacao.variaveis.containsKey(palavraAux)){
                
                }
            }
         //   System.out.println("assdasdadsasda");;
            if( i==0 ) { //Primeiro elemento da exp.  
                 int contAux = i;
                 String palavraAux = "";
                 String caractere = palavraAux;
                 do {                          
                    if(caractere.equals(","))
                        throw new RuntimeException("Expressao invalida!");
                    palavraAux+=String.valueOf(expressao.charAt(contAux));
                    contAux++;
                    caractere = String.valueOf(expressao.charAt(contAux));                       
                 } while(!caractere.equals("(")&&!numeros.contains(caractere)&&!caractere.equals("+")&&!caractere.equals("-")&&!mulop.contains(caractere)
                         && !relop.contains(caractere));
                 if(mulop.contains(palavraAux))
                    throw new RuntimeException("Expressao invalida!");
                   i=contAux;
                  
            }
            if(expressao.charAt(i-1)=='(' || expressao.charAt(i-1)==')' ){
                 int contAux = i;
                 String palavraAux = "";
                 String caractere = palavraAux;
                 do {                          
                   if(caractere.equals(","))
                        throw new RuntimeException("Expressao invalida!");
                    palavraAux+=String.valueOf(expressao.charAt(contAux));
                    contAux++;
                    caractere = String.valueOf(expressao.charAt(contAux)); 
                 
                 } while(!caractere.equals("(")&&!numeros.contains(caractere)&&!caractere.equals("+")&&!caractere.equals("-")&&!mulop.contains(caractere)
                         && !relop.contains(caractere) && !caractere.equals(")"));
                 if(mulop.contains(palavraAux))
                    throw new RuntimeException("Expressao invalida!");
                 i=contAux;
            }
            
                
                
                
                    
            
    }//for
        //TESTE expfinal
        System.out.println("final = "+expressao);
        return null;
    }    


}

   
