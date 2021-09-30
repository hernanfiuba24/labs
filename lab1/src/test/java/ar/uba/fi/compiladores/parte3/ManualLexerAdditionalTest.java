package ar.uba.fi.compiladores.parte3;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import ar.uba.fi.compiladores.parte3.BrainfuckAdder.BrainfuckAdderState;

public class ManualLexerAdditionalTest extends ManualLexerTest{

    @Test public void testExtractIntegerToken(){
        SingleTokenExtraction<BrainfuckAdderState> result = lexer.extractToken("++");
        assertEquals(1,result.getLastFinalIndex());
        assertEquals(BrainfuckAdderState.INTEGER,result.getFinalState());
    }

    @Test public void testGetFailure(){
        SingleTokenExtraction<BrainfuckAdderState> result = lexer.extractToken("peras ++");
        assertEquals(4,result.getLastFinalIndex());
        assertEquals(BrainfuckAdderState.ERROR,result.getFinalState());
    }
}
