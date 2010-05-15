package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class ArrayLenNotArrayException extends YAPLException {
	public ArrayLenNotArrayException(Token t){
		message = String.format("expression after '%s' is not an array type", t.image);
		errorNumber = CompilerError.ArrayLenNotArray;
		setTokenInfo(t);
	}
}
