package yapl.exceptions;

import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class SymbolAlreadyDefinedException extends YAPLException {
	public SymbolAlreadyDefinedException(Symbol oldSymbol){
		message = String.format("symbol '%s' already declared in current scope (as %s)", oldSymbol.getName(), oldSymbol.getKindString());
		errorNumber = CompilerError.SymbolExists;
		setTokenInfo(yapl.token);
	}
}
