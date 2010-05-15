package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class ReadonlyAssignException extends YAPLException {
	public ReadonlyAssignException(Token t){
		message = String.format("read-only l-value in assignment");
		errorNumber = CompilerError.ReadonlyAssign;
		setTokenInfo(t);
	}
}
