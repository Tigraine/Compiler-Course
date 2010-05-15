package yapl.exceptions;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class IllegalRetValProcException extends YAPLException {
	public IllegalRetValProcException(Symbol s, Token t){
		message = String.format("illegal return value in procedure %s (not a function)", s.getName());
		errorNumber = CompilerError.IllegalRetValProc;
		setTokenInfo(t);
	}
}
