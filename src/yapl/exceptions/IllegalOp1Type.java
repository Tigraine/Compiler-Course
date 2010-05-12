package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.interfaces.YAPLToken;
import yapl.lib.YAPLException;

public class IllegalOp1Type extends YAPLException {
	public IllegalOp1Type(Token op) {
		setTokenInfo(op);
		errorNumber = CompilerError.IllegalOp1Type;
		message = String.format("illegal operand type for unary operator %s", op.image);
	}
}
