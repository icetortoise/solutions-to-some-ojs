package ValidParentheses;

public class Solution {
    public boolean isValid(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	Character[] stk = new Character[1000];
	int stkTop = -1;
	for(int i = 0; i < s.length(); i++){
	    if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
		stkTop++;
		stk[stkTop] = s.charAt(i);
	    }else if(stkTop >= 0){
		if( (s.charAt(i) == ')' && stk[stkTop] == '(') ||
		    (s.charAt(i) == ']' && stk[stkTop] == '[') ||
		    (s.charAt(i) == '}' && stk[stkTop] == '{')){
		    stkTop--;
		}else{
		    return false;
		}
	    }else{
		return false;
	    }
	}
	if(stkTop >=0 ){return false;}
	else {return true;}
    }
}
