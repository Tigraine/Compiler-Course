package yapl;

import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class EndIdentifierMismatchException extends YAPLException {
	public EndIdentifierMismatchException(Symbol startSymbol, String endSymbol) {
		errorNumber = CompilerError.EndIdentMismatch;
		setTokenInfo(yapl.token);
		message = String.format("End %s does not match %s %s", startSymbol.getName(), startSymbol.getKindString(), endSymbol);
	}
}
