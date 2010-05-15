package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class IllegalRetValMainException extends YAPLException {
	public IllegalRetValMainException(Token t){
		message = String.format("illegal return value in main program");
		errorNumber = CompilerError.IllegalRetValMain;
		setTokenInfo(t);
	}
}
