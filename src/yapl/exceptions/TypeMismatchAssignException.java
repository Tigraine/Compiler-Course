package yapl.exceptions;

import yapl.Token;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class TypeMismatchAssignException extends YAPLException {
	public static Token errorToken;
	public TypeMismatchAssignException(){
		message = String.format("type mismatch in assignment");
		errorNumber = CompilerError.TypeMismatchAssign;
		setTokenInfo(errorToken);
	}
}
