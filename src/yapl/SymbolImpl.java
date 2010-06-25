package yapl;

import java.util.ArrayList;
import java.util.List;

import yapl.interfaces.Symbol;
import yapl.lib.ArrayType;
import yapl.lib.Type;

public class SymbolImpl implements Symbol {

	private Symbol.SymbolKind kind;
	private Type type;
	private boolean seen;
	private boolean isReference;
	private int offset;
	private Symbol nextSymbol;
	private boolean isGlobal;
	private String name;
	private List<Symbol> parameters = new ArrayList<Symbol>();

	public SymbolImpl(String name, Symbol.SymbolKind kind) {
		this.name = name;
		setKind(kind);
	}
	
	@Override
	public Symbol.SymbolKind getKind() {
		return kind;
	}

	@Override
	public String getKindString() {
		return kind.name();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Symbol getNextSymbol() {
		return nextSymbol;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public boolean getReturnSeen() {
		return seen;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public boolean isGlobal() {
		return isGlobal;
	}

	@Override
	public boolean isReference() {
		return isReference;
	}

	@Override
	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}

	@Override
	public void setKind(SymbolKind kind) {
		this.kind = kind;
	}

	@Override
	public void setNextSymbol(Symbol symbol) {
		this.nextSymbol = symbol;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public void setReference(boolean isReference) {
		this.isReference = isReference;
	}

	@Override
	public void setReturnSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public void setType(Type type) {
		this.type = type;
		if (type instanceof ArrayType)
			isReference = true;
	}

	@Override
	public List<Symbol> getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(List<Symbol> parameters) {
		this.parameters = parameters;
		
	}
}
