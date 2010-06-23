package yapl.test.backend;

import java.io.FileOutputStream;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

/**
 * BackendMJ test: reference parameters, 1-dimensional arrays.
 * @author Mario Taschwer
 * @version $Id: Test5.java 147 2010-03-30 13:41:46Z mt $
 */
public class Test5
{
    /**
     * Usage: java yapl.test.backend.Test5 object_file
     */
    public static void main(String[] args) throws Exception
    {
        BackendBinSM backend = new BackendMJ();
        // procedure swap(int[] a, int i, int j): swap contents of a[i] and a[j] 
        backend.enterProc("swap", 3, false);
        int addrTmp = backend.allocStack(1);              // local variable tmp
        backend.loadWord(backend.paramOffset(0), false);  // load start address of a
        backend.loadWord(backend.paramOffset(1), false);  // load i
        backend.loadArrayElement();                       // load a[i]
        backend.storeWord(addrTmp, false);                // tmp = a[i]
        backend.loadWord(backend.paramOffset(0), false);  // load start address of a
        backend.loadWord(backend.paramOffset(1), false);  // load i
        backend.loadWord(backend.paramOffset(0), false);  // load start address of a
        backend.loadWord(backend.paramOffset(2), false);  // load j
        backend.loadArrayElement();                       // load a[j]
        backend.storeArrayElement();                      // a[i] = a[j]
        backend.loadWord(backend.paramOffset(0), false);  // load start address of a
        backend.loadWord(backend.paramOffset(2), false);  // load j
        backend.loadWord(addrTmp, false);                 // load tmp
        backend.storeArrayElement();                      // a[j] = tmp
        backend.exitProc("swap_end");

        int addrA = backend.allocStaticData(1);           // allocate global variable a
        int addrNewline = backend.allocStringConstant("\n");
        
        // main program
        backend.enterProc("main", 0, true);
        // allocate 1-dimensional array of length 3
        backend.loadConst(3);
        backend.storeArrayDim(0);
        backend.allocArray();
        backend.storeWord(addrA, true);                   // store array start address in a
        // a[0] = 1
        backend.loadWord(addrA, true);                    // load start address of a
        backend.loadConst(0);                             // load index
        backend.loadConst(1);                             // load element value
        backend.storeArrayElement();
        // a[1] = 2
        backend.loadWord(addrA, true);                    // load start address of a
        backend.loadConst(1);                             // load index
        backend.loadConst(2);                             // load element value
        backend.storeArrayElement();
        // a[2] = 3
        backend.loadWord(addrA, true);                    // load start address of a
        backend.loadConst(2);                             // load index
        backend.loadConst(3);                             // load element value
        backend.storeArrayElement();
        // call swap(a, 1, 2)
        backend.loadWord(addrA, true);                    // load start address of a
        backend.loadConst(1);
        backend.loadConst(2);
        backend.callProc("swap");
        // print a[0]
        backend.loadWord(addrA, true);                    // load start address of a
        backend.loadConst(0);                             // load index
        backend.loadArrayElement();
        backend.writeInteger();
        backend.writeString(addrNewline);
        // print a[1]
        backend.loadWord(addrA, true);                    // load start address of a
        backend.loadConst(1);                             // load index
        backend.loadArrayElement();
        backend.writeInteger();
        backend.writeString(addrNewline);
        // print a[2]
        backend.loadWord(addrA, true);                    // load start address of a
        backend.loadConst(2);                             // load index
        backend.loadArrayElement();
        backend.writeInteger();
        backend.writeString(addrNewline);
        backend.exitProc("main_end");

        backend.writeObjectFile(new FileOutputStream(args[0]));
        System.out.println("wrote object file to " + args[0]);
    }
}
