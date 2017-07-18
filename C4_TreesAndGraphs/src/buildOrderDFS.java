import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class buildOrderDFS {
	public enum State{UNVISITED,VISITING,VISITED};
	public static class Project{
		private HashMap<String,Project> m = new HashMap<String,Project>();
		private ArrayList<Project> child = new ArrayList<Project>();
		private State s = State.UNVISITED;
		private String name;
		public Project(String n){
			this.name = n;
		}
		
		public ArrayList<Project> getChild(){
			return child;
		}
		
		public State getState(){
			return s;
		}
		
		public String name(){
			return name;
		}
		
		public void setState(State s){
			this.s = s;
		}
		
		public void addChild(Project c){
			if(!m.containsKey(c.name)){
				m.put(c.name,c);
				child.add(c);
			}
		}
	}
	
	public static class Graph{
		private HashMap<String,Project> m = new HashMap<String,Project>();
		private ArrayList<Project> nodes = new ArrayList<Project>();
		
		public ArrayList<Project> getNodes(){
			return nodes;
		}
		
		public Project addNode(String a){
			if(!m.containsKey(a)){
				Project A = new Project(a);
				m.put(a,A);
				nodes.add(A);
			}
			return m.get(a);
		}
		
		public void addEdge(String a, String b){
			addNode(a).addChild(addNode(b));
		}
	}
	
	public static Stack<Project> buildOrd(String[] projs, String[][] Dep){
		Graph g = new Graph();
		for(String p : projs){
			g.addNode(p);
		}
		for(String[] pair : Dep){
			g.addEdge(pair[0],pair[1]);
		}
		
		return buildOrder(g.getNodes());
		
	}
	
	public static Stack<Project> buildOrder(ArrayList<Project> n){
		Stack<Project> s = new Stack<Project>();
		for(Project p: n){
			if(p.s == State.UNVISITED){
				boolean f = doDFS(p,s);
				if(f == false){
					return null;
				}
			}else{
			}
		}
		return s;
	}
	
	public static boolean doDFS(Project p, Stack<Project> s){
		if(p.s == State.VISITING)	return false;
//		if(p == null) 	return true;		
		if(p.s == State.UNVISITED){
			p.s = State.VISITING;
			for(Project c: p.getChild()){		//leaves wont be executed whats inner
				boolean f = doDFS(c,s);
				if(f == false){
					return false;
				}
			}
//*出錯		}		
			p.s = State.VISITED;		//child will become p when recursive to it
			s.push(p);
		}
		return true;
	}
	
	public static String[] convertToStringList(Stack<Project> projects) {
		String[] buildOrder = new String[projects.size()];
		for (int i = 0; i < buildOrder.length; i++) {
			buildOrder[i] = projects.pop().name();
		}
		return buildOrder;
	}
	
	public static void main(String[] args){
		String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		String[][] dependencies = {
				{"a", "b"},
				{"b", "c"},
				{"a", "c"},
				{"d", "e"},
				{"b", "d"},
				{"e", "f"},
				{"a", "f"},
				{"h", "i"},
				{"h", "j"},
				{"i", "j"},
				{"g", "j"}};
		String[] res = convertToStringList(buildOrd(projects, dependencies));
		if(res == null){
			System.out.print("circular");
		}else{
		for(String s: res){
			System.out.print(s);
		}
		}
		
	}
	
	
}
