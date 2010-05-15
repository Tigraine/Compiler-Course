package yapl;

import yapl.exceptions.*;
import yapl.interfaces.Attrib;
import yapl.interfaces.CodeGen;
import yapl.interfaces.Symbol;
import yapl.interfaces.YAPLToken;
import yapl.lib.ArrayType;
import yapl.lib.YAPLException;
import yapl.types.BooleanType;
import yapl.types.IntegerType;

public class CodeGenImpl implements CodeGen {

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

	@Override
	public void assign(Attrib lvalue, Attrib expr) throws YAPLException {
		if (!lvalue.getType().isCompatible(expr.getType()))
		{
			throw new TypeMismatchAssignException();
		}
	}

	@Override
	public void branchIfFalse(Attrib condition, String label)
			throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Attrib callProc(Symbol proc, Attrib[] args) throws YAPLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void callWrite(String string) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterProc(Symbol proc) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Attrib equalOp(Attrib x, Token op, Attrib y)
			throws YAPLException {
		if (x.getType().isCompatible(y.getType()))
			return new AttribImpl(new BooleanType());
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
			return x;
		throw new IllegalOp2Type(op);
	}

	@Override
	public Attrib relOp(Attrib x, Token op, Attrib y) throws YAPLException {
		if (x.getType() instanceof IntegerType && y.getType() instanceof IntegerType) {
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

}
