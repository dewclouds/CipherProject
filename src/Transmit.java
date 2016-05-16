import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Transmit implements Protocols {
	public static void  StringEncryptor(String quote, String key) throws Exception {
		File meh = new File("Input.txt");								//Output file
		byte[] keyBytes = Protocols.getKey(key);						//Convert key to key byte array
		byte[][] v = new byte[quote.length()/2][2];						//Instantiate 2D array to load in byte values
		for (int i = 0; i < v.length; i++) {							//Converts quote into 2D array of byte values.
			v[i][0] = quote.substring(i*2,i*2+1).getBytes()[0];
			v[i][1] = quote.substring(i*2+1,i*2+2).getBytes()[0];
		}
		Protocols.encrypt(v, keyBytes);									//Take key byte array and quote byte array and encrypt
		PrintWriter writer = new PrintWriter(meh, "UTF-8");
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {						//Unload encrypted data into file
				byte resulty = v[i][j];
				writer.println(resulty);								//Output to file
			}
		}
		writer.close();
	}
}