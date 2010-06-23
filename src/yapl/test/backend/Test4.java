package yapl.test.backend;

import java.io.FileOutputStream;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

/**
 * BackendMJ test: functions, variables, arithmetic.
 * @author Mario Taschwer
 * @version $Id: Test4.java 147 2010-03-30 13:41:46Z mt $
 */
public class Test4
{
    /**
     * Usage: java yapl.test.backend.Test4 object_file
     */
    public static void main(String[] args) throws Exception
    {
        BackendBinSM backend = new BackendMJ();
        int global = backend.allocStaticData(1);          // global variable
        int addrNewline = backend.allocStringConstant("\n");
        
        // procedure int func(int x): returns global - x*x
        backend.enterProc("func", 1, false);
        backend.loadWord(global, true);                   // load global variable
        backend.loadWord(backend.paramOffset(0), false);  // load parameter 0 from stack frame
        backend.loadWord(backend.paramOffset(0), false);  // load parameter 0 from stack frame
        backend.mul();
        backend.sub();
        backend.exitProc("func_end");                     // return value on expression stack
        
        // main program
        backend.enterProc("main", 0, true);
        int local = backend.allocStack(1);                // local variable (declared in a block)
        backend.loadConst(17);
        backend.storeWord(global, true);                  // global = 17
        backend.loadConst(3);
        backend.callProc("func");                         // func(3)
        backend.storeWord(local, false);                  // store result
        backend.loadWord(local, false);
        backend.writeInteger();                           // print result: 8
        backend.writeString(addrNewline);
        backend.loadWord(local, false);
        backend.callProc("func");                         // func(8)
        backend.writeInteger();                           // print result: -47
        backend.writeString(addrNewline);
        backend.exitProc("main_end");
        
        backend.writeObjectFile(new FileOutputStream(args[0]));
        System.out.println("wrote object file to " + args[0]);
    }
}
