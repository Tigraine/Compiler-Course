package yapl.lib;

import java.util.ArrayList;
import java.util.List;

import yapl.interfaces.Symbol;

public class Type {
	private boolean isReadonly;

	public boolean isCompatible(Type t){
		if (this instanceof ArrayType && t instanceof ArrayType) {
			return ((ArrayType)this).getElementType().isCompatible(((ArrayType)t).getElementType());
		}
		return this.getClass().equals(t.getClass());
	}

	public boolean isReadonly() {
		return isReadonly;
	}

	public void setReadonly(boolean readOnly) {
		isReadonly = readOnly;
	}
}
