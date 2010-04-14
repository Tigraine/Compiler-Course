package yapl;

import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;
import yapl.interfaces.CompilerError;

public class SymbolIllegalUseException extends YAPLException {

	public SymbolIllegalUseException(Symbol symbol) {
		setTokenInfo(yapl.token);
		errorNumber = CompilerError.SymbolIllegalUse;
		message = String.format("illegal use of %s '%s'", symbol.getKindString(), symbol.getName());
	}
}
