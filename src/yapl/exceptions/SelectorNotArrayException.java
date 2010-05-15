package yapl.exceptions;

import yapl.Token;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class SelectorNotArrayException extends YAPLException {
	public SelectorNotArrayException(Token t){
		message = String.format("expression before '%s' is not an array type", t.image);
		errorNumber = CompilerError.SelectorNotArray;
		setTokenInfo(t);
	}
}
