package yapl.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

import junit.framework.Assert;
import junit.framework.TestCase;

import yapl.ParseException;
import yapl.Program;
import yapl.yapl;
import yapl.lib.YAPLException;

public class CodeGenTests extends TestCase {
	private static yapl parser = new yapl(new StringReader(Program.PREDEFINED_PROCEDURES));
	
	protected void createParser(String fileName) {
		try {
			parser.clear(String.format("%s.out", fileName));
			parser.ReInit(new StringReader(Program.PREDEFINED_PROCEDURES));
			parser.PredefinedProcedures();
			FileInputStream stream = new FileInputStream("testfiles\\codegen\\" + fileName + ".yapl");
			parser.ReInit(stream);
			parser.Start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} catch (YAPLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	protected void verifyOutput(String fileName){
		try {
			String expected = readFile("testfiles\\codegen\\" + fileName + ".out");
			String workingDir = System.getProperty("user.dir");
			Process exec = Runtime.getRuntime().exec(workingDir + "\\" + fileName + ".exe");
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			String line;
			StringWriter writer = new StringWriter();
			while((line = streamReader.readLine()) != null)
			{
				System.out.println(line);
				writer.append(line + "\n");
			}
			streamReader.close();
			String actual = writer.toString();
			Assert.assertEquals(expected, actual);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}
	
	public void test01() {
		createParser("test01min");
		verifyOutput("test01min");
	}
	
	public void test02() {
		createParser("test02min");
		verifyOutput("test02min");
	}
	
	public void test03() {
		createParser("test03min");
		verifyOutput("test03min");
	}
	
	public void test04() {
		createParser("test04");
		verifyOutput("test04");
	}
	
	public void test05() {
		createParser("test05");
		verifyOutput("test05");
	}
	
	private String readFile(String fileName) {
		StringWriter writer = new StringWriter();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			while(true){
				String content = reader.readLine();
				if (content == null) break;
				writer.append(content + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return writer.toString();
	}
}
