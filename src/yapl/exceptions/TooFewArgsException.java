package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class TooFewArgsException extends YAPLException {
	public TooFewArgsException(Symbol s, Token t) {
		errorNumber = CompilerError.TooFewArgs;
		message = String.format("too few arguments for procedure %s", s.getName());
		setTokenInfo(t);
	}
}
