import java.util.Arrays;

public class LivingPeople {
	private static final int BOTTOM = 1900;
	public static void main(String[] args) {
		int[][] liv = {
				{12,15},{20,90},{10,98},{01,72},{10,98},{23,82},{13,98},{90,98},{83,99},{75,94}
		};
		System.out.println(livingNum(liv));
	}
	
	private static int livingNum(int[][] liv) {
		int[] born = new int[liv.length];
		int[] death = new int[liv.length];
		for(int i = 0; i < liv.length; i++) {
			born[i] = liv[i][0];
			death[i] = liv[i][1];
		}
		Arrays.sort(born);
		Arrays.sort(death);
		int res = 0, max = Integer.MIN_VALUE, year = BOTTOM;
		int b = 0, d = 0;
		while(b < born.length) {
			if(born[b] <= death[d]) {
				res++;
				if(res > max) {
					max = res;
					year = 1900 + born[b];
				}
				b++;
			} else {
				res--;
				d++;
			}
		}
		return year;
	}
	
	// imagine the num in the liv arr are already offset to the start year 1900, if not, substract in index.
	private static final int SCOPE = 202;
	private static int livinLiner(int[][] liv) {
		int[] incre = new int[SCOPE];
		for(int[] l : liv) {
			incre[l[0]]++;
			incre[l[1] + 1]--;
		}
		int res = 0, max = Integer.MIN_VALUE, year = BOTTOM;
		for(int i = 0; i < incre.length; i++) {
			res += incre[i];
			if(res > max) {
				max = res;
				year = 1900 + i; 
			}
		}
		return year;
	}

}
