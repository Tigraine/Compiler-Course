package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class IllegalRelOpTypeException extends YAPLException {
	public IllegalRelOpTypeException(Token t) {
		setTokenInfo(t);
		message = String.format("non-integer operand types for relational operator %s", t.image);
		errorNumber = CompilerError.IllegalRelOpType;
	}
}
