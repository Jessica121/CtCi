
public class MagicIndex {

	// given a sorted array, find the index where arr[index] = index
	public static void main(String[] args) {
		int[] arr = {-10,-5,2,2,2,3,4,7,9,12,13};
		System.out.println(magicWithDup(arr, 0, 10));
	}
	
	/*
	 * No-dup version, iterative.
	 * */
	public static int magicIndex(int[] arr) {
		int start = 0, end = arr.length - 1, mid = -1;
		while(start < end) {
			mid = start + (end - start) / 2;
			if(arr[mid] == mid) return mid;
			else if(arr[mid] > mid) end = mid - 1;
			else start = mid + 1;
		}
		return -1;
	}
	
	/*
	 * No-dup version, recursive.
	 * */
	public static int magicRecursion(int[] arr, int s, int e) {
		if(s > e) return -1;
		int mid = s + (e - s) / 2;
		if(arr[mid] == mid) return mid;
		else if(arr[mid] > mid) return magicRecursion(arr, s, mid - 1);
		else return magicRecursion(arr, mid + 1, e);
	}
	
	/*
	 * Dup version, recursive.
	 * */
	public static int magicWithDup(int[] arr, int s, int e) {
		if(s > e) return -1;
		int mid = s + (e - s) / 2;
		if(arr[mid] == mid) return mid;
		int left = magicWithDup(arr, s, Math.min(arr[mid], mid - 1));
		if(left != -1) return left;
		int right = magicWithDup(arr, Math.max(arr[mid], mid + 1), e);
		if(right != -1) return right;
		return -1;
	}
}
