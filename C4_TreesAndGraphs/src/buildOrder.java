import java.util.ArrayList;
import java.util.HashMap;

public class buildOrder {
	//4.7 buildOrder, give pairs of dependencies
	public static class Project{
		private int numDep = 0;
		private ArrayList<Project> child;
		private HashMap<String, Project> chker;
		private String name;
		public Project(String n){
			this.name = n;
			child = new ArrayList<Project>();
			chker = new HashMap<String, Project>();
		}
		public String name(){
			return this.name;
		}
		public void addNei(Project n){
			n.numDep++;
			if(!chker.containsKey(n.name())){
			child.add(n);
			chker.put(n.name(),n);
			}
		}
		public ArrayList<Project> getChild(){
			return child;
		}
//		
		public int depNum(){
			return numDep;
		}
		
	}
	
	public static class Graph{
		private ArrayList<Project> nodes = new ArrayList<Project>();
		private HashMap<String, Project> ckr = new HashMap<String, Project>();

		public Project addNode(String name){
			Project n = new Project(name);
			if(!ckr.containsKey(name)){
				nodes.add(n);
				ckr.put(name , n);
			}
			return  n;
		}
		
		public void addEdge(String a , String b){
			addNode(a).addNei(addNode(b));
		}
		
		public ArrayList<Project> getNodes(){
			return nodes;
		}
	}
	
	public static Graph buildG(String[] input, String[][] dep){
		Graph g = new Graph();
		for(String n : input){
			g.addNode(n);
		}
		
		for(String[] pair: dep){
			String a = pair[0];
			String b = pair[1];
			g.addEdge(a,b);
		}
		return g;
	}
	
	public static Project[] buildOrder(ArrayList<Project> ns){
		Project[] res = new Project[ns.size()];
		int processI = 0;
		int index = 0;
		index = addToArr(ns,res,index);
		if(res[processI] == null){
			return null;
		} 
		while(processI < res.length){
		for(Project c : res[processI].getChild()){
			c.numDep--;
		}
		index = addToArr(res[processI].getChild(),res,index);
		processI++;
		}
		return res;
	}

	public static int addToArr(ArrayList<Project> l, Project[] addTo, int addAti){
		for(Project p: l){
			if(p.numDep == 0){
				addTo[addAti] = p;
				addAti ++ ;
			}
		}
		return addAti;
	}
	
	public static Project[] buildOrder(String[] i, String[][] d){
		Graph g = buildG(i,d);
		Project[] res = buildOrder(g.getNodes());
		return res;
	}
	
}
