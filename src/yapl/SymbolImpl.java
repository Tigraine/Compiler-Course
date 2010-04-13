package yapl;

import yapl.interfaces.Symbol;
import yapl.lib.Type;

public class SymbolImpl implements Symbol {

	private Symbol.SymbolKind kind;
	private Type type;
	private boolean seen;
	private boolean isReference;
	private int offset;
	private Symbol nextSymbol;
	private boolean isGlobal;

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
		throw new UnsupportedOperationException("Not implemented yet");
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
	}

}
