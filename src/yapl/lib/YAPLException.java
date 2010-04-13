package yapl.lib;

import yapl.interfaces.CompilerError;

public class YAPLException extends Exception implements CompilerError {

	public YAPLException(String message) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int column() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int errorNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int line() {
		// TODO Auto-generated method stub
		return 0;
	}

}
