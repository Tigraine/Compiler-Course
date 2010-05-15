package yapl.lib;

public class ArrayType extends Type {
	private Type elementType;

	public void setElementType(Type elementType) {
		this.elementType = elementType;
	}

	public Type getElementType() {
		return elementType;
	}
	
	public ArrayType(Type elementType) {
		this.elementType = elementType;
	}
}
