package ValidNumber;

public class Solution {
    public boolean isNumber(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	s = s.trim();
	if(s.length() == 0) return false;
	char f = s.charAt(0);
	if(f != '+' && f != '-' && f !='.' && !numerical(f)){
	    return false;
	}
	boolean inNum = numerical(f);
	boolean noDot = f != '.';
	boolean numOnly = false;
	for(int i = 1; i<s.length();i++){
	    if(numOnly){
		if(!numerical(s.charAt(i)))
		    return false;
	    }else if(s.charAt(i) == 'e'){
		if(inNum){
		    if(i == s.length()-1){
			return false;
		    }else if (s.charAt(i+1) == '+' || s.charAt(i+1) == '-'){
			i++;
			numOnly = true;
		    }else{
			numOnly = true;
		    }
		}else{
		    return false;
		}
	    }else if(s.charAt(i) == '.'){
		if(noDot){
		    noDot = false;
		}else{
		    return false;
		}
	    }else if(numerical(s.charAt(i))){
		inNum = true;
	    }else{
		return false;
	    }
	}
	if(!numerical(s.charAt(s.length()-1)) && s.charAt(s.length()-1)!='.'){
	    return false;
	}
	return inNum?true:false;
    }
    public boolean numerical(char c){
	return c - '0' >= 0 && c - '0' <= 9;
    }
}
