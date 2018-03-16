import java.util.*;

public class encodeXML {

	class Element {
		List<Attribute> attributes;
		List<Element> children;
		String name;
		String value;
		public Element(String n, String v) {
			attributes = new ArrayList<>();
			children = new ArrayList<>();
			name = n;
			value = v;
		}
		
		public Element(String n) {
			attributes = new ArrayList<>();
			children = new ArrayList<>();
			name = n;
		}

		private void addAttribute(Attribute tag) {
			attributes.add(tag);
		}
		
		private void insertChild(Element child) {
			children.add(child);
		}
		
		private String getElementCode() {
			switch(name) {
				case "family" : return "1";
				case "person" : return "2";
				case "firstName" : return "3";
				case "lastName" : return "4";
				case "state" : return "5";
				default : return "ERROR";
			}
		}	
	}
	
	class Attribute {
		String tag;
		String value;
		public Attribute(String t, String v) {
			this.tag = t;
			this.value = v;
		}
		
		private String getAttributeCode() {
			switch(tag) {
				case "family" : return "1";
				case "person" : return "2";
				case "firstName" : return "3";
				case "lastName" : return "4";
				case "state" : return "5";
				default : return "ERROR";
			}
		}
	}
	
	public static void main(String[] args) {
		encodeXML instance = new encodeXML();
		Element root = instance.new Element("family");
		root.addAttribute(instance.new Attribute("lastName", "McDowell"));
		root.addAttribute(instance.new Attribute("state", "CA"));
		Element child = instance.new Element("person", "Some message");
		child.addAttribute(instance.new Attribute("firstName", "Gayle"));
		root.insertChild(child);
		StringBuilder sb = new StringBuilder();
		encodeXML(root, sb);
		System.out.println(sb.toString().trim());
	}
	
	private static void encodeXML(Element root, StringBuilder sb) {
		appendStr(root.getElementCode(), sb);
		decodeAttributes(root.attributes, sb);
		for(Element child : root.children) {
			encodeXML(child, sb);
		}
		if(root.value != null && root.value != "") appendStr(root.value, sb);
		appendStr("0", sb);
	}
	
	private static void decodeAttributes(List<Attribute> attrs, StringBuilder sb) {
		for(Attribute a : attrs) {
			appendStr(a.getAttributeCode(), sb);
			appendStr(a.value, sb);
		}
		appendStr("0", sb);
	}

	private static void appendStr(String s, StringBuilder sb) {
		sb.append(s).append(" ");
	}
}
