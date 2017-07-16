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
			
			if(!chker.containsKey(n.name())){
			child.add(n);
//*这里也出错
			n.numDep++;
			System.out.println(n.name+"'s numDep is "+n.numDep);
			chker.put(n.name(),n);
			}else{
				System.out.println(this.name+" node had "+n.name+" node! ");
			}
		}
		public ArrayList<Project> getChild(){
			return child;
		}
		
		public int depNum(){
			return numDep;
		}
		
	}
	
	public static class Graph{
		private ArrayList<Project> nodes = new ArrayList<Project>();
		private HashMap<String, Project> ckr = new HashMap<String, Project>();
		public Project addNode(String name){
//*这里出错
//			Project n = new Project(name);
			if(!ckr.containsKey(name)){
				Project n = new Project(name);
				nodes.add(n);
				ckr.put(name , n);
			}
//			*
			return ckr.get(name);//*get 已有的dep不一定0，在原来的基础上更新
//			return n;		//all new nodes with numDep == 0;
		}
		
		public void addEdge(String a , String b){
			addNode(a).addNei(addNode(b));
		}
		
		public ArrayList<Project> getNodes(){
			for(Project p:nodes){
				System.out.print(p.name()+" "+p.numDep+" ");
			}
			System.out.println();
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
	
	public static Project[] buildNodeOrder(ArrayList<Project> ns){
		Project[] res = new Project[ns.size()];
		int processI = 0;
		int index = 0;
		index = addToArr(ns,res,index);
 
		while(processI < res.length){
			if(res[processI] == null){
				return null;
			}
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
				addAti++ ;
			}
		}
		return addAti;
	}
	
	public static Project[] buildOrder(String[] i, String[][] d){
		Graph g = buildG(i,d);
		Project[] res = buildNodeOrder(g.getNodes());
		return res;
	}
	
	public static void convertTS(Project[] inp){
		String[] s = new String[inp.length];
		int i = 0;
		for (Project p:inp){
			s[i] = p.name();
			System.out.print(s[i]+" ");
			i++;
		}

	}
	
	public static void main (String[] args){
		String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		String[][] dependencies = {
				{"a", "b"},
				{"b", "c"},
				{"a", "c"},
				{"a", "c"},
				{"d", "e"},
				{"b", "d"},
				{"e", "f"},
				{"a", "f"},
				{"h", "i"},
				{"h", "j"},
				{"i", "j"},
				{"g", "j"}};
		if(buildOrder(projects,dependencies) != null){
		convertTS(buildOrder(projects,dependencies));
		}else{
			System.out.print("circular");
		}
	}
}
