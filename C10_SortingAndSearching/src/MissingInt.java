import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
public class MissingInt {

	/*
	 * input file with 4 billion non-negative integers, the memory is 1GB, find the missing integer.
	 * 
	 * (num of int) / 8 elements in the byte array, and for every 8 ints map into one slot in the array, and each int within uses one bit of 8 bits in 1 byte.
	 * (4 billion = 4 * 10^9 ints -> map into 1/2 * 10 ^ 9 bytes)
	 * (1 GB = 10 ^ 9 * 8! bits = 10 ^ 9 byte.)
	 * 
	 * */
	public static final String path = "/Users/Jessica/Desktop/CTCI答案/答案W:Java/Ch 10. Sorting and Searching/Q10_07_Missing_Int/input.txt";
	public static int missingInt() throws FileNotFoundException {
		// Integer.MAX_VALUE == 2 ^ 31 = 2 * 10 ^ 9
		long numOfInts = ((long) Integer.MAX_VALUE) + 1; // 1 is for 0.
		int lenOfArray = (int) (numOfInts / 8);  // 4 * Math.pow(10, 9)
		System.out.println("len of array is " + numOfInts + ", " + lenOfArray);
		byte[] arr = new byte[lenOfArray];
		Scanner in = new Scanner(new FileReader(path));
		while(in.hasNextInt()) {
			int n = in.nextInt();
			arr[n / 8] |= 1 << (n % 8);
		}
		
		for(int j = 0; j < arr.length; j++) {
			for(int i = 0; i < 8; i++) {
				if(((arr[j] >> i) & 1) == 0) return j * 8 + i; // mind this is the index j * 8 noy the value.
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(missingInt());
	}
}
