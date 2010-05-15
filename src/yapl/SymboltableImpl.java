package yapl;

import java.util.Hashtable;

import yapl.exceptions.SymbolNameNullException;
import yapl.interfaces.Symbol;
import yapl.interfaces.Symboltable;
import yapl.interfaces.Symbol.SymbolKind;
import yapl.lib.YAPLException;
import yapl.util.ConsoleLogger;
import yapl.util.Logger;
import yapl.util.NullLogger;

public class SymboltableImpl implements Symboltable {

	private Scope currentScope = new Scope();
	private Logger logger = new NullLogger();
	
	@Override
	public void addSymbol(Symbol symbol) throws YAPLException {
		logger.log("Adding Symbol %s", symbol.getName());
		if (symbol.getName() == null) throw new SymbolNameNullException();
		currentScope.addSymbol(symbol);
	}

	@Override
	public void closeScope() {
		logger.log("Closing scope");
		currentScope = currentScope.getParent();
	}

	@Override
	public Symbol getNearestParentSymbol(SymbolKind kind) {
		Symbol nearestParentSymbol = currentScope.getNearestParentSymbol(kind);
		if (nearestParentSymbol != null)
			logger.log("Looking up parent symbol, returning %s", nearestParentSymbol.getName());
		return nearestParentSymbol;
	}

	@Override
	public Symbol lookup(String name) throws YAPLException {
		if (name == null) throw new SymbolNameNullException();
		logger.log("Looking up %s", name);
		return currentScope.getSymbol(name);
	}

	@Override
	public void openScope(boolean isGlobal) {
		logger.log("Opening scope");
		currentScope = new Scope(currentScope);
		/*if (isGlobal) {
			currentScope = new Scope();
			logger.log("Opened new Global Scope");
		}*/
	}

	@Override
	public void setDebug(boolean on) {
		if (on){
			this.logger = new ConsoleLogger();
			logger.log("Symboltable logging is not ON");
		}
		else {
			logger.log("Symboltable logging is not OFF");
			this.logger = new NullLogger();
		}
	}

	@Override
	public void setParentSymbol(Symbol sym) {
		logger.log("Added parent symbol %s to scope", sym.getName());
		currentScope.setParentSymbol(sym);
	}

}
