package yapl.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import yapl.ParseException;
import yapl.TokenMgrError;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import yapl.lib.YAPLException;
import junit.framework.TestCase;

public class LexerTests extends AbstractParserTestCase {
	protected String getFolder() {
		return "lexer";
	}
	
	public void testTest01() throws ParseException, YAPLException{
		try {
		createParser("test01");
		} catch (ParseException ex) {
			verifyException(ex, 3, 20, 9);
		}
	}
	
	public void testTest02() throws ParseException, YAPLException{
		try {
		createParser("test02"); 
		} catch (TokenMgrError ex) {
			verifyException(ex, 2, 21, 18);
		}
	}
	
	public void testTest03() throws ParseException, YAPLException{
		createParser("test03");
	}
}
