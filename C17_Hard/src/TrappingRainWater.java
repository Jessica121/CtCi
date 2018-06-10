
public class TrappingRainWater {

    public int trap(int[] height) {
        /*
        for each element, calculate wholly of the rectangle formed by the highest building
        so each element are not determined by immediate higher ones but highest on the left and right, respectly
        calculate directly the final result, later ones does not rely on previous results.
        for each element, get left max and right max, get min of the two and substract with itself.
        if it self is one or both of the max, it's 0
        */
        if(height == null || height.length == 0) return 0;
        int[] left = new int[height.length], right = new int[height.length];
        int res = 0;
        maxFromLeft(left, height);
        maxFromRight(right, height);
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        for(int i = 0; i < height.length; i++) {
            int hei = Math.min(height[left[i]], height[right[i]]);
            res += hei - height[i];
        }
        return res;
    }
    
    private void maxFromLeft(int[] left, int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            left[i] = arr[i] >= arr[left[i - 1]] ? i : left[i - 1];
        }
    }
    
    private void maxFromRight(int[] right, int[] arr) {
        right[arr.length - 1] = arr.length - 1;
        for(int i = arr.length - 2; i >= 0; i--) {
            right[i] = arr[i] >= arr[right[i + 1]] ? i : right[i + 1];
        }
    }
    
	public int trapTwoPointers(int[] arr) {
        /*
        two pointers, calculate and advance the smaller pointer. if has a tie, advance first one.
        the smaller pointer will be bounded by its prev max and right max. althou the right may not be the biggest since the small pointer, it is sufficient to calculate as it is not bounded by bigger one anyways.
        if later ones smaller, can be bounded by right pointer. if larger, can also be bounded by right
        if the chosen pointer is larger than prev max, update and advance.
        add the result.
        if it is larger than prev maxes, update itself and advance.
        if has a tie, calculate the left and advance.
        
        */
        int left = 0, right = arr.length - 1, res = 0, leftMax = 0, rightMax = 0;
        while(left < right) {
            if(left < right) {
                if(arr[left] <= arr[right]) {
                    if(arr[left] >= leftMax) leftMax = arr[left++];
                    else res += leftMax - arr[left++];
                } else {
                    if(arr[right] >= rightMax) rightMax = arr[right--];
                    else res += rightMax - arr[right--];
                }
            }
        }
        return res;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
