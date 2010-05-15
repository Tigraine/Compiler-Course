package yapl.lib;

import java.util.ArrayList;
import java.util.List;

import yapl.interfaces.Symbol;

public class Type {
	public boolean isCompatible(Type t){
		if (this instanceof ArrayType && t instanceof ArrayType) {
			return ((ArrayType)this).getElementType().isCompatible(((ArrayType)t).getElementType());
		}
		return this.getClass().equals(t.getClass());
	}
}
