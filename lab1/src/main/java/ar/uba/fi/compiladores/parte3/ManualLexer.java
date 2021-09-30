package ar.uba.fi.compiladores.parte3;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa un algoritmo de lexeo a partir de un lenguage.
 */
public class ManualLexer<S,T>{
    private LanguageAutomata<S,T> language;
    public ManualLexer(LanguageAutomata<S,T> language){
        this.language=language;
    }
    /**
     * extracts a token from a string and returns the next state of the LanguageAutomata and the length of the extracted token.
     * @param program
     * @return
     */
    public SingleTokenExtraction<S> extractToken(String program){
        S actualState = language.getInitialState();
        S followState = null;
        S lastState = null;
        int lastIndex = 0;
        int i = 0;


        char c = program.charAt(i);
        followState = language.transition(actualState, c);
        boolean isDead = followState == language.getDeadState();
        if (language.isFinal(followState)){
            lastState = followState;
            actualState = followState;
            lastIndex = i;
        }
        if (isDead) {
            return new SingleTokenExtraction<S>(followState, lastIndex);
        }
        
        i = i+1;
        while (i < program.length()) {
            c = program.charAt(i);
            followState = language.transition(actualState, c);
            isDead = followState == language.getDeadState();
            if (language.isFinal(followState)){
                if (!isDead) {
                    lastState = followState;
                    lastIndex = i;
                }
            }
            
            if (isDead) {
                return new SingleTokenExtraction<S>(lastState, lastIndex);
            }
            actualState = followState;
            i = i+1;
        }        
        return new SingleTokenExtraction<S>(lastState, lastIndex);
    }

    public List<Token<T>> lex(String program) throws BadTokenException{
        List<Token<T>> tokens = new ArrayList<>();
        while (program.length() > 0) {
            SingleTokenExtraction<S> token = extractToken(program);
            T tokenType = language.stateToTokenType(token.getFinalState());

            String tokenContent = program.substring(0, token.getLastFinalIndex()+1);

            if (language.getErrorState() == token.getFinalState()) {
                throw new BadTokenException(tokenContent);
            }

            if ( tokenType != null) {
                tokens.add(new Token<T>(tokenType, tokenContent));
            }

            program = program.substring(token.getLastFinalIndex()+1);
        }
        tokens.add(new Token<T>(language.eofTokenType(), null));
        return tokens;
    }

}