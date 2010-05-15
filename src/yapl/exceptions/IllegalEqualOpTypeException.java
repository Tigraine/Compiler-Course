package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class IllegalEqualOpTypeException extends YAPLException {
	public IllegalEqualOpTypeException(Token op) {
		setTokenInfo(op);
		errorNumber = CompilerError.IllegalEqualOpType;
		message = String.format("illegal operand types for equality operator %s", op.image);
	}
}
