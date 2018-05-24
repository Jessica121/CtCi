package src;

import java.util.Random;

public class Apocalypse {

	public static void main(String[] args) {
		System.out.println(generateAll(100000));
	}
	
	// randomly generate family kids and stop when they have girl. calculate the ratio of girls / all
	
	private static double generateAll(int n) {
		int girl = 0, boy = 0;
		Random r = new Random();
		for(int i = 0; i <= n; i++) {
			while(true) {
				boolean sex = r.nextBoolean();
				if(sex) {
					girl++;
					break;
				} else boy++;
			}
		}
		return (double) girl / boy;
	}

}
