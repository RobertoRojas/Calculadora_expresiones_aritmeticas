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

/**
 * <p>Esta clase valida que la expresion ingresada sea correcta.</p>
 * 
 * <p>Los parametros de revision son que la expresion cumpla ciertas reglas de
 * manera general y otra es que los parentesis sean correctos.</p>
 * 
 * @author Roberto
 * @version 1.0
 * @since 1.0
 */

public class Validador {
    
    /**
     * <p>Expresion regular para evaluar de manera general la expresion.</p>
     * 
     * @since 1.0
     */
    
    private final String REGEX = "^[\\(]*(([-]|[\\+])?[\\d]+([.][\\d]+)?)([\\)]*(\\+|\\*|\\/|-)([\\(][-]?)*([\\d]+([.][\\d]+)?)[\\)]*)*[\\)]*$";
    
    /**
     * <p>Intancia de la clase</p>
     * 
     * @since 1.0
     */
    
    private static Validador validador = null;
    
    /**
     * <p>Contructor por defecto de la clase, es privado para que no pueda ser
     * construido fuera de la instancia unica.</p>
     * 
     * @since 1.0
     */
    private Validador(){}
    
    /**
     * <p>Metodo con el que se obtiene la instancia de la clase, en caso de no 
     * estar inicializada se construye.</p>
     * 
     * @since 1.0
     * @return <i>Retorna instancia del validador.</i>
     */
    
    static Validador getInstancia(){
        if(validador == null)validador = new Validador();
        return validador;
    }
    
    /**
     * <p>Revisa si se cumplen todos los parametros de validacion.</p>
     * 
     * @since 1.0
     * @param sentencia <b>Sentencia a revisar.</b>
     * @return <b>Si se cumplen todos los parametros de validacion retorna 
     * <i style="color:blue">true</i>, en caso contrario retorna
     * <i style="color:blue">false</i>.</b>
     */
    
    boolean isValido(String sentencia){
        return sentencia.matches(REGEX) && isParenctecisCorrectos(sentencia);
    }
    
    /**
     * <p>Esta funcion nos indica el estado de las validaciones de la expresion.</p>
     * 
     * @since 1.0
     * @param sentencia <b>Sentencia a evaluar.</b>
     * @return <b>Retorna un mensaje que detalla las validaciones de la expresion
     * ingresada.</b>
     */
    
    String getValidacion(String sentencia){
        return "General: ".concat(getGeneral(sentencia)).concat("|Parentecis: ").concat(getValidacionParentencis(sentencia));
    }
    
    /**
     * <p>Obtiene las revisiones de la sentencia en forma de un arreglo.</p>
     * 
     * <p>El primer elemento indica si se cumple la expresion general y el segundo
     * si los parentesis estan bien formados.</p>
     * 
     * @since 1.0
     * @param sentencia <b>Sentencia a revisar.</b>
     * @return <b>Arreglo con las revisiones en partes.</b>
     */
    
    boolean[] getBanderas(String sentencia){
        return new boolean[]{sentencia.matches(REGEX), isParenctecisCorrectos(sentencia)};
    }
    
    /**
     * <p>Esta funcion nos indica el estado de una sentencia al evaluarse generalmente.</p>
     * 
     * @since 1.0
     * @param sentencia <b>Sentencia a evaluar.</b>
     * @return <b>Retorna un mensaje que detalla el estado de la validacion.</b>
     */
    
    private String getGeneral(String sentencia){
        if(sentencia.matches(REGEX))return "correcto";
        return "incorrecto, un error en la sintaxis general de la expresion.";
    }
    
    /**
     * <p>Esta funcion nos muestra el estado de la validacion de parentesis de 
     * la expresion.</p>
     * 
     * @since 1.0
     * @param sentencia <b>Sentencia a evaluar.</b>
     * @return <b>Nos indica el estado de la validacion con respecto a los parentesis.</b>
     */
    
    private String getValidacionParentencis(String sentencia){
        if(isParenctecisCorrectos(sentencia))return "correcto";
        int apertura = 0;
        int cierre = 0;
        for (int i = 0; i < sentencia.length(); i++) {
            char caracter = sentencia.charAt(i);
            switch(caracter){
                case '(':
                    apertura++;
                    continue;
                case ')':
                    cierre++;
                default:
            }
        }
        return "incorrecto, no se ".concat(apertura>cierre?"cierran ":"abren ").concat("todos los perentecis ingresados.");
    }
    
    /**
     * <p>Este metodo revisa que la sentencia cumpla con las reglas de parentesis.
     * Como las siguientes:</p>
     * 
     * <ul>
     *  <li>Se tienen que cerrar todos los parentesis.</li>
     *  <li>No puede ser el parentesis ")" el primero en ser encontrado.</li>
     * </ul>
     * 
     * @since 1.0
     * @param sentencia <b>Sentencia a evaluar.</b>
     * @return <b>Si cumple las reglas de parentesis retorna <i style="color:blue">true</i>,
     * en caso contrario retornaria <i style="color:blue">false</i>.</b>
     */
    
    private boolean isParenctecisCorrectos(String sentencia){
        if(sentencia.isEmpty())return false;
        int apertura = 0, cierre = 0;
        for(int i = 0; i < sentencia.length(); i++){
            if(sentencia.charAt(i)=='(')apertura++;
            if(sentencia.charAt(i)==')')cierre++;
            if(cierre>apertura)return false;
        }
        return apertura == cierre;
    }
}