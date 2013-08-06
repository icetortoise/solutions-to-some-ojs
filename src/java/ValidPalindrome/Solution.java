package ValidPalindrome;

public class Solution {
    public boolean isPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        StringBuilder sb = new StringBuilder();
	for(int i = 0; i<s.length(); i++){
	    char c = s.charAt(i);
	    if('0' <= c && c <= '9'){
		sb.append(c);
	    }else if('A' <= c && c <= 'Z'){
		sb.append(c);
	    }
	    else if('a' <= c && c <= 'z'){
		sb.append((char)(c - 'a' + 'A'));
	    }
	}
	int i = 0, j = 0;
	String ss = sb.toString();
	if(ss.length() == 0){
	    return true;
	}
	if(ss.length() % 2 == 0){
	    i = (ss.length() - 1)/2;
	    j = i + 1;
	}else{
	    i = ss.length()/2 - 1;
	    j = ss.length()/2 + 1;
	}
	while(i >= 0 && j <= ss.length()-1){
	    if(ss.charAt(i) == ss.charAt(j)){
		i--; j++;
		continue;
	    }else{
		return false;
	    }
	}
	return true;
    }
}
