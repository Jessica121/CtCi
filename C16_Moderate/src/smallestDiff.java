import java.util.*;
public class smallestDiff {

	public static void main(String[] args) {
		// http://lintcode.com/en/problem/the-smallest-difference/
	}
	
	 private static int smallestDifference(int[] A, int[] B) {
	        Arrays.sort(A);
	        Arrays.sort(B);
	        int p1 = 0, p2 = 0;
	        int res = Integer.MAX_VALUE;
	        while(p1 < A.length && p2 < B.length) {
	            res = Math.min(res, Math.abs(A[p1] - B[p2]));
	            if(A[p1] > B[p2]) {
	                p2++;
	            } else if(A[p1] < B[p2]) {
	                p1++;
	            } else return 0;
	        }
	        return res;
	    }

}
