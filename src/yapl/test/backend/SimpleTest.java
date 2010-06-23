package yapl.test.backend;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

public class SimpleTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		BackendBinSM backend = new BackendMJ();

		backend.loadConst(500);
		backend.loadConst(2);
		backend.mul();
		
        backend.writeObjectFile(new FileOutputStream(args[0]));
        System.out.println("wrote object file to " + args[0]);
	}

}
