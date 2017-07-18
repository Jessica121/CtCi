import java.util.LinkedList;
import java.util.ArrayList;

public class ProblemsPart1 {
	
	public enum State{
		Univisited, Visiting, Visited;
	}
	
	public static void main(String args[]){
		Graph g = createGinSize6();
		Node[] allV = g.getV();
		if(hasPath(g, allV[0], allV[4])){
			System.out.print("Yes");
		}else{
			System.out.print("No"); 
		}
//		int[] arr = {};
//		for (int a : arr){
//			System.out.print("?");	//NOT EXECUTED, BUT WOULD NOT RETURN AN ERROR
//		}
//		System.out.print("D");
		
	}
//	4.1
	public static Graph createGinSize6(){
		Graph g = new Graph(6);
		Node[] nodes = new Node[6];
		nodes[0] = new Node("a",3);
		nodes[1] = new Node("b",0);
		nodes[2] = new Node("c",0);
		nodes[3] = new Node("d",1);
		nodes[4] = new Node("e",1);
		nodes[5] = new Node("f",0);
		
		nodes[0].addNei(nodes[1]);
		nodes[0].addNei(nodes[2]);
		nodes[0].addNei(nodes[3]);
		nodes[3].addNei(nodes[4]);
		nodes[4].addNei(nodes[5]);
		
		for(Node a: nodes){
			g.addVer(a);
		}
		return g;
		}	
	
	public static boolean hasPath(Graph g, Node s, Node end) {
		if(s == end) return true;
		LinkedList<Node> q = new LinkedList<Node>();
		q.addLast(s);
		s.state = State.Visiting;
		for(Node a: g.getV()){
			a.state = State.Univisited;
		}
		while(!q.isEmpty()){
			Node h = q.poll();
			if(h != null){
				for(Node n : h.getNei()){
					if(n.state == State.Univisited){
						if(n==end){
							return true;
						}else{
							q.addLast(n);
							n.state = State.Visiting;
						}
					}
				}
				h.state = State.Visited;
			}
		}
		return false;
	}
	
	
	
		
}
		
		