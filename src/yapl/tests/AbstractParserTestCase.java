package yapl.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;

import yapl.ParseException;
import yapl.Program;
import yapl.TokenMgrError;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;
import junit.framework.Assert;
import junit.framework.TestCase;

public abstract class AbstractParserTestCase  extends TestCase{

	private static yapl parser = new yapl(new StringReader(Program.PREDEFINED_PROCEDURES));
	protected abstract String getFolder();
	
	protected void createParser(String fileName) throws ParseException, YAPLException {
		try {
			parser.clear();
			parser.ReInit(new StringReader(Program.PREDEFINED_PROCEDURES));
			parser.PredefinedProcedures();
			FileInputStream stream = new FileInputStream("testfiles\\" + getFolder() + "\\" + fileName + ".yapl");
			parser.ReInit(stream);
			parser.Start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void runTestExpectingError(String fileName, int errorNumber, int line, int column) 
	{
		try {
			createParser(fileName);
			Assert.fail();
		} catch (YAPLException ex) {
			verifyException(ex, errorNumber, line, column);
		} catch (ParseException ex) {
			verifyException(ex, errorNumber, line, column);
		} catch (TokenMgrError ex) {
			verifyException(ex, errorNumber, line, column);
		}
	}
	
	protected void runTestNotExpectingError(String fileName)
	{
		try {
			createParser(fileName);
		} catch (ParseException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (YAPLException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (TokenMgrError e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	protected void verifyException(CompilerError ex, int errorNumber, int line, int column)
	{
		assertEquals(errorNumber, ex.errorNumber());
		assertEquals(line, ex.line());
		assertEquals(column, ex.column());
	}
}
