public interface Protocols {
	public static void encrypt(long[] v, long[] k) {
		long y = v[0];
		long z = v[1];
		long sum = 0;
		final long delta = 0x9e3779b9;
		int loops = 32;
		while (loops-- > 0) {
			sum += delta;
			y += ((z << 4) + k[0]) ^ (z+sum) ^ ((z >> 5) + k[1]);
			z += ((y << 4) + k[2]) ^ (y+sum) ^ ((y >> 5) + k[3]);
		}
		v[0] = y;
		v[1] = z;
	}
	public static void decrypt(long[] v, long[] k) {		
		long y = v[0];
		long z = v[1];
		long sum;
		final long delta = 0x9e3779b9;
		int loops = 32;
		sum = delta << 5;
		while (loops-- > 0) {
			z -= ((y << 4) + k[2]) ^ (y+sum) ^ ((y >> 5) + k[3]);
			y -= ((z << 4) + k[0]) ^ (z+sum) ^ ((z >> 5) + k[1]);
			sum -= delta;
		}
		v[0] = y;
		v[1] = z;
	}
}