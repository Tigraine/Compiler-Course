package yapl.lib;

import java.util.ArrayList;
import java.util.List;

import yapl.interfaces.Symbol;

public class Type {
	public boolean isCompatible(Type t){
		return this.getClass().equals(t.getClass());
	}
}
