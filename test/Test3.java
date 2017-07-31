package test;

import calculadora.Calculadora;

public class Test3 {
    public static void main(String[] args){
        String[] sentencias = {
            "1+1",
            "23*(9-8)",
            "*2",
            "2*2*2*2*2",
            "1234$",
            "23(9*(9)"
        };
        Calculadora calculadora = Calculadora.getInstancia();
        for(String sentencia:sentencias){
            calculadora.setSentencia(sentencia);
            if(calculadora.isValido()){
                System.out.println("Sentencia " + sentencia + " valida");
                System.out.println("Resultado: " + calculadora.getResultado());
                System.out.println("Notacion: " + calculadora.getRobertiano());
            } else {
                System.out.println("Sentencia " + sentencia + " no valida");
                System.out.println("Valor no valido detalles: " + calculadora.showValidacion());
            }
        }
    }
}
