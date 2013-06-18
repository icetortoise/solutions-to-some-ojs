package ZigZag;

public class Solution {
    public String convert(String s, int nRows) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(nRows == 1){return s;}
	int l = s.length();
	StringBuffer sb = new StringBuffer();
	for(int i = 0 ; i <= nRows - 1; i++){
	    if(i >= l){break;}
	    int cursor = i;
	    int distance = 2*(nRows-1 - i);
	    while(cursor < l){
		sb.append(s.charAt(cursor));
		if(i!=0 && i!= nRows-1){
		    if(cursor + distance < l){
			sb.append(s.charAt(cursor + distance));
		    }else{
			break;
		    }
		}
		cursor = cursor + 2*(nRows-1);
	    }
	}
	return sb.toString();
    }
}
