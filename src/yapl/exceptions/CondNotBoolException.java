package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class CondNotBoolException extends YAPLException {
	public CondNotBoolException(Token t){
		message = String.format("condition is not a boolean expression");
		errorNumber = CompilerError.CondNotBool;
		setTokenInfo(t);
	}
}
