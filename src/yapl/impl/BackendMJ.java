package yapl.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import yapl.interfaces.BackendBinSM;

public class BackendMJ implements BackendBinSM {
	private class Mj {
		static final byte PRINT = (byte)51;
		static final byte RETURN = (byte)47;
		static final byte ADD = (byte)23;
		static final byte SUB = (byte)24;
		static final byte MUL = (byte)25;
		static final byte DIV = (byte)26;
		static final byte REM = (byte)27;
		static final byte NEG = (byte)28;
		static final byte CONST = (byte)22;
		static final byte JEQ = (byte)40;
		static final byte JUMP = (byte)39;
		static final byte JNE = (byte)41;
		static final byte JLT = (byte)42;
		static final byte JLE = (byte)43;
		static final byte JGT = (byte)44;
		static final byte JGE = (byte)45;
		static final byte SPRINT = (byte)55;
		static final byte GETSTATIC = (byte)11;
		static final byte PUTSTATIC = (byte)12;
		static final byte LOAD = (byte)1;
		static final byte STORE = (byte)6;
		static final byte EXIT = (byte)49;
		static final byte TRAP = (byte)55;
		static final byte ENTER = (byte)48;
		static final byte CALL = (byte)46;
		static final byte POP = (byte)38;
		static final byte NEWARRAY = (byte)32;
		static final byte ARRAYLEN = (byte)37;
		static final byte ALOAD = (byte)33;
		static final byte ASTORE = (byte)34;
	}
	private int codeSize = 0;
	private int entryAddress = 0;
	private List<Byte> code = new ArrayList<Byte>();
	private List<Byte> data = new ArrayList<Byte>();
	//private ArrayLst<byte> code = new ArrayList<byte>();
	
	private Hashtable<String, List<Integer>> backpatchList = new Hashtable<String, List<Integer>>();
	
	public BackendMJ()
	{
		//Initialize Built-In Methods
		
		//writeln
		enterProc("writeln", 0, false);
		int addr = allocStringConstant("\n");
		writeString(addr);
		exitProc("writeln");
		
		//writeint
		enterProc("writeint", 1, false);
		loadWord(0, false);
		writeInteger();
		exitProc("writeint");
		
		//writebool
		enterProc("writebool", 1, false);
		loadWord(0, false);
		int a = allocStringConstant("True");
		int b = allocStringConstant("False");
		branchIf(true, "writebool_true");
		writeString(b);
		exitProc("writebool");
		assignLabel("writebool_true");
		writeString(a);
		exitProc("writebool");
		
		arrayTemp = allocStaticData(1);
	}
	
	private int currentCodeAddress()
	{
		return code.size();
	}
	
	private int currentDataAddress()
	{
		return data.size() / 4;
	}
	
	private void emit(byte value)
	{
		code.add(value);
	}
	private void emit(int value)
	{
		emit((byte)value);
	}
	
	
	private void emit4(int value)
	{
		byte[] byteArray = intToByteArray(value);
		for(int i = 0; i < 4; i++)
			emit(byteArray[i]);
	}
	
	private void emit2(int value)
	{
		byte base = (byte) 0xFF;
		emit((base << 8) & value);
		emit(base & value);
	}
	
	private void emitData(byte value)
	{
		data.add(value);
	}
	private void emitData(int value)
	{
		byte[] arr = intToByteArray(value);
		for(byte b : arr)
		{
			emitData(b);
		}
	}
	
	@Override
	public void add() {
		emit(Mj.ADD);
	}


	@Override
	public void storeArrayDim(int dim) {
		//loadConst(dim);
		//mul();
		storeWord(arrayTemp, true);
	}
	
	@Override
	public void allocArray() {
		loadWord(arrayTemp, true);
		emit(Mj.NEWARRAY);
		emit(1);
	}	

	@Override
	public int allocStack(int words) {
		//TODO: This is hackish and will lead to bugs
		int addr = nextFreeLocalWord;
		nextFreeLocalWord += words;
		return addr;
	}

	@Override
	public int allocStaticData(int words) {
		int address = currentDataAddress();
		emitData(0);
		return address;
	}

	@Override
	public int allocStringConstant(String string) {
		int startAddress = currentDataAddress();
		for(int i = 0; i < string.length(); i++)
			data.add((byte)string.charAt(i));
		data.add((byte)0);
		while (data.size() % 4 > 0)
			data.add((byte)0);
		return startAddress;
	}
	
	@Override
	public void and() {
		isEqual();
	}
	

	@Override
	public void or() {
		emit(Mj.ADD);
		loadConst(0);
		emit(Mj.JNE);
		emitOperatorJump();
	}

	@Override
	public void arrayLength() {
		emit(Mj.ARRAYLEN);
	}

	private Hashtable<String, Integer> labels = new Hashtable<String, Integer>();
	
	@Override
	public void assignLabel(String label) {
		int currentAddr = currentCodeAddress();
		if (backpatchList.containsKey(label))
		{
			for(int address : backpatchList.get(label))
			{
				byte base = (byte) 0xFF;
				code.set(address, (byte)((base << 8) & currentCodeAddress()));
				code.set(address +1, (byte)(base & currentCodeAddress()));				
			}
		}
		
		labels.put(label, currentAddr);
	}
	
	private void addBackpatch(String label, int address)
	{
		if (!backpatchList.containsKey(label))
			backpatchList.put(label, new ArrayList<Integer>());
		backpatchList.get(label).add(address);
	}
	
	private int getLabelAddress(String label)
	{
		if (!labels.containsKey(label))
		{
			addBackpatch(label, currentCodeAddress());
			return 0;
		}
		return labels.get(label);
	}

	@Override
	public int boolValue(boolean value) {
		if (value) return 1;
		return 0;
	}

	@Override
	public void branchIf(boolean value, String label) {
		loadConst(boolValue(value));
		emit(Mj.JEQ);
		emit2(getLabelAddress(label));
	}

	@Override
	public void callProc(String label) {
		int address = getLabelAddress(label);
		emit(Mj.CALL);
		emit2(address);
	}

	@Override
	public void div() {
		emit(Mj.DIV);
	}

	private int nextFreeLocalWord = 0;
	private int arrayTemp;
	
	@Override
	public void enterProc(String label, int nParams, boolean main) {
		assignLabel(label);
		emit(Mj.ENTER);
		emit(nParams);
		emit(nParams +20);
		//TODO: This is hackish and will lead to bugs
		nextFreeLocalWord = nParams;
		
		/*for(int i = 0; i < nParams; i++)
		{
			storeWord(i*wordSize(), false);
		}*/
		if (main){
			entryAddress = getLabelAddress(label);
		}
	}

	@Override
	public void exitProc(String label) {
		assignLabel(label + "_end");
		emit(Mj.EXIT);
		emit(Mj.RETURN);
		//TODO: This is hackish and will lead to bugs
		nextFreeLocalWord = 0;
	}

	@Override
	public void isEqual() {
		emit(Mj.JEQ);   //1 Byte
		emitOperatorJump();
	}

	@Override
	public void isGreater() {
		emit(Mj.JGT);
		emitOperatorJump();
	}
	private void emitOperatorJump() {
		emit2(currentCodeAddress() + 6);
		loadConst(0);
		emit(Mj.JUMP);
		emit2(currentCodeAddress() + 3);
		loadConst(1);
	}

	@Override
	public void isGreaterOrEqual() {
		emit(Mj.JGE);
		emitOperatorJump();
	}

	@Override
	public void isLess() {
		emit(Mj.JLT);
		emitOperatorJump();
	}

	@Override
	public void isLessOrEqual() {
		emit(Mj.JLE);
		emitOperatorJump();
	}

	@Override
	public void jump(String label) {
		emit(Mj.JUMP);
		emit2(getLabelAddress(label));
	}

	@Override
	public void loadArrayElement() {
		emit(Mj.ALOAD);
	}

	@Override
	public void loadConst(int value) {
		if (value < 6 && value >= 0)
		{
			emit(15 + value);
		}
		else if (value == -1)
			emit(21);
		else
		{
			emit(Mj.CONST);
			emit4(value);
		}
		
	}

	@Override
	public void loadWord(int addr, boolean staticData) {
		if(!staticData){
			emit(Mj.LOAD);
			emit(addr);
		}
		else
		{
			emit(Mj.GETSTATIC);
			emit2(addr);
		}
	}
	

	@Override
	public void storeWord(int addr, boolean staticData) {
		if(!staticData)
		{
			emit(Mj.STORE);
			emit(addr);
		}
		else
		{
			emit(Mj.PUTSTATIC);
			emit2(addr);
		}
	}
	
	@Override 
	public void storeStaticConst(int addr, int value)
	{
		byte[] bytes = intToByteArray(value);
		int i = 0;
		for(byte b : bytes)
		{
			data.set((addr * 4) + i, b);
			i++;
		}
	}

	@Override
	public void mod() {
		emit(Mj.REM);
	}

	@Override
	public void mul() {
		emit(Mj.MUL);
	}

	@Override
	public void neg() {
		emit(Mj.NEG);
	}

	@Override
	public int paramOffset(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void storeArrayElement() {
		emit(Mj.ASTORE);
	}

	@Override
	public void sub() {
		emit(Mj.SUB);
	}

	@Override
	public int wordSize() {
		return 4;
	}

	@Override
	public void writeInteger() {
		loadConst(0);
		emit(Mj.PRINT);
	}

	private static byte[] intToByteArray(int value) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((value >>> offset) & 0xFF);
        }
        return b;
    }
	
	@Override
	public void writeObjectFile(OutputStream outStream) throws IOException {
			
		emit(Mj.RETURN);
		outStream.write('M');
		outStream.write('J');
		outStream.write(intToByteArray(code.size()));
		outStream.write(intToByteArray(data.size() / wordSize()));
		outStream.write(intToByteArray(entryAddress));
		for(Byte b : code){
			outStream.write(b);
		}
		for(Byte b : data){
			outStream.write(b);
		}
		outStream.flush();
	}

	@Override
	public void writeString(int addr) {
		emit(Mj.SPRINT);
		emit2(addr);
	}
	
	@Override
	public void isNotEqual() {
		isEqual();
		loadConst(0);
		and();
	}

	@Override
	public void removeOperand() {
		emit(Mj.POP);
	}

}
