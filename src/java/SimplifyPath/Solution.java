package SimplifyPath;

public class Solution {
    public String simplifyPath(String path) {
        // Start typing your Java solution below
        // DO NOT write main() function
	String[] ps = path.split("/");
	int stack = 0;
	for(int i = 0; i < ps.length; i++){
	    if(ps[i].equals(".") || ps[i].equals("")){
		continue;
	    }else if(ps[i].equals("..")){
		if(stack > 0)
		    stack--;
	    }else {
		ps[stack] = ps[i];
		stack++;
	    }
	}
	StringBuffer sb = new StringBuffer();

	for(int i = 0; i<stack; i++){
	    sb.append("/");
	    sb.append(ps[i]);
	}
	if (sb.length() == 0) sb.append("/");
	return sb.toString();
    }
}
