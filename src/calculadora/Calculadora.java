/*
 * Copyright (C) 2017 Roberto
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package calculadora;

import calculadora.calculo.Robertiano;

/**
 * <p>Esta clase nos sirve para revisar que las expresiones esten bien estructuradas, 
 * asi como poder saber saber de antemano si la expresion puede ser evaluada o no.</p> 
 * 
 * <p>Tambien nos provee de distintas banderas para saber el motivo de que la expresion
 * no es valida.</p>
 * 
 * @author Roberto
 * @version 1.0
 * @since 1.0
 */

public class Calculadora {
    
    /**
     * <p>Esta variable guarda la sentencia que se va a evaluar</p>
     * 
     * @since 1.0
     */
    
    private String sentencia = "";
    
    /**
     * <p>Esta clase es la que calcula el resultado de una expresion.</p>
     * 
     * @since 1.0
     */
    
    private Robertiano robertiano = null;
    
    /**
     * <p>Variable para que no se instancie mas de una calculadora por ejecucion de
     * programa.</p>
     * 
     * @since 1.0
     */
    
    private static Calculadora calculadora = null;
    
    /**
     * <p>Constructor privado de la clase para que quien se encarge de hacer los 
     * calculos solo sea un objeto y este no sea replicable.</p>
     * 
     * @since 1.0
     */
    
    private Calculadora(){}
    
    /**
     * <p>Metodo que nos provee la instancia de la clase, en caso de no existir 
     * la construye</p>
     * 
     * @since 1.0
     * @return <b>Instancia de la clase.</b>
     */
    
    public static Calculadora getInstancia(){
        return calculadora == null?calculadora = new Calculadora():calculadora;
    }
    
    /**
     * <p>Metodo para ingresar una nueva sentencia.</p>
     * 
     * <p>La sentencia se formatea para que sea valida, en caso de ser posible.</p>
     * 
     * @since 1.0
     * @param sentencia <i>Sentencia ingresada en la clase.</i>
     */
    
    public void setSentencia(String sentencia){
        this.sentencia = formatearSentencia(sentencia);
        ingresarRobertiano();
    }
    
    /**
     * <p>Metodo que ingresa la expresion en el objeto que se encargara de realizar
     * el calculo, en caso de ser valido.</p>
     * 
     * @since 1.0
     */
    
    private void ingresarRobertiano(){
        if(Validador.getInstancia().isValido(sentencia))robertiano = new Robertiano(sentencia);
    }
    
    /**
     * <p>Ingresa la expresion sin revisar que sea valida.</p>
     * 
     * @since 1.0
     */
    
    public void forcarCalculo(){
        robertiano = new Robertiano(sentencia);
    }
    
    /**
     * <p>Metodo que retorna si la expresion ingresada es valida.</p>
     * 
     * <p>En caso de no haber ingresado ninguna expresion y se llama este metodo, 
     * se retorna false.</p>
     * 
     * @since 1.0
     * @return <b><i>Booleano</i> que indica si la expresion
     * es valida.</b>
     */
    
    public boolean isValido(){
        return Validador.getInstancia().isValido(sentencia);
    }
    
    /**
     * <p>Retorna un string con un resumen de la validacion.</p>
     * 
     * @since 1.0
     * @return <b>Resumen de la validacion.</b>
     */
    
    public String showValidacion(){
        return Validador.getInstancia().getValidacion(sentencia);
    }
    
    /**
     * <p>Retorna la sentencia que se ingreso en la calculadora.</p>
     * 
     * @since 1.0
     * @return <b>Sentencia.</b>
     */
    
    public String getSentencia(){
        return sentencia;
    }
    
    /**
     * <p>Este metodo retorna la notacion <i>Robertiana</i> que se utiliza para 
     * dar el resultado a la expresion.</p>
     * 
     * @since 1.0
     * @return <b>Expresion en notacion <i>Robertiana</i>.</b>
     */
    
    public String getRobertiano(){
        if(robertiano==null)throw new ECalculadora();
        return robertiano.showRobertiano();
    }
    
    /**
     * <p>Retorna el resultado de la operacion.</p>
     * 
     * <p>En caso de no haber ingresado una expresion retorna una excepcion en
     * tiempo de ejecucion.</p>
     * 
     * @since 1.0
     * @return <b>Resultado de la operacion</b>
     */
    
    public double getResultado(){
        if(robertiano==null)throw new ECalculadora();
        return robertiano.getResultado();
    }
    
    /**
     * <p>Retorna un arreglo con las banderas de la validacion.</p>
     * 
     * <p>El primer valor indica si la expresion cumple con la REGEX general. El
     * segundo nos indica si los parentesis cierran de manera correcta.</p>
     * 
     * @since 1.0
     * @return <b>Banderas de la validacion.</b>
     */
    
    public boolean[] getBanderas(){
        return Validador.getInstancia().getBanderas(sentencia);
    }
    
    /**
     * <p>Este metodo ingresa ciertos operadores en caso de que en la expresion
     * ingresada se hubieran obviado. Casos:</p>
     * <ul>
     *  <li>Expresion: "(1)(1)" Formato: "(1)*(1)"</li>
     *  <li>Expresion: "1(2)" Formato: "1*(1)"</li>
     *  <li>Expresion: "(1)1" Formato: "(1)*1"</li>
     * </ul>
     * 
     * @since 1.0
     * @param sentencia <b>Se ingresa la sentencia que se va a evaluar.</b>
     * @return <b>Sentencia formateada</b>
     */
    
    private String formatearSentencia(String sentencia){
        if(sentencia.isEmpty())return sentencia;
        sentencia = sentencia.replaceAll("\\)\\(", ")*(");
        String aux = String.valueOf(sentencia.charAt(0));
        for (int i = 1; i < sentencia.length() - 1; i++) {
            if(sentencia.charAt(i)=='(' && Utileria.isNumero(sentencia.charAt(i - 1)))aux = aux.concat("*(");
            else if(sentencia.charAt(i)==')' && Utileria.isNumero(sentencia.charAt(i + 1)))aux = aux.concat(")*");
            else aux = aux.concat(String.valueOf(sentencia.charAt(i)));
        }
        return sentencia.length()>1?aux.concat(String.valueOf(sentencia.charAt(sentencia.length() - 1))):aux;
    }
}