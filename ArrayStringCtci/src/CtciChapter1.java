import java.util.Arrays;

public class CtciChapter1 {

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
			hasOdd = true;	//
		}
	}
	return true;
}

public static boolean isPermuOfPalindrome(String input){
	
	return hasAtMostOneOdd(input);
}

public static boolean isPermuOfPalindromeCheckOnFly(String input){
	//no freq table, check the oddNum on the fly. by +1 -1 when encounting num of char, will 抵消成0偶数，1奇数for EACH char.
	int[] map = new int[charToInt('z')-charToInt('a')+1];
	char[] cVer = input.toCharArray();
	int oddCount = 0;
	for(char c:cVer){
		if (charToInt(c) != -1){
		map[charToInt(c)]++;
		if ((map[charToInt(c)])%2 == 1)
			oddCount ++;		//--
		else
			oddCount --;		//++ oddCount>=-1
		}
	}
	return oddCount <=1;// 1时iff只有一个char是奇数；aa 是0；aaa=1；abba=0；abbba= 1 2 1 2 1 = 1;aaabbb=2
}

public static int charToInt(char c){
	int m = Character.getNumericValue(c);
	System.out.println(m);
	int a = Character.getNumericValue('a');
	System.out.println(a);
	int z = Character.getNumericValue('z');
	System.out.println(z);
	if(m>=a && m<=z){	//equal sigh. || exclude && include
		return m-a;
	}
	return -1;
}

public static boolean isPermuPalinBitVer(String input){
	int bv = creatBitV(input);
	return (bv == 0)||((bv&(bv-1))==0);
}

public static int creatBitV(String input){
	int bV = 0;
	char[] cVer = input.toCharArray();
	for(char c: cVer){
		int i = charToInt(c);
		if(i != -1)
		bV = addToBitV(bV,i);
	}
	//System.out.println(bV);
	return bV;
}

public static int addToBitV(int bV,int i){
	int adder = 1;		//adder is 1. 0 is always 0.
	//from a to z 一定要有对应。没有都是0的情况。与array从0开始两码事。
	System.out.println(adder);
	adder = adder<<i;
	System.out.println(i);
	System.out.println(adder);
	if((adder & bV) == 0)//never mapped, (adder & bV) use ();
		bV |= adder;
	else 
		bV &= ~adder;
	System.out.println(bV);
	return bV;
}

public static boolean oneEditAway(String s1, String s2){
	if (Math.abs(s1.length()-s2.length())>=2){
		return false;
	}
	if (Math.abs(s1.length()-s2.length()) == 1){
		return oneRorIAway(s1, s2);//abc, abcd; abc, adbc; abc, addd
	}
	if(s1.length() == s2.length()){	//abc, adc; abc, add
		return oneReplaceAway(s1, s2);
	}
	return true;
}

public static boolean oneRorIAway(String s1, String s2){
	//abc, abcd; abc, adbc; abc, addd
	String shorter = s1.length() < s2.length() ? s1 : s2;
	System.out.println(shorter);
	String longer = s1.length() < s2.length() ? s2 : s1;
	System.out.println(longer);
	int flag = 0; //or bool, mark when the only one diff is allowed;
	int idx1 = 0;
	int idx2 = 0;
	while(idx1<s1.length() && idx2 < s2.length() ){
		if (shorter.charAt(idx1) != longer.charAt(idx2)) {
			if (flag == 1){
				return false;
			}
			flag = 1;
			idx2++;
		}else{		//不写else的话不论怎么样都会加！！
		idx1++;
		idx2++;
		}
	}
	return true;

}

public static boolean oneReplaceAway(String s1, String s2){
	int index1 = 0;//abc, abd; abb, add
	int index2 = 0;
	boolean flag = false;
	while(index1< s1.length()){		//String and Array 按位置操作一定要限定位置的范围，不能超过。最最基本。
		if(s1.charAt(index1) != s2.charAt(index2)){
			if(flag == true){//if 条件判断对错，双等号最最基本。
				return false;
			}
			flag = true;	//FLAG在一定条件下改变起它的作用！
		}
		index1++;//增长的条件写在那一块；
		index2++;
	}return true;
}

public static boolean oneEditAwayComb(String s1, String s2){
	int flg = 0;
	int index1 = 0;
	int index2 = 0;
	String shorter = s1.length() < s2.length() ? s1 : s2;
	//if len is the same, go to the second condition;
	String longer = s1.length() < s2.length() ? s2 : s1;
	//System.out.println(longer);
	if (longer.length() - shorter.length() >= 2)
		return false;		//check the most basic case that doesnt fit at all
	
	while(index1<s1.length() && index2 < s2.length() ){
		if(shorter.charAt(index1) != longer.charAt(index2) ){
			if(flg == 1)		//int == 1 cannot cast to bool type:)
				return false;
			flg = 1;
			if (shorter.length() == longer.length() ){
				index1++;
			}//for replace doesnt match; if not replace, ignore it
		}
		else//match
			index1++;
		index2++;	//whether match or not, it is true anyways:)
		//combine 的方法特别好，compact, think more before you go。
	}		
	return true;
}


public static void main(String args[]){
	String str1 = "";
	String str2 = " ";	//好例子也是关键：））
	if(oneEditAwayComb(str1,str2)){
		System.out.println("yay");
	}else{
		System.out.println("naaah");
	}
	
	
	/* 1.4 好题，三种方法。onthefly, bit manipulation.
	if(isPermuPalinBitVer("aaaccc"))
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
