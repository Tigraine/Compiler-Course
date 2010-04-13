package yapl;

import java.util.Hashtable;

import yapl.interfaces.Symbol;
import yapl.interfaces.Symboltable;
import yapl.lib.YAPLException;

public class SymboltableImpl implements Symboltable {

	private Scope currentScope = new Scope();
	
	@Override
	public void addSymbol(Symbol symbol) throws YAPLException {
		if (symbol.getName() == null) throw new YAPLException("Symbol name is null");
		currentScope.addSymbol(symbol);
	}

	@Override
	public void closeScope() {
		currentScope = currentScope.getParent();
	}

	@Override
	public Symbol getNearestParentSymbol(int kind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol lookup(String name) throws YAPLException {
		if (name == null) throw new YAPLException("Symbol name is null");
		return currentScope.getSymbol(name);
	}

	@Override
	public void openScope(boolean isGlobal) {
		Scope scope = new Scope(currentScope);
		currentScope = scope;
	}

	@Override
	public void setDebug(boolean on) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParentSymbol(Symbol sym) {
		// TODO Auto-generated method stub
	}

}
