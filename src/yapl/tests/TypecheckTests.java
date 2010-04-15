package yapl.tests;

import yapl.interfaces.CompilerError;

public class TypecheckTests extends AbstractParserTestCase {

	@Override
	protected String getFolder() {
		return "typecheck";
	}

	public void test01(){
		runTestExpectingError("test01", CompilerError.IllegalOp1Type, 10, 10);
	}
	
	public void test02(){
		runTestExpectingError("test02", CompilerError.IllegalOp2Type, 10, 12);
	}
	
	public void test03(){
		runTestExpectingError("test03", CompilerError.IllegalOp2Type, 11, 12);
	}
	
	public void test04(){
		runTestExpectingError("test04", CompilerError.IllegalRelOpType, 12, 12);
	}
	
	public void test05(){
		runTestExpectingError("test05", CompilerError.IllegalEqualOpType, 11, 12);
	}
	
	public void test06(){
		runTestExpectingError("test06", CompilerError.ProcNotFuncExpr, 16, 10);
	}
	
	public void test07(){
		runTestExpectingError("test07", CompilerError.SelectorNotArray, 12, 11);
	}
	
	public void test08(){
		runTestExpectingError("test08", CompilerError.BadArraySelector, 12, 13);
	}
	
	public void test09(){
		runTestExpectingError("test09", CompilerError.BadArraySelector, 13, 30);
	}
	
	public void test10(){
		runTestExpectingError("test10", CompilerError.ArrayLenNotArray, 12, 10);
	}
	
	public void test11(){
		runTestExpectingError("test11", CompilerError.TypeMismatchAssign, 11, 7);
	}
	
	public void test12(){
		runTestExpectingError("test12", CompilerError.BadArraySelector, 12, 16);
	}
	
	public void test13(){
		runTestExpectingError("test13", CompilerError.TypeMismatchAssign, 13, 17);
	}
	
	public void test14(){
		runTestExpectingError("test14", CompilerError.TypeMismatchAssign, 11, 7);
	}
	
	public void test15(){
		runTestExpectingError("test15", CompilerError.IllegalOp2Type, 12, 37);
	}
	
	public void test16(){
		runTestExpectingError("test16", CompilerError.TypeMismatchAssign, 13, 7);
	}
	
	public void test17(){
		runTestExpectingError("test17", CompilerError.TypeMismatchAssign, 13, 9);
	}
	
	public void test18(){
		runTestExpectingError("test18", CompilerError.ProcNotFuncExpr, 15, 14);
	}
	
	public void test19(){
		runTestExpectingError("test19", CompilerError.TypeMismatchAssign, 18, 7);
	}
	
	public void test20(){
		runTestExpectingError("test20", CompilerError.TooFewArgs, 13, 10);
	}
	
	public void test21(){
		runTestExpectingError("test21", CompilerError.TooFewArgs, 13, 12);
	}
	
	public void test22(){
		runTestExpectingError("test22", CompilerError.ArgNotApplicable, 13, 18);
	}
	
	public void test23(){
		runTestExpectingError("test23", CompilerError.ArgNotApplicable, 15, 17);
	}
	
	public void test24(){
		runTestExpectingError("test24", CompilerError.ArgNotApplicable, 15, 15);
	}
	
	public void test25(){
		runTestExpectingError("test25", CompilerError.ArgNotApplicable, 15, 10);
	}
	
	public void test26(){
		runTestExpectingError("test26", CompilerError.ArgNotApplicable, 19, 19);
	}

	public void test27(){
		runTestExpectingError("test27", CompilerError.ArgNotApplicable, 11, 24);
	}
	
	public void test28(){
		runTestExpectingError("test28", CompilerError.CondNotBool, 10, 12);
	}
	
	public void test29(){
		runTestExpectingError("test29", CompilerError.CondNotBool, 13, 11);
	}
	
	public void test30(){
		runTestExpectingError("test30", CompilerError.MissingReturn, 16, 1);
	}
	
	public void test31(){
		runTestExpectingError("test31", CompilerError.InvalidReturnType, 11, 15);
	}
	
	public void test32(){
		runTestExpectingError("test32", CompilerError.InvalidReturnType, 10, 4);
	}
	
	public void test33(){
		runTestExpectingError("test33", CompilerError.IllegalRetValProc, 11, 15);
	}
	
	public void test34(){
		runTestExpectingError("test34", CompilerError.IllegalRetValMain, 11, 11);
	}
	
	public void test35(){
		runTestNotExpectingError("test35");
	}
	
	public void test36(){
		runTestExpectingError("test36", CompilerError.IllegalRelOpType, 11, 15);
	}
	
	public void test37(){
		runTestExpectingError("test37", CompilerError.IllegalOp1Type, 10, 13);
	}
	
	public void test38(){
		runTestExpectingError("test38", CompilerError.IllegalOp2Type, 12, 19);
	}
	
	public void test39(){
		runTestExpectingError("test39", CompilerError.SelectorNotArray, 13, 14);
	}
	
	public void test40(){
		runTestExpectingError("test40", CompilerError.ArrayLenNotArray, 13, 10);
	}
	
	public void test41(){
		runTestExpectingError("test41", CompilerError.TypeMismatchAssign, 14, 19);
	}
	
	public void test42(){
		runTestExpectingError("test42", CompilerError.TypeMismatchAssign, 12, 9);
	}
	
	public void test43(){
		runTestExpectingError("test43", CompilerError.TypeMismatchAssign, 23, 7);
	}
	
	public void test44(){
		runTestExpectingError("test44", CompilerError.ArgNotApplicable, 21, 17);
	}
	
	public void test45(){
		runTestExpectingError("test45", CompilerError.SelectorNotArray, 12, 16);
	}
	
	public void test46(){
		runTestExpectingError("test46", CompilerError.ReadonlyAssign, 10, 5);
	}
	
	public void test47(){
		runTestExpectingError("test47", CompilerError.ReadonlyAssign, 11, 5);
	}
	
	public void test48(){
		runTestExpectingError("test48", CompilerError.ReadonlyNotArray, 8, 31);
	}
	
	public void test49(){
		runTestExpectingError("test49", CompilerError.ReadonlyArg, 21, 15);
	}
	
	public void test50(){
		runTestExpectingError("test50", CompilerError.ReadonlyArg, 15, 37);
	}
	
	
	
	
}
