package yapl.test.backend;

import java.io.FileOutputStream;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

/**
 * BackendMJ test: printing an integer, procedure call.
 * @author Mario Taschwer
 * @version $Id: Test2.java 147 2010-03-30 13:41:46Z mt $
 */
public class Test2
{
    /**
     * Usage: java yapl.test.backend.Test2 object_file
     */
    public static void main(String[] args) throws Exception
    {
        BackendBinSM backend = new BackendMJ();
        // procedure writeint(int i): print integer
        backend.enterProc("writeint", 1, false);
        backend.loadWord(backend.paramOffset(0), false);  // load parameter 0 from stack frame
        backend.writeInteger();
        backend.exitProc("writeint_end");
        // main program
        backend.enterProc("main", 0, true);
        // call writeint(7)
        backend.loadConst(7);
        backend.callProc("writeint");
        backend.exitProc("main_end");
        
        backend.writeObjectFile(new FileOutputStream(args[0]));
        System.out.println("wrote object file to " + args[0]);
    }
}
