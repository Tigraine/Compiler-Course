package yapl.lib;

public class Type {
	public Type parentType;
	public boolean isCompatible(Type t){
		Type parent = t;
		return this.getClass().equals(t.getClass());
	}
}
