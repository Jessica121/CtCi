import java.util.Arrays;

public class CtciChapter1 {
public static boolean isUnique(String a){//1.1
	int len = a.length();
	int[] map = new int[256];
	if (len >=256){
		return false;
	}
	for(int i=0; i<len;i++){
		int index = a.charAt(i);// why is int(ASCII form??)
		System.out.println(index);
		if(map[index] == 1){
			return false;}
		else
			map[index] = 1;
	}
	return true;
}

public static String sort(String string){//1.2
	char[] ofCharVer = string.toCharArray();
	java.util.Arrays.sort(ofCharVer);
	System.out.println(new String (ofCharVer));
	return new String (ofCharVer);//.toString();
}

public static boolean isPermu(String s1, String s2){
	return s1.equals(s2);
}

public static boolean Permu(String s1, String s2){
	
	int len1 = s1.length();
	int len2 = s2.length();
	if (len1!=len2) return false;
	int[] map = new int[128];//ACII
	for (int i =0; i <len1; i++){
		int index1 = s1.charAt(i);
		map[index1]++ ;
	}
	
	for(int j = 0; j<len2; j++){
		int index2 = s2.charAt(j);
		map[index2]--;
		if (map[index2] < 0)
			return false;
	}
	return true;
}

public static void main(String args[]){
	String s1 = "cba";
	String s2 = "abcck.dfgjfbgdkjfbdjksnsjkdnjdbdjzbf,dzfbz";
	if(Permu(s1,s2)){
		System.out.println("yep");
	}else
		System.out.println("nop");
	
	/*1.2	SORT THE CHAR
	String s1 = "AC";
	String s2 = "ac";
	String s12 = sort(s1);
	String s22 = sort(s2);
	if(isPermu(s12,s22)){
		System.out.println("yep");
	}else
		System.out.println("nop");
	
	
	
	
	/*1.1
 String string = "yelo";
 if(isUnique(string)){
	 System.out.println("yes");
 }else
	 System.out.println("no");
	*/
}

}
