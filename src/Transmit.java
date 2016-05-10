import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Transmit implements Protocols {
	public static void main (String[] args) throws Exception {
		Scanner fileInput = new Scanner(new File("Input.txt"));
		String stringInput = fileInput.nextLine();
		
		long[] v = {16,18};
		long[] k = {4,4,4,4};
		
		System.out.println(Arrays.toString(v));
		System.out.println(Arrays.toString(k));
		
		Protocols.encrypt(v, k);
		
		System.out.println(Arrays.toString(v));
		System.out.println(Arrays.toString(k));
		
		Protocols.decrypt(v, k);
		
		System.out.println(Arrays.toString(v));
		System.out.println(Arrays.toString(k));
		//byte[] result = Protocols.decrypt(crypt);
		
		//System.out.println("Original: \t"+Arrays.toString(original));
		//System.out.println("Crypt: \t\t"+Arrays.toString(crypt));
		//System.out.println("Result: \t"+Arrays.toString(result));
	}
}