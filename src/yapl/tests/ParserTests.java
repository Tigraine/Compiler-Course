package yapl.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import yapl.ParseException;
import yapl.TokenMgrError;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;
import junit.framework.TestCase;

public class ParserTests extends AbstractParserTestCase {

	protected String getFolder() {
		return "parser";
	}
	
	public void test01() {
		runTestNotExpectingError("test01");
	}
	
	public void test02() {
		runTestExpectingError("test02", CompilerError.Lexical, 22, 10);
	}
	
	public void test03(){
		runTestExpectingError("test03", 3, 8, 15);
	}
	
	public void test04() {
		runTestExpectingError("test04", 3, 8, 19);
	}
	
	public void test05() {
		runTestExpectingError("test05", 3, 10, 12);
	}
	
	public void test06() {
		runTestExpectingError("test06", 3, 8, 10);
	}
	
	public void test07() {
		runTestExpectingError("test07", 3, 8, 6);
	}
	
	public void test08() {
		runTestExpectingError("test08", 3, 8, 3);
	}
	
	public void test09() {
		runTestExpectingError("test09", 3, 8, 16);
	}
	
	public void test10() {
		runTestExpectingError("test10", 3, 29, 1);
	}
	
	public void test11() throws ParseException {
		runTestNotExpectingError("test11");
	}
	
	public void test12() throws ParseException {
		runTestNotExpectingError("test12");
	}
	
	public void test13() {
		runTestExpectingError("test13", 3, 14, 27);
	}
	
	public void test14() {
		runTestExpectingError("test14", 3, 8, 14);
	}
}
