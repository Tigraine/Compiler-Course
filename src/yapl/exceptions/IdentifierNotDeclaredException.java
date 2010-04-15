package yapl.exceptions;

import yapl.yapl;
import yapl.lib.YAPLException;
import yapl.interfaces.CompilerError;

public class IdentifierNotDeclaredException extends YAPLException {
	public IdentifierNotDeclaredException(String name) {
		setTokenInfo(yapl.token);
		errorNumber = CompilerError.IdentNotDecl;
		message = String.format("identifier '%s' not declared", name);
	}
}
