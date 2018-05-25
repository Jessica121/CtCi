import java.util.Arrays;

public class Intro_MergeSort {

	// Principle: Merge Two Sorted Array
	// Base case: Merge Two Elements
	// Merging: left half of the array is sorted, right half is also sorted : merge two sorted array from low - middle, middle - high
	// current pointer weave the sorted total array
	// 7.2.5.1.4.2.3
	// low, mid + 1
	
	public static void mergeSort(int[] arr) {
		int[] helperArray = new int[arr.length]; // auxiliary array space O(n)
		merge(arr, helperArray, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void merge(int[] arr, int[] helperArr, int start, int end) {
		if(start >= end) return; // when it is the same element, when calling the right half (middle + 1, end) the start (middles + 1) > end
		int middle = start + (end - start) / 2;
		merge(arr, helperArr, start, middle);
		merge(arr, helperArr, middle + 1, end);
		mergeSortedHalves(arr, helperArr, start, end);
	}
	
	private static void mergeSortedHalves(int[] arr, int[] helperArr, int start, int end) {
		// 7.2.5.1.4.2.3
		// helper 7(s & m).2 (e)
		// array  2.7
		for(int i = start; i <= end; i++) helperArr[i] = arr[i];
		int mid = start + (end - start) / 2, p1 = start, p2 = mid + 1, cur = start; // mid + 1
		while(p1 <= mid && p2 <= end) {
			if(helperArr[p1] < helperArr[p2]) arr[cur++] = helperArr[p1++];
			else arr[cur++] = helperArr[p2++];
		}
		while(p1 <= mid) arr[cur++] = helperArr[p1++];
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 0};
		mergeSort(arr);
	}

}
