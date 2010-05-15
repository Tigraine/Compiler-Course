package yapl;

import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class ReadonlyArgException extends YAPLException {
	public ReadonlyArgException(int argNumber, Symbol s, Token t){
		message = String.format("read-only argument #1 passed to read-write procedure %s", argNumber, s.getType());
		errorNumber = CompilerError.ReadonlyArg;
		setTokenInfo(t);
	}
}
