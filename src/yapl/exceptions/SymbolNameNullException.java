package yapl.exceptions;

import yapl.yapl;
import yapl.lib.YAPLException;

public class SymbolNameNullException extends YAPLException {
	public SymbolNameNullException() {
		setTokenInfo(yapl.token);
		message = "Symbol name is null";
	}
}
