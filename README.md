# Calculo con orden de operaciones.
Hay ocasiones en las que se necesita hacer una operación matemática simple en tiempo de ejecución de un programa. Como cuando se insertó una pequeña calculadora a un sistema de cobro de una tienda.

El problema comienza cuando se llega a una situación en la que si no se hace el cálculo en el orden correcto el resultado final puede llegar a ser erróneo, por ejemplo:

**2+25**
Resultados |
------------ |
20 |
12 |
¿Cuál de los dos es el resultado correcto? Bueno el resultado es 12, debido a que primero se tiene que multiplicar y luego dividir, esto lo indican las reglas de **[PEDMAS](http://www.disfrutalasmatematicas.com/operaciones-orden-pemdas.html)**.

### Notacion Robertiana

Esta notación viene basada en la **[notación pocala inversa](https://es.wikipedia.org/wiki/Notaci%C3%B3n_polaca_inversa)** y la cual tiene una explicación más profunda en la wiki del proyecto, pero en general es la conversión de una cadena a un conjunto de subexpresiones leyendo de izquierda a derecha la sentencia.

**Expresion:** _4*2(3+6)/3_
**Notacion robertiana:** _([{[{[{[4.0][2.0] *}][([3.0][6.0][0.0] ++)] *}][3.0] /}][0.0] +)_

El siguiente proyecto intenta dar una solución a este problema, otorgando una herramienta con la cual podamos hacer estos cálculos de manera sencilla. Hay dos clases que se pueden utilizar para estos fines, las cuales serían:
- **Calculadora**
Esta clase nos permite validad la expresión, si es que no tenemos una manera de estar seguros que la información ingresada por el usuario sea correcta, por ejemplo:

```java
String expresion = "4+2(9)";
Calculadora calculadora = Calculadora.getInstancia();
calculadora.setSentencia(expresion);
if(calculadora.isValido()){
    System.out.println(calculadora.getResultado());
}else{
    System.out.println(calculadora.showValidacion());
}
```
- **Robertiano**
Si ya se tiene un sistema que valide la expresión, se puede usar directamente la clase que efectúa el cálculo sin necesidad de gastar recursos revalidando la expresión, por ejemplo:

```java
String expresion = "4-(-6)";
Robertiano robertiano = new Robertiano(expresion);
double resultado = robertiano.getResultado();
System.out.println(resultado);
```
##### Espero que estas herramientas sean de utilidad para ti y no dudes en colaborar si lo crees pertinente.
