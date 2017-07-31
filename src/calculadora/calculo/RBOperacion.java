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

package calculadora.calculo;

/**
 * <p>Esta clase se encarga de operar dos valores dados.</p>
 * 
 * <p>Esta clase hereda de la clase abstracta de <i>{@link RBase}</i>.</p>
 * 
 * @author Roberto
 * @version 1.0
 * @since 1.0
 * @see RBase
 */

public class RBOperacion extends RBase{
    
    /**
     * <p>Valores a operar.</p>
     * 
     * @since 1.0
     */
    
    private final RBase a,b;
    
    /**
     * <p>Tipo de operacion que se va a realizar.</p>
     * 
     * @since 1.0
     */
    
    private final REOperadores operador;
    
    /**
     * <p>Constructor de la clase que recibe los valores y la operacion que se 
     * va a realizar</p>
     * 
     * @since 1.0
     * @param a <i>Primer valor, numerador en una operacion de tipo division.</i>
     * @param b <i>Segundo valor, denominador en una operacion de tipo division</i>
     * @param operador <i>Tipo de operacion que se va a realizar.</i>
     */
    
    RBOperacion(RBase a, RBase b, REOperadores operador) {
        this.a = a;
        this.b = b;
        this.operador = operador;
    }
    
    
    /**
     * <p>Retorna la expresion en notacion <i>Robertiana.</i></p>
     * 
     * @since 1.0
     * @return <i>Cadena con la notacion robertianan de la expresion</i>
     */
    
    @Override
    String showRobertiano() {
        return "{[".concat(a.showRobertiano()).concat("][").concat(b.showRobertiano())
                .concat("] ").concat(operador.toString()).concat("}");
    }
    
    /**
     * <p>Obtiene el resultado de operar los valores con el operador ingresado.</p>
     * 
     * @since 1.0
     * @return <i>Resultado de la expresion.</i>
     */

    @Override
    double getValor() {
        return operador.operar(a, b);
    }
}