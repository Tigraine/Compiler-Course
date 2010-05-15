package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class MissingReturnException extends YAPLException {
	public MissingReturnException(Symbol s, Token t){
		message = String.format("missing Return statement in function %s", s.getName());
		errorNumber = CompilerError.MissingReturn;
		setTokenInfo(t);
	}
}
