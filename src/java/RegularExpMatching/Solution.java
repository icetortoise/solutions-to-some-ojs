package RegularExpMatching;

public class Solution {
    public boolean isMatch(String s, String p) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(s.isEmpty() && p.isEmpty()){
	    return true;
	}else if(s.isEmpty() && wildcardsOnly(p)){return true;}
	else if(s.isEmpty() || p.isEmpty()){
	    return false;
	}
	char a = s.charAt(0);
	char b = p.charAt(0);
	if(p.length()>1 && p.charAt(1) == '*'){
	    if (b == '.' || a == b){
		return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
	    }else{
		return isMatch(s, p.substring(2));
	    }
	}else{
	    if (b == '.' || a == b){
		return isMatch(s.substring(1), p.substring(1));
	    }else{
		return false;
	    }
	}
    }
    
    private boolean wildcardsOnly(String p){
	int i = 0;
	while(i<p.length()){
	    if(i+1 < p.length() && p.charAt(i+1) == '*'){
		i = i + 2;
	    }else{
		return false;
	    }
	}
	return true;
    }
}
