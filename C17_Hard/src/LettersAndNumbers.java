import java.util.HashMap;
import java.util.Map;

public class LettersAndNumbers {
	
	/*
	 * In an array with letters and numbers, find the longest subarray that has same cnt of numbers and letters.
	 * 					A B C|2 F E 4|W F 5 R D H
	 * 					0 0 0|1 0 0 1|0 0 1 0 0 0
	 * num of letters   1 2 3|3 4 5 5|6 7 7 8 9 10
	 * num of numbers   0 0 0|1 1 1 2|2 2 3 3 3 3
	 * diff				1 2 3|2 3 4 3|4 5 4 5 6 7
	 * 
	 * when contains the subarray, the difference of two ends are the same. start is the offset.
	 * 1. diff array 2. hashmap
	 * 
	 * two pointers will work, but it's tricky as to when to shrink the first pointer.
	 *  
	 * */

	public static int findLongestSubarray(char[] arr) {
		int cntChar = 0, cntDigit = 0, res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < arr.length; i++) {
			if(Character.isDigit(arr[i])) cntDigit++;
			else cntChar++;
			int diff = cntChar - cntDigit;
			if(!map.containsKey(diff)) map.put(diff, i);
			else res = Math.max(i - map.get(diff), res);
		}
		return res;
	}
	public static void main(String[] args) {
		char[] array = {'A', 'B' ,'C', '2', 'F', 'E', '4', 'W', 'F', '5', 'R', 'D', 'H'};
		System.out.println(findLongestSubarray(array));
	}

}
