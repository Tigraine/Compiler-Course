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
	
	@Override 
	public void setReadonly(boolean readOnly) {
		elementType.setReadonly(readOnly);
		super.setReadonly(readOnly);
	}
}
