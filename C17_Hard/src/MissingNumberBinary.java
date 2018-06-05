import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MissingNumberBinary {

	/*
	 * Array contains 0 - n, one is missing, find the one.
	 * each element is in binary rep, can only use A[i]'s jth bit.
	 * cannot use XOR. 
	 * The array may not be sorted.
	 * 
	 * the characteristic of integers are 0101 from the least significant bit.
	 * and they have the same number of 0s and 1s.
	 * since it's from 0 to n, so when n is even, there are odd numbers in total, n(0) = n(1) + 1
	 * 							  when n is odd, 			even 				, n(0) = n(1)
	 * 				n is even 			| n is odd
	 * 				n(0) = n(1) + 1		| n(0) = n(1)
	 * remove 1 | 	n(0) > n(1)			| n(0) > n(1) 			=> n(0) > n(1)
	 * remove 0 |	n(0) = n(1)			| n(0) < n(1)			=> n(0) <= n(1)
	 * 
	 * can pinpoint the missing jth bit, then move on the the previous bit, using the subset that jth bit is the missing one.
	 * put ending with 0 in jth bit in a zero list, one in one list. choose one to recurse in the next bit.
	 * in the next bit. the logic is the same.
	 * 
	 * the res from next bit's recursive call left shift << by 1, and OR with the missing bit of this time.
	 * 
	 * when the input list is empty, n(0) = n(1) miss a zero.
	 * stop when i == 32. return a 0.
	 * */
	
	public static int missingInt(List<Integer> arr) {
		return missingInt(arr, 0);
	}
	
	private static int missingInt(List<Integer> arr, int index) {
		if(index >= Integer.SIZE) return 0;
		List<Integer> zeroList = new ArrayList<>(arr.size() / 2), oneList = new ArrayList<>(arr.size() / 2);
		for(Integer b : arr) {
			if(((b >> index) & 1) == 1) oneList.add(b);
			else zeroList.add(b);
		}
		// miss a 0
		if(oneList.size() >= zeroList.size()) {
			int res = missingInt(zeroList, index + 1);
			return (res << 1);
		} else { // miss a 1
			int res = missingInt(oneList, index + 1);
			return (res << 1) | 1;
		}
	}
	
	
	public static void main(String[] args) {
		Random rand = new Random(); 
        int missing = rand.nextInt(300000);
        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i < 300000; i++) {
        	if(i != missing) array.add(i);
        }
        System.out.println("The array contains all numbers but one from 0 to " + 300000 + ", except for " + missing);
        
        int result = missingInt(array);
        if (result != missing) {
            System.out.println("Error in the algorithm!\n" + "The missing number is " + missing + ", but the algorithm reported " + result);
        } else {
            System.out.println("The missing number is " + result);
        }

	}

}
