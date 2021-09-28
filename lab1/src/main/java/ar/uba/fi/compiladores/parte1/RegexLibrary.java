package ar.uba.fi.compiladores.parte1;

import java.util.regex.Pattern;

public class RegexLibrary {
    
    private final static String DECIMAL_REGEX = "^[+-]?(([0-9][1-9]+|[1-9][0-9]+)\\.?\\d*)$";
    private final static String HEXA_REGEX = "^([1-9]|[A-F]|0[1-9]|[A-F])([0-9]|[A-F])*$";
    private final static String BRAIN_FUCK_REGEX = "^[+-]+$";
    private final static String NAMES_REGEX = "^[A-Z][a-z][a-z]+$";

    /**
     * Regex para decimales que no empiezan en 0
     * @return
     */
    Pattern getDecimalsRegex(){ return Pattern.compile(DECIMAL_REGEX); }

    /**
     * Regex para decimales que no empiezan en 0
     * @return
     */
    Pattern getHexaRegex(){ return Pattern.compile(HEXA_REGEX); }

    /**
     * Regex para brainfuck. 
     * @return
     */
    Pattern getBrainfuckRegex(){ return Pattern.compile(BRAIN_FUCK_REGEX); }

    /**
     * Nombres: Palabras con mayusculas de al menos 3 letras.
     * @return
     */
    Pattern getNamesRegex(){ return Pattern.compile(NAMES_REGEX); }

}
