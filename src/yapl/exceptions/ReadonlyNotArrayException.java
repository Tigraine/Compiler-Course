package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;

public class ReadonlyNotArrayException extends YAPLException {
	public ReadonlyNotArrayException(Token t){
		message = String.format("type qualified as Readonly is not an array type");
		errorNumber = CompilerError.ReadonlyNotArray;
		setTokenInfo(t);
	}
}
