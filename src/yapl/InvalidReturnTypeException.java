package yapl;

import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class InvalidReturnTypeException extends YAPLException {
	public InvalidReturnTypeException(Symbol s, Token t){
		message = String.format("returning none or invalid type from function %s", s.getName());
		errorNumber = CompilerError.InvalidReturnType;
		setTokenInfo(t);
	}
}
