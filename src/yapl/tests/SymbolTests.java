package yapl.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import yapl.ParseException;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SymbolTests extends AbstractParserTestCase {
	
	protected String getFolder() {
		return "symbol";
	}
	
	public void test01() {
		runTestNotExpectingError("test01");
	}
	
	public void test02(){
		runTestExpectingError("test02", CompilerError.IdentNotDecl, 9, 5);
	}
	
	public void test03() {
		runTestExpectingError("test03", CompilerError.IdentNotDecl, 11, 10);
	}
	
	public void test04() {
		runTestExpectingError("test04", CompilerError.IdentNotDecl, 11, 10);
	}
	
	public void test05() {
		runTestExpectingError("test05", CompilerError.IdentNotDecl, 10, 11);
	}
	
	public void test06() {
		runTestExpectingError("test06", CompilerError.IdentNotDecl, 15, 5);
	}
	
	public void test07() {
		runTestExpectingError("test07", CompilerError.IdentNotDecl, 13, 5);
	}
	
	public void test08() {
		runTestExpectingError("test08", CompilerError.SymbolExists, 9, 11);
	}
	
	public void test09() {
		runTestExpectingError("test09", CompilerError.SymbolExists, 9, 8);
	}
	
	public void test10() {
		runTestExpectingError("test10", CompilerError.SymbolExists, 10, 31);
	}
	
	public void test11() {
		runTestExpectingError("test11", CompilerError.SymbolExists, 10, 11);
	}
	
	public void test12() {
		runTestExpectingError("test12", CompilerError.SymbolExists, 12, 11);
	}
	
	public void test13() {
		runTestNotExpectingError("test13");
	}
}
