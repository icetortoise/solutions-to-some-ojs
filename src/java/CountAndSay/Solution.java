package CountAndSay;

public class Solution {
    public String countAndSay(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	String s = "1";
	while(--n > 0){
	    s = countAndSayImpl(s);
	}
	return s;
    }
    public String countAndSayImpl(String s){
	StringBuffer sb = new StringBuffer();
	char c = '*';
	int count = 0;
	for(int i = 0; i <= s.length(); i++){
	    if(i == s.length()){
		if(count > 0){
		    sb.append(count);
		    sb.append(c);
		}
		break;
	    }
	    if(s.charAt(i) != c){
		if(count > 0){
		    sb.append(count);
		    sb.append(c);
		}
		c = s.charAt(i);
		count = 1;
	    }else{
		count++;
	    }
	}
	return sb.toString();
    }
}
