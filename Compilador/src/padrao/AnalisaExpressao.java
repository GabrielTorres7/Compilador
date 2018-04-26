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

   
    public Object analisa (String expressao){
        Variavel arroz  = new Variavel(teste1,"arroz");
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
        //preenchendo reservadas
        reservadas.add("mod"); reservadas.add("div"); reservadas.add("and"); reservadas.add("or"); reservadas.add("mod");
        
        //TESTE
        System.out.println("Inicial = "+expressao);
        testaParenteses(expressao);
        
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
                        !numeros.contains(caractere)&&
                        !caractere.equals("+")&&!caractere.equals("-")&&
                        !mulop.contains(caractere)&&
                        !relop.contains(caractere)&&
                        !caractere.equals(")"))
                   {
                        palavraAux+=caractere;
                   } else{
                            if(!palavraAux.equals("")){
                                System.out.println("palavra1="+palavraAux);
                                if(Aplicacao.variaveis.containsKey(palavraAux)){
                                    //VALOR VARIAVEL
                                    expressao = expressao.replace(palavraAux, "valor1");
                                }// se nao é variavel E nao é reservada
                                if(!Aplicacao.variaveis.containsKey(palavraAux) && !reservadas.contains(palavraAux)){   
                                    throw new RuntimeException("Expressao invalida! Palavra n existe!");
                                }
                            }
                           palavraAux=  "";
                         }
                   if(caractere.equals("("))
                       break;
                }
                i = contAux;
                
                if(!palavraAux.equals("")){
                    System.out.println("palvra2="+palavraAux);
                    if(Aplicacao.variaveis.containsKey(palavraAux)){
                        //VALOR VARIAVEL
                        expressao = expressao.replace(palavraAux, "valor2");
                    }
                    // se e reservada
                     if(reservadas.contains(palavraAux)){
                        System.out.println("contem"+palavraAux);
                    }
                    //Testa se nao e variavel E nao e reservada
                    if(!Aplicacao.variaveis.containsKey(palavraAux) && !reservadas.contains(palavraAux)){   
                        throw new RuntimeException("Expressao invalida! Palavra n existe!");
                    }
                }
            }     System.out.println("i="+i);
            if( i==0 ) { //Primeiro elemento da exp.
                 int contAux;
                 String palavraAux = "";
                 String caractere ;
                 for( contAux = i; contAux<expressao.length();contAux++) {
                     caractere = String.valueOf(expressao.charAt(contAux));
                     System.out.println("caractere="+caractere);
                    if(caractere.equals("/")||caractere.equals("*")||caractere.equals(",")){
                        throw new RuntimeException("Expressao invalida!");
                    }
                    if( !caractere.equals("(")&&
                        !numeros.contains(caractere)&&
                        !caractere.equals("+")&&
                        !caractere.equals("-")&&
                        !relop.contains(caractere)){
                        if(caractere.equals(',') ){
                            throw new RuntimeException("Expressao invalida!");
                        }
                        palavraAux+=String.valueOf(expressao.charAt(contAux));
                    }                      
                 }System.out.println("palavraAux="+palavraAux); 
                 if(Aplicacao.variaveis.containsKey(palavraAux)){
                     //VALOR VARIAVEL
                    expressao = expressao.replace(palavraAux, "valor1");
                 }
                 if(mulop.contains(palavraAux)||palavraAux.equals("") ||
                    (!Aplicacao.variaveis.containsKey(palavraAux) && !reservadas.contains(palavraAux) ))
                    throw new RuntimeException("Expressao invalida!");
                   i=contAux;
            }        
            if(i<expressao.length()){
                if(expressao.charAt(i)=='('){
                    int contAux;
                     String palavraAux = "";
                     String caractere ;
                     for( contAux = i; contAux<expressao.length();contAux++) {
                         caractere = String.valueOf(expressao.charAt(contAux));
                        if(caractere.equals("/")||caractere.equals("*")){
                            throw new RuntimeException("Expressao invalida!");
                        }
                        if( !caractere.equals("(")&&
                            !numeros.contains(caractere)&&
                            !caractere.equals("+")&&
                            !caractere.equals("-")&&
                            !mulop.contains(caractere)&& 
                            !relop.contains(caractere)&& 
                            !caractere.equals(")")){
                            if(caractere.equals(',') ){
                                throw new RuntimeException("Expressao invalida!");
                            }
                            palavraAux+=String.valueOf(expressao.charAt(contAux));
                        }                      
                     }
                }
            }
    }//for
        //TESTE expfinal
        System.out.println("final = "+expressao);
        System.exit(0);
        return null;
    }    


}

   
