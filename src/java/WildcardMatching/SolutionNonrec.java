package WildcardMatching;

public class SolutionNonrec {
    public boolean isMatch(String s, String p) {
        // Start typing your Java solution below
        // DO NOT write main() function
	StringBuffer sb = new StringBuffer();
	char last = '\0';
	for(int i = 0; i < p.length(); i++){
	    if(last == p.charAt(i) && last == '*'){continue;}
	    last = p.charAt(i);
	    sb.append(p.charAt(i));
	}
	p = sb.toString();
	Boolean[][] results = new Boolean[s.length()][p.length()];
	return false;
	//	return isMatchImpl(s, 0, p, 0);
    }
}
