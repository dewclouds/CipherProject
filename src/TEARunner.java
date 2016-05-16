import java.util.Scanner;


public class TEARunner {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter command to execute. (Encrypt or decrypt) :: ");
		String response = input.nextLine();
		while (!response.trim().toLowerCase().equals("encrypt") && !response.trim().toLowerCase().equals("decrypt")) {
			System.out.println("\nInvalid command!");
			System.out.print("\nEnter command to execute. (Encrypt or decrypt) :: ");
			response = input.nextLine();
		}
		if (response.trim().toLowerCase().equals("encrypt")) {
			System.out.print("\nEnter string to encrypt :: ");
			String inputString = input.nextLine();
			System.out.print("\nEnter encryption key (At least 16 characters):: ");
			String key = input.nextLine();
			Transmit.StringEncryptor(inputString, key);
			System.out.println("\nString encrypted! Exiting...");
		}
		else if (response.trim().toLowerCase().equals("decrypt")) {
			System.out.print("\nEnter encryption key :: ");
			String key = input.nextLine();
			System.out.println("\nOutput string:");
			System.out.println(Receive.StringDecryptor(key));
		}
	}
}
