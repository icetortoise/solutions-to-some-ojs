package WildcardMatching;

public class Solution {
    public boolean isMatch(String s, String p) {
        // Start typing your Java solution below
        // DO NOT write main() function
	StringBuffer sb = new StringBuffer();
	char last = '0';
	for(int i = 0; i < p.length(); i++){
	    if(last == p.charAt(i) && last == '*'){continue;}
	    last = p.charAt(i);
	    sb.append(p.charAt(i));
	}
	p = sb.toString();
	return isMatchImpl(s, 0, p, 0);
    }
    public boolean isMatchImpl(String s, int si, String p, int pi){
	if(si == s.length() && pi == p.length()){
	    return true;
	}
	if(si <= s.length() && pi < p.length() && p.charAt(pi) == '*'){
	    if(isMatchImpl(s, si, p, pi+1)){
		return true;
	    }else{
		return isMatchImpl(s, si+1, p, pi);
	    }
	}
	if(si >= s.length() || pi >= p.length()){
	    return false;
	}
	if(p.charAt(pi) != '?' && p.charAt(pi) != '*' && si < s.length()){
	    if(p.charAt(pi) == s.charAt(si)){
		return isMatchImpl(s, si+1, p, pi+1);
	    }else{
		return false;
	    }
	}
	if(p.charAt(pi) == '?' && si < s.length()) {
	    return isMatchImpl(s, si+1, p, pi+1);
	}
	
	return false; // never reach
    }
}
