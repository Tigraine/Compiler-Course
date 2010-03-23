package yapl.interfaces;

/** Generic YAPL compiler error interface.
 * It defines error numbers and common methods of exception and error types
 * thrown by the YAPL compiler.
 * 
 * @author Mario Taschwer
 * @version $Id: CompilerError.java 137 2010-03-10 20:34:28Z mt $
 */
public interface CompilerError {

	/* --- Error Numbers --- */
	
	/** Internal error. */
	public static final int Internal             = 1;
	/** Lexical error. */
	public static final int Lexical              = 2;
	/** Syntax error. */
	public static final int Syntax               = 3;
	
	/* Symbol check errors */
	
	/** Symbol already declared. */
	public static final int SymbolExists         = 10;
	/** Identifier not declared. */
	public static final int IdentNotDecl         = 11;
	/** Illegal use of symbol. */
	public static final int SymbolIllegalUse     = 12;
	/** End identifier does not match program|procedure. */
	public static final int EndIdentMismatch     = 13;
	
	/* Type check errors */
	
	/** Expression before '[' is not an array type. */
	public static final int SelectorNotArray     = 20;
	/** Array index or dimension is not an integer type. */
	public static final int BadArraySelector     = 21;
	/** Expression after '#' is not an array type. */
	public static final int ArrayLenNotArray     = 22;
	/** Illegal operand type for unary operator. */
	public static final int IllegalOp1Type       = 23;
	/** Illegal operand type for binary operator. */
	public static final int IllegalOp2Type       = 24;
	/** Illegal operand type for relational operator. */
	public static final int IllegalRelOpType     = 25;
	/** Illegal operand type for equality operator. */
	public static final int IllegalEqualOpType   = 26;
	/** Using procedure (not a function) in expression. */
	public static final int ProcNotFuncExpr      = 27;
	/** Type mismatch in assignment. */
	public static final int TypeMismatchAssign   = 28;
	/** Argument not applicable to procedure. */
	public static final int ArgNotApplicable     = 29;
	/** Too few arguments for procedure. */
	public static final int TooFewArgs           = 30;
	/** Condition is not a boolean expression. */
	public static final int CondNotBool          = 31;
	/** Missing return statement in function. */
	public static final int MissingReturn        = 32;
	/** Returning none or invalid type from function. */
	public static final int InvalidReturnType    = 33;
	/** Illegal return value in procedure (not a function). */
	public static final int IllegalRetValProc    = 34;
	/** Illegal return value in main program. */
	public static final int IllegalRetValMain    = 35;

	/* Code generation errors */
	
	/** Too many registers used. */
	public static final int NoMoreRegs           = 50;
	
	/** Too many array dimensions. */
	public static final int TooManyDims          = 51;
	
    /* --- End of error numbers --- */
	
	/** Return the compiler error number.
	 * @return One of the constants defined by this class.
	 */
	public int errorNumber();
	
	/** Return the source code line number where the error occurred. */
	public int line();
	
	/** Return the source code column number where the error occurred. */
	public int column();
	
	/** Return the detailed error message. */
	public String getMessage();
}
