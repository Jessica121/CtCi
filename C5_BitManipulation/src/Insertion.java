
public class Insertion {

	/*
	 * 32bit num, M and N, insert M into N (replace) from index j thru i.
	 * where i - j >= M.length
	 * 
	 * 
	 * e.g. N = 10001111100
	 * 		M = 10011
	 * 		i = 2, j = 6
	 * 		res = 10001001100
	 * 
	 * 	first clear the j to i in N by creating 00001111100 : ~0 shift j + 1 bits, and a ~0 right shift 31 - i + 1 bits, or create all 1s in the right, create a 1 with one more bit, and - 1, 1 << i - 1
	 * 	shift the M by i bits.
	 * 	OR the two results.
	 * */
	public static int insertBits(int n, int m, int i, int j) {
		int allOnes = ~0;
		int left = allOnes << (j + 1), right = (1 << i) - 1, mask = left | right; // should be or to concat them!
		n &= mask;
		m <<= 2;
		n |= m;
		return n;
	}
	
	
	public static void main(String[] args) {
		System.out.println(insertBits(1148, 19, 2, 6));
	}

}
