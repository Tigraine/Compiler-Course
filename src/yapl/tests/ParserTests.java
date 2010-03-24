package yapl.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import yapl.ParseException;
import yapl.TokenMgrError;
import yapl.yapl;
import yapl.interfaces.CompilerError;
import junit.framework.TestCase;

public class ParserTests extends TestCase {

	public void test01() throws ParseException{
		createParser("test01");
	}
	
	public void test02() throws ParseException {
		try {
		createParser("test02");
		} catch (TokenMgrError ex)
		{
			//2 (line 22, column 10)
			verifyException(ex, 2, 22, 10);
		}
	}
	
	public void test03(){
		try {
			createParser("test03");
		} catch (ParseException ex) {
			//ERROR 3 (line 8, column 15)
			verifyException(ex, 3, 8, 15);
		}
	}
	
	public void test04() {
		try {
			createParser("test04");
		} catch (ParseException ex) {
			// ERROR 3 (line 8, column 19)
			verifyException(ex, 3, 8, 19);
		}
	}
	
	public void test05() {
		try {
			createParser("test05");
		} catch (ParseException ex) {
			// ERROR 3 (line 10, column 12)
			verifyException(ex, 3, 10, 12);
		}
	}
	
	public void test06() {
		try {
			createParser("test06");
		} catch (ParseException e) {
			// ERROR 3 (line 8, column 10)
			verifyException(e, 3, 8, 10);
		}
	}
	
	public void test07() {
		try {
			createParser("test07");
		} catch (ParseException e) {
			// ERROR 3 (line 8, column 6)
			verifyException(e, 3, 8, 6);
		}
	}
	
	public void test08() {
		try {
			createParser("test08");
		} catch (ParseException e) {
			// ERROR 3 (line 8, column 3)
			verifyException(e, 3, 8, 3);
		}
	}
	
	public void test09() {
		try {
			createParser("test09");
		} catch (ParseException e) {
			// ERROR 3 (line 8, column 16)
			verifyException(e, 3, 8, 16);
		}
	}
	
	public void test10() {
		try {
			createParser("test10");
		} catch (ParseException e)
		{
			// ERROR 3 (line 29, column 1)
			verifyException(e, 3, 29, 1);
		}
	}
	
	public void test11() throws ParseException {
		createParser("test11");
	}
	
	public void test12() throws ParseException {
		createParser("test12");
	}
	
	public void test13() {
		try {
			createParser("test13");
		} catch (ParseException e) {
			// ERROR 3 (line 14, column 27)
			verifyException(e, 3, 14, 27);
		}
	}
	
	public void test14() {
		try {
			createParser("test14");
		} catch (ParseException e) {
			// ERROR 3 (line 8, column 14)
			verifyException(e, 3, 8, 14);
		}
	}
	
	private void createParser(String fileName) throws ParseException {
		yapl parser;
		try {
			parser = new yapl(new FileInputStream("testfiles\\parser\\" + fileName + ".yapl"));
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
