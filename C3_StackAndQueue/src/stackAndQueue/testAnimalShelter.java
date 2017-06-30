package stackAndQueue;

import stackAndQueue.AnimalShelter.AnimalQ;
import stackAndQueue.AnimalShelter.Dogs;

public class testAnimalShelter {
	static AnimalShelter AS = new AnimalShelter();
	public static void main(String args[]){
		AnimalQ Q = new AnimalQ();
		Q.enQ(AS.new Dogs("d1"));
		Q.enQ(AS.new Dogs("d2"));
		Q.enQ(AS.new Dogs("d3"));
		Q.enQ(AS.new Dogs("d4"));
		Q.enQ(AS.new Cats("c1"));
		Q.enQ(AS.new Cats("c2"));
		Q.enQ(AS.new Cats("c3"));
		for(int i = 0; i<7; i++){
		System.out.println(Q.deqAny().name);
		}
		
	}
}
