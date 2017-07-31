package test;

import calculadora.Calculadora;

public class Test2 {
    public static void main(String[] args){
        String expresion = "2*((-2*4)/9)";
        Calculadora calculadora = Calculadora.getInstancia();
        calculadora.setSentencia(expresion);
        System.out.println("Resultado: " + calculadora.getResultado());
        System.out.println("Notacion: " + calculadora.getRobertiano());
    }
}
