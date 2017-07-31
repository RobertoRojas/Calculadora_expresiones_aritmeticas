package test;

import calculadora.calculo.Robertiano;

public class Test1 {

    public static void main(String[] args) {
        String expresion = "(1*3-(2/8))-6";
        Robertiano robertiano = new Robertiano(expresion);
        System.out.println("Resultado: " + robertiano.getResultado());
        System.out.println("Expresion: " + robertiano.showRobertiano());
    }    
}
