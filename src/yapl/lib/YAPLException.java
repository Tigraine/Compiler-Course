package yapl.lib;

import yapl.Token;
import yapl.interfaces.CompilerError;
import yapl.interfaces.YAPLToken;

public abstract class YAPLException extends Exception implements CompilerError {

	protected int column;
	protected int errorNumber;
	protected int line;
	protected String message;

	@Override
	public int column() {
		return column;
	}

	@Override
	public int errorNumber() {
		return errorNumber;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int line() {
		return line;
	}
	
	protected void setTokenInfo(Token t) {
		line = t.line();
		column = t.column();
	}

}
