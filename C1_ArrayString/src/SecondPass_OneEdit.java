
public class SecondPass_OneEdit {
/*
    String check char by char.
    1. diff of len >= 2 or value equal, false
    2. len equal: check char by char. meet a diff char, return rest subtring value equal
    3. len diff by 1: if cur two chars not equal, check a's cur char with b next, if equal, check two substrings. else check a's next and b cur substring 
    
    we could check in one pass, when meet a different char, perform length check and do different things.
*/
public boolean isOneEditDistance(String s, String t) {
    if(s.equals(t) || Math.abs(s.length() - t.length()) >= 2) return false;
    int ptr1 = 0, ptr2 = 0;
    while(ptr1 < s.length() && ptr2 < t.length()) {
        if(s.charAt(ptr1) != t.charAt(ptr2)) {
            if(s.length() == t.length()) return s.substring(++ptr1).equals(t.substring(++ptr2));
            else {
                if((ptr1 + 1) < s.length() && (s.charAt(ptr1 + 1) == t.charAt(ptr2))) 
                    return s.substring(ptr1 + 1).equals(t.substring(ptr2));
                else return s.substring(ptr1).equals(t.substring(ptr2 + 1));
            }
        } else {
            ptr1++;
            ptr2++;
        }
    }
    return true;
}
}
