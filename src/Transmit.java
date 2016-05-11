import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Transmit implements Protocols {
	public static void main (String[] args) throws Exception {
		Scanner fileInput = new Scanner(new File("Input.txt"));
		String quote = fileInput.nextLine();
		
		String key = "0000111122223333";
		long[] keyBytes = Protocols.getKey(key);
		
		
		long[][] v = new long[quote.length()/2][2];
		for (int i = 0; i < v.length; i++) {
			v[i][0] = quote.substring(i*2,i*2+1).getBytes()[0];
			v[i][1] = quote.substring(i*2+1,i*2+2).getBytes()[0];
		}
		System.out.println("Original string:");
		System.out.println(quote);
		
		System.out.println("Key:");
		System.out.println(key);
		System.out.println("Key data");
		System.out.println(Arrays.toString(keyBytes));
		System.out.println();
		
		System.out.println("Original data:");
		print2DArray(v);
		
		Protocols.encrypt(v, keyBytes);
		System.out.println("Encrypted data:");
		print2DArray(v);
		
		String bleh = "";
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				bleh += (char)v[i][j];
			}
		}
		System.out.println("Encrypted string:");
		System.out.println(bleh);
		
		Protocols.decrypt(v, keyBytes);
		System.out.println("Decrypted data:");
		print2DArray(v);
		
		String result = "";
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				result += (char) v[i][j];
			}
		}
		System.out.println("\nResultant string:");
		System.out.println(result);
	}
	public static void print2DArray(long[][] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.println(Arrays.toString(n[i]));
		}
	}
}