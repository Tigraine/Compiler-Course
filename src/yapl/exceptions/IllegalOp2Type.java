package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class IllegalOp2Type extends YAPLException {
	public IllegalOp2Type(Token t) {
		setTokenInfo(t);
		message = String.format("illegal operand types for binary operator %s", t.image);
		errorNumber = CompilerError.IllegalOp2Type;
	}
}
