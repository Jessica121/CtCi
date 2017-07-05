
public class Graph {
	public Node[] vertices;	//All the vertices
	public int numOfV;
	public int count;
	public Graph(int numOfV){
		vertices = new Node[numOfV];
		count = 0;
	}
	
	public void addVer(Node v){
		if(count < vertices.length){
			vertices[count] = v;
			count ++;
		}else{
			System.out.println("Graph is full now");
		}
	}
	
	public Node[] getV(){
		return vertices;
	}

}
