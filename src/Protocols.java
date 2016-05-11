public interface Protocols {
	public static long[] getKey(String key) {
		byte[] keyBytes = key.getBytes();
		long[] output = new long[4];
		for (int i = 0, j = 0; j < 4; j++) {
			output[j] = ((keyBytes[i++] & 0xff)) | ((keyBytes[i++] & 0xff) << 8) | ((keyBytes[i++] & 0xff) << 16) | ((keyBytes[i++] & 0xff) << 24);
		}
		return output;
	}
	public static void encrypt(long[][] v, long[] k) {
		for (int i = 0; i < v.length; i++) {
			long y = v[i][0];
			long z = v[i][1];
			long sum = 0;
			final long delta = 0x9e3779b9;
			int loops = 32;
			while (loops-- > 0) {
				sum += delta;
				y += ((z << 4) + k[0]) ^ (z+sum) ^ ((z >> 5) + k[1]);
				z += ((y << 4) + k[2]) ^ (y+sum) ^ ((y >> 5) + k[3]);
			}
			v[i][0] = y;
			v[i][1] = z;
		}
	}
	public static void decrypt(long[][] v, long[] k) {
		for (int i = 0; i < v.length; i++) {
			long y = v[i][0];
			long z = v[i][1];
			long sum;
			final long delta = 0x9e3779b9;
			int loops = 32;
			sum = delta << 5;
			while (loops-- > 0) {
				z -= ((y << 4) + k[2]) ^ (y+sum) ^ ((y >> 5) + k[3]);
				y -= ((z << 4) + k[0]) ^ (z+sum) ^ ((z >> 5) + k[1]);
				sum -= delta;
			}
			v[i][0] = y;
			v[i][1] = z;
		}
	}
}