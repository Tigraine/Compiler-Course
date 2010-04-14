package yapl.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import yapl.ParseException;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SymbolTests extends TestCase {
	
	public void test01() throws YAPLException, ParseException {
		createParser("test01");
	}
	
	public void test02() throws YAPLException, ParseException {
		try {
			createParser("test02");
			Assert.fail();
		} catch (YAPLException ex) {
			verifyException(ex, CompilerError.IdentNotDecl, 9, 5);
		}
	}
	
	public void test03() throws YAPLException, ParseException {
		try {
			createParser("test03");
			Assert.fail();
		} catch (YAPLException ex) {
			verifyException(ex, CompilerError.IdentNotDecl, 11, 10);
		}
	}
	
	public void test04() throws YAPLException, ParseException {
		try {
			createParser("test04");
			Assert.fail();
		} catch (YAPLException ex) {
			verifyException(ex, CompilerError.IdentNotDecl, 11, 10);
		}
	}
	
	public void test05() throws YAPLException, ParseException {
		try {
			createParser("test05");
			Assert.fail();
		} catch (YAPLException ex) {
			verifyException(ex, CompilerError.IdentNotDecl, 10, 11);
		}
	}
	
	private void createParser(String fileName) throws ParseException, YAPLException {
		yapl parser;
		try {
			parser = new yapl(new FileInputStream("testfiles\\symbolcheck\\" + fileName + ".yapl"));
			parser.Start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void verifyException(CompilerError ex, int errorNumber, int line, int column)
	{
		assertEquals(errorNumber, ex.errorNumber());
		assertEquals(line, ex.line());
		assertEquals(column, ex.column());
	}
}
