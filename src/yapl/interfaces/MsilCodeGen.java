package yapl.interfaces;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Locale;

import yapl.*;
import yapl.exceptions.*;
import yapl.interfaces.Attrib;
import yapl.interfaces.CodeGen;
import yapl.interfaces.Symbol;
import yapl.interfaces.YAPLToken;
import yapl.lib.ArrayType;
import yapl.lib.YAPLException;
import yapl.types.BooleanType;
import yapl.types.IntegerType;

public class MsilCodeGen implements CodeGen {

	private OutputStreamWriter writer;
	private final String fileName;

	public MsilCodeGen(String fileName){
		this.fileName = fileName;
		try {
			this.writer = new OutputStreamWriter(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Attrib allocArray(ArrayType arrayType) throws YAPLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void allocVariable(Symbol sym) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Attrib arrayLength(Attrib arr) throws YAPLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void arrayOffset(Attrib arr, Attrib index) throws YAPLException {
		// TODO Auto-generated method stub

	}

	public void assign(Attrib lvalue, Attrib expr, Symbol targetSymbol) throws YAPLException {
		if (!lvalue.getType().isCompatible(expr.getType()))
		{
			throw new TypeMismatchAssignException();
		}
		
		writeln("stloc %s", targetSymbol.getName());
	}

	@Override
	public void branchIfFalse(Attrib condition, String label)
			throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Attrib callProc(Symbol proc, Attrib[] args) throws YAPLException {
		if (proc.getName().equals("writeln"))
		{
			writeln("call void [mscorlib]System.Console::WriteLine()");
		} else if (proc.getName().equals("writeint"))
		{
			writeln("call void [mscorlib]System.Console::Write(int32)");
		} else if (proc.getName().equals("writebool")) {
			writeln("call void [mscorlib]System.Console::Write(bool)");
		}
		return new AttribImpl(proc.getType());
	}

	@Override
	public void callWrite(String string) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterProc(Symbol proc) throws YAPLException {

	}

	@Override
	public Attrib equalOp(Attrib x, Token op, Attrib y)
			throws YAPLException {
		if (x.getType().isCompatible(y.getType())){
			writeln("ceq");
			return new AttribImpl(new BooleanType());
		}
		throw new IllegalEqualOpTypeException(op);
	}

	@Override
	public void exitProc(Symbol proc) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void freeReg(Attrib attr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jump(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadReg(Attrib attr) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public String newLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attrib op1(Token op, Attrib x) throws YAPLException {
		if (op != null)
		{
			if (!(x.getType() instanceof IntegerType))
				throw new IllegalOp1Type(op);
			if (op.image.equals("-"))
				writeln("neg");
		}
		return x;
	}

	@Override
	public Attrib op2(Attrib x, Token op, Attrib y) throws YAPLException {
		if (op.kind == yapl.OR || op.kind == yapl.AND)
		{
			if (!(x.getType() instanceof BooleanType && y.getType() instanceof BooleanType))
				throw new IllegalOp2Type(op);
		}
		if (x.getType().isCompatible(y.getType()))
		{
			if (op.image.equals("+"))
				writeln("add");
			if (op.image.equals("-"))
				writeln("sub");
			if (op.image.equals("*"))
				writeln("mul");
			if (op.image.equals("/"))
				writeln("div");
			if (op.image.equals("And"))
				writeln("and");
			if (op.image.equals("Or"))
				writeln("or");
			if (op.image.equals("%"))
				writeln("rem");
			return x;
		}
		throw new IllegalOp2Type(op);
	}

	@Override
	public Attrib relOp(Attrib x, Token op, Attrib y) throws YAPLException {
		if (x.getType() instanceof IntegerType && y.getType() instanceof IntegerType) {
			if (op.image.equals("<"))
				writeln("clt");
			if (op.image.equals(">"))
				writeln("cgt");
			if (op.image.equals(">=")) {
				writeln("clt");
				pushInt(0);
				writeln("ceq");
			}
			if (op.image.equals("<=")) {
				writeln("cgt");
				pushInt(0);
				writeln("ceq");
			}
			return new AttribImpl(new BooleanType());
		}
		throw new IllegalRelOpTypeException(op);
	}

	@Override
	public void returnFromProc(Symbol proc, Attrib returnVal)
			throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParamOffset(Symbol sym, int pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeArrayDim(int dim, Attrib length) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeLabel(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeLabel(String label, String comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verifyArraySelector(Attrib a, Token t) throws YAPLException {
		if (!(a.getType() instanceof IntegerType))
			throw new BadArraySelectorException(t);
	}
	
	@Override
	public void program(String programName){
		writeln(".assembly %s {}", programName);
		writeln(".method static public void main() cil managed {");
		writeln(".entrypoint");
		writeln(".maxstack 100");
	}
	
	@Override
	public void writeStatement(String text) {
		writeln("ldstr " + text);
		writeln("call void [mscorlib]System.Console::Write(string)");
	}
	
	@Override
	public void endProgram() {
		writeln("ret \n}");
	}
	
	private void write(String s, Object...objects) {
		try {
			writer.write(String.format(s, objects));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeln(String s) {
		try {
			System.out.println(s);			
			writer.write(s + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeln(String s, Object...objects) {
		try {
			System.out.println(s);			
			writer.write(String.format(Locale.GERMAN, s, objects) + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void compile()
	{
		try {
			writer.flush();
			writer.close();
			System.err.println("Compiling");
			Process exec = Runtime.getRuntime().exec("ilasm " + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void pushInt(int value)
	{
		if (value < 9)
			writeln("ldc.i4." + value);
		else
			writeln("ldc.i4.s %s", value);
	}
	
	public void initLocals() {
		writeln(".locals init (");
	}
	private int localCount = 0;
	private ArrayList<String> locals = new ArrayList<String>();
	private ArrayList<Integer> localValue = new ArrayList<Integer>();
	public void addLocal(Symbol symbol){
		if (localCount != 0) write(",");
		localCount++;
		if (symbol.getType() instanceof IntegerType)
			write("int32 ");
		else
			write("bool ");
		locals.add(symbol.getName());
		writeln(symbol.getName());
	}
	
	public void addLocalValue(int value) {
		localValue.add(value);
	}
	
	public void closeLocals() {
		writeln(")");
		for(int i = 0; i < localValue.size(); i++) {
			Integer val = localValue.get(i);
			String local = locals.get(i);
			pushInt(val);
			writeln("stloc %s", local);
		}
	}
	
	public void loadLocal(Symbol symbol)
	{
		writeln("ldloc %s", symbol.getName());
	}

	@Override
	public void assign(Attrib lvalue, Attrib expr) throws YAPLException {
		// TODO Auto-generated method stub
		
	}
}
