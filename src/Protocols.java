public interface Protocols {
	public static byte[] getKey(String key) {
		byte[] keyBytes = key.getBytes();
		byte[] output = new byte[4];
		for (int i = 0, j = 0; j < 4; j++) {
			output[j] = (byte) (((keyBytes[i++] & 0xff)) | ((keyBytes[i++] & 0xff) << 8) | ((keyBytes[i++] & 0xff) << 16) | ((keyBytes[i++] & 0xff) << 24));
		}
		return output;
	}
	public static void encrypt(byte[][] v, byte[] k) {	//Encryption method.
		for (int i = 0; i < v.length; i++) {
			byte y = v[i][0];
			byte z = v[i][1];
			long sum = 0;
			final long delta = 0x9e3779b9;
			int loops = 32;
			while (loops-- > 0) {
				sum += delta;
				y += ((z << 4) + k[0]) ^ (z+sum) ^ ((z >> 5) + k[1]);	//Adds the following to 'y': Binary shifts 'z' by 4 to the left, adds k[0], binary or 'z' plus sum, binary or 'z' shifted by 5 to the right, and adds k[1].
				z += ((y << 4) + k[2]) ^ (y+sum) ^ ((y >> 5) + k[3]);	//Adds the following to 'z': Binary shifts 'y' by 4 to the left, adds k[2], binary or 'y' plus sum, binary or 'y' shifted by 5 to the right, and adds k[3].
			}
			v[i][0] = y;
			v[i][1] = z;
		}
	}
	public static void decrypt(byte[][] v, byte[] k) {	//Decryption method.
		for (int i = 0; i < v.length; i++) {
			byte y = v[i][0];
			byte z = v[i][1];
			long sum;
			final long delta = 0x9e3779b9;
			int loops = 32;
			sum = delta << 5;
			while (loops-- > 0) {
				z -= ((y << 4) + k[2]) ^ (y+sum) ^ ((y >> 5) + k[3]);	//Subtracts the above encryption from 'z'.
				y -= ((z << 4) + k[0]) ^ (z+sum) ^ ((z >> 5) + k[1]);	//Subtracts the above encryption from 'y'.
				sum -= delta;
			}
			v[i][0] = y;
			v[i][1] = z;
		}
	}
}