package yapl;

import java.util.Hashtable;

import yapl.interfaces.Symbol;
import yapl.lib.YAPLException;

public class Scope {
	private Hashtable<String, Symbol> symbols = new Hashtable<String, Symbol>();
	private Scope parent;
	
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
			throw new YAPLException(String.format("symbol %s already declared in current scope (as %s)", symbolName, foundSymbol.getKindString()));
		}
		symbols.put(symbolName, symbol);
		symbol.setGlobal(parent == null);
	}
	
	public Symbol getSymbol(String name) throws YAPLException {
		if (symbols.containsKey(name)) {
			return symbols.get(name);
		}
		if (parent == null) throw new YAPLException(String.format("Symbol %s not defined", name));
		return parent.getSymbol(name);
	}
	public Scope getParent() {
		return parent;
	}
}
