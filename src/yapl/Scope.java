package yapl;

import java.util.Hashtable;

import yapl.interfaces.Symbol;
import yapl.interfaces.Symbol.SymbolKind;
import yapl.lib.YAPLException;

public class Scope {
	private Hashtable<String, Symbol> symbols = new Hashtable<String, Symbol>();
	private Scope parent;
	private Symbol parentSymbol;
	
	public Scope() {
		this(null);
	}
	public Scope(Scope parent) {
		this.parent = parent;
	}
	
	public void addSymbol(Symbol symbol) throws YAPLException {
		String symbolName = symbol.getName();
		if (symbols.containsKey(symbolName)) {
			Symbol foundSymbol = getSymbol(symbolName);
			throw new SymbolAlreadyDefinedException(foundSymbol);
		}
		symbols.put(symbolName, symbol);
		symbol.setGlobal(parent == null);
	}
	
	public Symbol getSymbol(String name) throws YAPLException {
		if (symbols.containsKey(name)) {
			return symbols.get(name);
		}
		if (parent == null) throw new IdentifierNotDeclaredException(name);
		return parent.getSymbol(name);
	}
	public Scope getParent() {
		return parent;
	}
	
	public void setParentSymbol(Symbol parentSymbol) {
		this.parentSymbol = parentSymbol;
	}
	public Symbol getNearestParentSymbol(SymbolKind kind) {
		if (parentSymbol == null && parent != null) return parent.getNearestParentSymbol(kind);
		return parentSymbol;
	}
}
