import java.util.Arrays;

public class CtciChapter1 {
	
public static void Print(){
	System.out.println();
}


public static boolean isUnique(String a){//1.1
	int len = a.length();
	int[] map = new int[256];
	if (len >=256){
		return false;
	}
	for(int i=0; i<len;i++){
		int index = a.charAt(i);// why is int (ASCII form??)
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
	char[] charVer = s1.toCharArray();
	for(char index : charVer){
		map[index]++;
	}
	/*for (int i =0; i <len1; i++){
		int index1 = s1.charAt(i);
		map[index1]++ ;
	}*/
	
	for(int j = 0; j<len2; j++){
		int index2 = s2.charAt(j);
		map[index2]--;
		if (map[index2] < 0)
			return false;
	}
	return true;
}

public static void URLify(char[] str, int trueLen){
	int spaceCount = 0;
	for(int i = 0; i< trueLen; i++){
		if (str[i]== ' '){
			spaceCount++;
		}
	}
	int urlLen = trueLen + spaceCount*2;
	if(trueLen < str.length)
		str[trueLen] = '\0';
	for(int j = trueLen-1; j>=0; j--){//搞清楚原来和要覆盖
		if (str[j] == ' '){
			str[urlLen-1] = '0';
			str[urlLen-2] = '2';
			str[urlLen-3] = '%';
			urlLen -= 3;
		} else{
			str[urlLen-1] = str[j];
			urlLen--;
		}
	}
}


public static boolean isPermuOfPalindrome(String input){
	
	return hasAtMostOneOdd(input);
}

public static int charToInt(char c){
	int m = Character.getNumericValue(c);
	System.out.println(m);
	int a = Character.getNumericValue('a');
	System.out.println(a);
	int z = Character.getNumericValue('z');
	System.out.println(z);
	if(m>=a && m<=z){
		return m-a;//???
	}
	return -1;
}

public static int[] buildFreqTable(String input){
	char[] cVer = input.toCharArray();
	int[] map = new int[charToInt('z')-charToInt('a')+1];
	for(char c : cVer){
		if (charToInt(c)!= -1)
		map[charToInt(c)]++;
	}
	return map;
}
public static boolean hasAtMostOneOdd(String input){
	int[] freq = buildFreqTable(input);
	boolean hasOdd = false;
	for (int i : freq){
		if (i%2 ==1){
			if(hasOdd == true)
			return false;
			else
			hasOdd = true;	
		}
			
	}
	return true;
}

public static void main(String args[]){
	if(isPermuOfPalindrome("tacocat"))
		System.out.println("yep");
	else System.out.println("nop");
	
	
	/*
	char[] str = {'c','h','e',' ','s',' ',' '};	//1.3
	URLify(str,5);
	System.out.println(str);
	/*
	String s1 = "cba";
	String s2 = "abc";
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
