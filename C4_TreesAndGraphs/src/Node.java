

public class Node {
	public int count;
	public Node[] Nei;
	public int maxNumOfNei;
	public String name;
	public ProblemsPart1.State state;
	public Node(String n, int maxN){
		count = 0;
		this.maxNumOfNei = maxN;
		Nei = new Node[maxNumOfNei];
		this.name = n;
	}
	
	public void addNei(Node n){
		if(count < Nei.length){
			Nei[count] = n;
			count ++;
		}else{
			System.out.println("Could not add nei anymore");
		}
	}
	
	public Node[] getNei(){
		return Nei;
	}
	
	public String getName(){
		return this.name;
	}
}
