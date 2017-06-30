package stackAndQueue;
import java.util.LinkedList;

public class AnimalShelter {
//3.6
	public abstract class Animals {
		protected String name;
		private int orderI = 0;//I= Individual;
		public Animals (String n){
			this.name = n;
		}
		public void setOrder(int o){
			this.orderI = o;
		}
		public boolean isOlderThan(Animals b){
			return this.orderI < b.orderI;
		}
	}
	public class Dogs extends Animals{
		public Dogs(String n){
			super(n);
		}
	}
	public class Cats extends Animals{
		public Cats(String n){
			super(n);
		}
	}
	
	public static class AnimalQ{
		int orderT = 0; //T=Total;
		LinkedList<Dogs> dogQ = new LinkedList<Dogs>();
		LinkedList<Cats> catQ = new LinkedList<Cats>();
		
		public void enQ(Animals a){
			orderT++;
			((stackAndQueue.AnimalShelter.Animals) a).setOrder(orderT);
			if(a instanceof Dogs){
				dogQ.addLast((Dogs) a);
			}else if(a instanceof Cats){
				catQ.addLast((Cats) a);
			}
		}
		
		public Animals deqAny(){
			if(dogQ.size() == 0){
				return (Animals) catQ.poll();
			}else if(catQ.size() == 0){
				return (Animals) dogQ.poll();
			}else{
				if(dogQ.peek().isOlderThan(catQ.peek())){
						return (Animals) dogQ.poll();
				}else{
					return (Animals) catQ.poll();
				}
			}
		}
		
		public Cats deqCats(){
			return catQ.poll();
		}
		public Dogs deqDogs(){
			return dogQ.poll();
		}
	}

}