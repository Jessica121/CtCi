
public class Intersection {
// Given two lines, find their intersection
// in the form of double[4]  
	// calculate the slops and the equation, solve it
	public static void main(String[] args) {
		double[] p1 = {1,1,1,2};
		double[] p2 = {2,1,2,2};
		System.out.println(intersection(p1, p2));
	}
	
	private static String intersection(double[] p1, double[] p2) {
		double s1 = 0, s2 = 0, k1 = 0, k2 = 0;
		s1 = (p1[1] - p1[3]) / (p1[0] - p1[2]);
		s2 = (p2[1] - p2[3]) / (p2[0] - p2[2]);
		k1 = p1[1] - p1[0] * s1;
		k2 = p2[1] - p2[0] * s2;
//		System.out.println(s1 + " " + s2 + " " + k1 + " " + k2);
		if(precise(s1, s2) && precise(k1, k2)) return "Intersect at range: " + Math.max(p1[0], p2[0]) + " to " + Math.min(p1[3], p2[3]);
		double s = s1 - s2, k = k2 - k1;
		double x = k / s;
		double y = x * s1 + k1;
		if(Double.isNaN(x) || Double.isNaN(y)) return "No intersection";
		return "Intersects at: " + x + ", " + y;
	}
	
	private static boolean precise(double a, double b) {
		return Math.abs(a - b) < Double.MIN_VALUE;
	}

}
