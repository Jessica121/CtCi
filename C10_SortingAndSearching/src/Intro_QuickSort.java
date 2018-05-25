import java.util.Arrays;
import java.util.Random;

public class Intro_QuickSort {
	// 7.2.5.1.4.8.9
	// pick a pivot, sort left and right
	// pick left as the pivot, then left is the pivot + 1, right = end
	// if arr.left > pivot, swap with right, right-- left remains. 	else left++
	// until left > right. then swap the pivot with the left - 1, return left - 1
	
	public static void quickSort(int[] arr) {
		if(arr.length <= 1) return;
		quickSort(arr, 0, arr.length - 1);
	}
	
	public static void quickSort(int[] arr, int start, int end) {
		if(start >= end) return; // no need to sort when less and equal to one element.
//		int pivot = partitionPivotLeft(arr, start, end);
		int pivot = partitionRandomPivot(arr, start, end);
		System.out.println(Arrays.toString(arr) + "\t pivot " + pivot);
		quickSort(arr, start, pivot - 1);
		quickSort(arr, pivot + 1, end);
	}
	
	private static int partitionPivotLeft(int[] arr, int start, int end) {
		int pivot = arr[start], left = start + 1, right = end;
		while(left <= right) {
			while(right >= start + 1 && arr[right] > pivot) right--; // Mind while inside a while loop: bound check. and right bound should be start(pivot) + 1. not start.
			while(left <= end && arr[left] < pivot) left++;
			if(left <= right) swap(arr, left++, right--);
		}
		swap(arr, right, start);
		return right;
	}
	
	private static int partitionRandomPivot(int[] arr, int start, int end) {
		Random r = new Random();
		int offset = r.nextInt(end - start + 1);
		int pivot = arr[start + offset];
		int left = start, right = end;
		while(left <= right) {
			while(left <= end && arr[left] < pivot) left++;
			while(right >= start && arr[right] > pivot) right--;
			if(left <= right) {
				swap(arr, left, right);
				left++;
				right--;
			}
		}
		swap(arr, start + offset, right); // swap with the pivot
		return right;
	}
	
	private static void swap(int[] arr, int left, int right) {
		int t = arr[left];
		arr[left] = arr[right];
		arr[right] = t;
	}
	
	public static void main(String[] args) {
		int[] arr = {2,2,3,3,4,3,4,6,5};
		quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
