import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Transmit implements Protocols {	//Implements Protocols interface.
	public static void main (String[] args) throws Exception {
		Scanner fileInput = new Scanner(new File("Input.txt")); //Takes in String or .txt file for encryption.
		String quote = fileInput.nextLine();
		
		String key = "0000111122223333";	//Sets encryption key.
		byte[] keyBytes = Protocols.getKey(key);
		
		
		byte[][] v = new byte[quote.length()/2][2];
		for (int i = 0; i < v.length; i++) {		//Converts quote into 2D array of byte values.
			v[i][0] = quote.substring(i*2,i*2+1).getBytes()[0];
			v[i][1] = quote.substring(i*2+1,i*2+2).getBytes()[0];
		}
		System.out.println("Original string:");
		System.out.println(quote);		//Prints input string.
		
		System.out.println("Key:");
		System.out.println(key);		//Prints current encryption key.
		System.out.println("Key data");
		System.out.println(Arrays.toString(keyBytes));		//Prints array of key.
		System.out.println();
		
		System.out.println("Original data:");
		print2DArray(v);	//Prints original string's ASCII values per character.
		
		Protocols.encrypt(v, keyBytes); //Encrypts data.
		System.out.println("Encrypted data:");
		print2DArray(v);	//Prints original string data after encryption occurs.
		
		String bleh = "";
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				bleh += (char)v[i][j];
			}
		}
		System.out.println("Encrypted string:");
		System.out.println(bleh);	//WIP
		
		Protocols.decrypt(v, keyBytes); //Decrypts data.
		System.out.println("Decrypted data:");
		print2DArray(v);	//Prints data after decryption.
		
		String result = "";
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				result += (char) v[i][j];
			}
		}
		System.out.println("\nResultant string:");
		System.out.println(result); 	//Prints the resulting String of the decryption.
	}
	public static void print2DArray(byte[][] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.println(Arrays.toString(n[i]));
		}
	}
}