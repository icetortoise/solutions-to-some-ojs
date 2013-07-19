package AddBinary;

public class Solution {
    public String addBinary(String a, String b) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int m = a.length(), n = b.length();
	StringBuilder sb = new StringBuilder();
	int i = 1;
	boolean carry = false;
	while(m - i >=0 || n -i >=0){
	    if(!carry){
		if((m - i) < 0){
		    sb.append(b.charAt(n-i));
		}else if((n - i) < 0){
		    sb.append(a.charAt(m-i));
		}else if(a.charAt(m-i) == '0' && b.charAt(n-i) == '0'){
		    sb.append('0');
		}else if((a.charAt(m-i) == '1' && b.charAt(n-i) == '0') ||
			 (a.charAt(m-i) == '0' && b.charAt(n-i) == '1')){
		    sb.append('1');
		}else{
		    sb.append('0');
		    carry = true;
		}
	    }else{
		if((m - i) < 0){
		    if(b.charAt(n-i) == '0'){
			sb.append('1');
			carry = false;
		    }else{
			sb.append('0');
		    }
		}else if((n - i) < 0){
		    if(a.charAt(m-i) == '0'){
			sb.append('1');
			carry = false;
		    }else{
			sb.append('0');
		    }
		}else if(a.charAt(m-i) == '0' && b.charAt(n-i) == '0'){
		    sb.append('1');
		    carry = false;
		}else if((a.charAt(m-i) == '1' && b.charAt(n-i) == '0') ||
			 (a.charAt(m-i) == '0' && b.charAt(n-i) == '1')){
		    sb.append('0');
		}else{
		    sb.append('1');
		}
	    }
	    i++;
	}
	if(carry) sb.append('1');
	return sb.reverse().toString();
    }
}
