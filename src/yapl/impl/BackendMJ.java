package yapl.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
	}
	private int codeSize = 0;
	private int entryAddress = 0;
	private List<Byte> code = new ArrayList<Byte>();
	private List<Byte> data = new ArrayList<Byte>();
	//private ArrayLst<byte> code = new ArrayList<byte>();
	
	private int currentCodeAddress()
	{
		return code.size();
	}
	
	private int currentDataAddress()
	{
		return data.size();
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
		emitData((byte)value);
	}
	
	@Override
	public void add() {
		emit(Mj.ADD);
	}

	@Override
	public void allocArray() {
		// TODO Auto-generated method stub

	}

	@Override
	public int allocStack(int words) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int allocStaticData(int words) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int allocStringConstant(String string) {
		int startAddress = currentDataAddress();
		for(int i = 0; i < string.length(); i++)
			emitData(string.charAt(i));
		emitData(0);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void assignLabel(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public int boolValue(boolean value) {
		if (value) return 1;
		return 0;
	}

	@Override
	public void branchIf(boolean value, String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callProc(String label) {
		// TODO Auto-generated method stub

	}

	@Override
	public void div() {
		emit(Mj.DIV);
	}

	@Override
	public void enterProc(String label, int nParams, boolean main) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitProc(String label) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void loadArrayElement() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
	public void storeArrayDim(int dim) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeArrayElement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeWord(int addr, boolean staticData) {
		// TODO Auto-generated method stub

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

}
