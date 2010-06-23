package yapl.test.backend;

import java.io.FileOutputStream;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

/**
 * BackendMJ test: printing a string constant.
 * @author Mario Taschwer
 * @version $Id: Test1.java 147 2010-03-30 13:41:46Z mt $
 */
public class Test1
{
    /**
     * Usage: java yapl.test.backend.Test1 object_file
     */
    public static void main(String[] args) throws Exception
    {
        BackendBinSM backend = new BackendMJ();
        backend.enterProc("main", 0, true);
        int addr = backend.allocStringConstant("Hello world!");
        backend.writeString(addr);
        backend.exitProc("main_end");
        backend.writeObjectFile(new FileOutputStream(args[0]));
        System.out.println("wrote object file to " + args[0]);
    }
}
