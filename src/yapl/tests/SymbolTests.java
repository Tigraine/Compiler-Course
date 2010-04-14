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
}
