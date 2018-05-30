
public class SparseSearch {
	
	/* 
	 * search for an array of strings which is sparse, and contains a lot of empty strings. 
	 * non-empty strings are sorted.
	 * return the index.
	 */
	
	// if the mid is empty, fins the left and right non-empty, compare to the target, decided which way to go
	
	public static int searchSparse(String target, String[] arr) {
		int lo = 0, hi = arr.length - 1, mid = -1;
		while(lo <= hi) { // equal sign for the only element in array condition.
			mid = lo + (hi - lo) / 2;
			if(arr[mid].equals(target)) return mid;
			else if(!arr[mid].isEmpty()) {
				if(arr[mid].compareTo(target) > 0) hi = mid - 1;
				else lo = mid + 1;
			} else {
				int left = mid, right = mid;
				while(left >= 0 && arr[left].isEmpty()) left--;
				while(right < arr.length && arr[right].isEmpty()) right++;
				// while loop inside of the while looop! condition of the inner while loop, check in bounds. 
				// outside the inner while loop, check the pointers inbound before use them.
				if(left < 0 || right >= arr.length) return -1;
				if(arr[left].equals(target)) return left;
				else if(arr[right].equals(target)) return right;
				else if(arr[left].compareTo(target) > 0) {
					hi = left - 1;
				} else if(arr[right].compareTo(target) < 0) {
					lo = right + 1;
				} else return -1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
//		String[] arr = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
//		String[] arr = {"apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower"};
		String[] arr = {"asf"};
		System.out.println(searchSparse("d", arr));
	}

}
