
public class SecondPass_CompressString {
	/*
    use a prev to record the previous char, once not equal, check
    combine the i < arr.length and out of loop i == arr.length condition
    put prev into arr[ptr++]
    if(cnt == 1) ignore
    else {
        convert the cnt into string and weave the character one by one into array
    }
    if(i < arr.length)  prev = cur char
                        cnt = 1;
    
    do this out of the loop again.
    
    */
    public int compress(char[] chars) {
        if(chars.length == 0) return 0;
        int cnt = 1, ptr = 0;
        char prev = chars[0];
        for(int i = 1; i <= chars.length; i++) {
            if(i < chars.length && chars[i] == prev) cnt++;
            else {
                chars[ptr++] = prev;
                if(cnt != 1) {
                    String str = String.valueOf(cnt);
                    for(int j = 0; j < str.length(); j++) {
                        chars[ptr++] = str.charAt(j);
                    }
                }
                if(i < chars.length) {
                    prev = chars[i];
                    cnt = 1;
                }
            }
        }
        return ptr;
    }
}
