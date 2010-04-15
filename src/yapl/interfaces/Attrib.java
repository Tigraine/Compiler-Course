package yapl.interfaces;

import yapl.lib.Type;

/**
 * Attributes of production symbols for type checking and code generation.
 * An object implementing this interface stores attributes of production
 * symbols of the YAPL grammar. Conceptually, an Attrib object represents an
 * operand of a generated instruction, or a branch condition.
 * <p>
 * The constants defined by this interface specify the various kinds of operands
 * represented by an object implementing this interface.
 * </p><p>
 * An Attrib object may represent a memory operand which is to be computed
 * at run time using a base address and an offset, e.g. an array element.
 * In this case the base address is represented by an Attrib object b which
 * is linked to another Attrib object representing the offset using the
 * <code>b.setOffsetAttrib(Attrib)</code> method.
 * </p>
 * 
 * @author Mario Taschwer
 * @version $Id: Attrib.java 141 2010-03-16 17:17:56Z mt $
 */
public interface Attrib {
	/** Invalid operand. */
	public static final byte None = 0;
	
	/** Register or stack operand. Requires a data type and 
	 * (optionally) a register number. 
	 */
	public static final byte Register  = 1;
	
	/** Constant operand. Requires a data type. */
	public static final byte Constant  = 2;
	
	/** Memory operand. 
	 * Requires a data type and a memory address.
	 * The address points to a memory location storing the target object
	 * of the given data type.
	 */
	public static final byte Address   = 3;
	
	/**
	 * Array element. This Attrib object holds the array base address,
	 * but the data type is set to the element type!
	 * The associated index operand is set using {@link #setOffsetAttrib(Attrib)}.
	 * Both the base address and the index are held in registers.
	 */
	public static final byte ArrayElement = 4;
	
	/** Return the kind of operand represented by this object.
	 * 
	 * @return one of the constants defined by this interface.
	 */
	public byte getKind();
	
	/**
	 * Set the kind of operand represented by this object.
	 * 
	 * @param kind     one of the constants defined by this interface.
	 */
	public void setKind(byte kind);
	
	/** Get the data type of this operand (or its target object). */
	public Type getType();
	
	/** Set the data type of this operand (or its target object). */
	public void setType(Type type);
	
	/** Return <code>true</code> iff this operand can be evaluated
	 * at compile time.
	 */
	public boolean isConstant();
	
	/** Specify whether this operand can be evaluated at compile time. */
	public void setConstant(boolean isConstant);
	
	/** Return <code>true</code> iff this operand represents
	 * a global memory object (i.e. it is stored in heap memory).
	 */
	public boolean isGlobal();
	
	/** Specify whether this operand represents a global memory object.
	 * @see #isGlobal()
	 */
	public void setGlobal(boolean isGlobal);
	
	/** Return the address offset (for memory operands).
	 *  If this operand represents a global memory object
	 *  (see {@link #isGlobal()}), the offset is relative to
	 *  the global variable storage area in heap memory. Otherwise,
	 *  the offset is relative to the current stack frame.
	 */
	public int getOffset();
	
	/** Set the address offset (for memory operands). 
	 * @see #getOffset()
	 */
	public void setOffset(int offset);
	
	/** Get the register number (for register operands). */
	public byte getRegister();
	
	/** Set the register number (for register operands). */
	public void setRegister(byte register);
	
	/**
	 * Return the Attrib object representing an offset relative to the
	 * memory address represented by this object.
	 */
	public Attrib getOffsetAttrib();
	
	/**
	 * Set the Attrib object representing an offset relative to the
	 * memory address represented by this object.
	 * @see #getOffsetAttrib()
	 */
	public void setOffsetAttrib(Attrib offset);
}
