import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Receive implements Protocols {
	public static String StringDecryptor (String key) throws Exception {
		File meh = new File("Input.txt");
		Scanner fileInput = new Scanner(meh);
		int counter = 0;
		while(fileInput.hasNextByte()){
			counter++;
			fileInput.nextLine();
		}
		byte[] quote = new byte[counter];
		fileInput.close();
		Scanner fileInput2 = new Scanner(meh);
		for (int i = 0; i < quote.length; i++) {
			quote[i] = fileInput2.nextByte();
		}
		byte[] keyBytes = Protocols.getKey(key);
		byte[][] v = new byte[quote.length/2][2];
		for (int i = 0; i < v.length; i++) {
			v[i][0] = quote[i*2];
			v[i][1] = quote[i*2+1];
		}
		Protocols.decrypt(v, keyBytes);
		String result = "";
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				result += (char) v[i][j];
			}
		}
		PrintWriter writer = new PrintWriter(meh, "UTF-8");
		for (int i1 = 0; i1 < v.length; i1++) {
			for (int j = 0; j < v[0].length; j++) {
				byte resulty = v[i1][j];
				writer.println(resulty);
			}
		}
		writer.close();
		return result;
	}
}