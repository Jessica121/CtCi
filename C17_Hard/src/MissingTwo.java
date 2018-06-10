
public class MissingTwo {
	/*
	 * give number 0 to N, find the missing two number
	 * 
	 * swap to the correct place, when the val >= arr.length, record the candidate: c1 = val == arr.len, c2 = arr.len + 1
	 * 
	 * */
	
	// O(n) time: n swaps to put things in place in the worst case, O(1) space
	public static void missingTwo(int[] arr) {
		int c1 = arr.length, c2 = arr.length + 1, i = 0;
		while(i < arr.length) {
			if(arr[i] == i) i++;
			else if(arr[i] == arr.length) c1 = i++;
			else if(arr[i] == arr.length + 1) c2 = i++;
			else {
				while(arr[i] != i && arr[i] < arr.length) swap(arr, i, arr[i]);
			}
		}
		System.out.println("Missing num is first: " + c1 + ", second is: " + c2);
	}
	
	private static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

	public static void main(String[] args) {
		int[] arr = {5, 4, 2, 3, 0, 1};
		missingTwo(arr);
	}

}
