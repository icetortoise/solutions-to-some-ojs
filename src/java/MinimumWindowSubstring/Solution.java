package MinimumWindowSubstring;

public class Solution {
    public String minWindow(String S, String T) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int[] occurances = new int[256];
	for(int i = 0; i<T.length(); i++){
	    int idx = (int)T.charAt(i);
	    if(occurances[idx] == 0){
		occurances[idx] = -1;
	    }
	}
	for(int i = 0; i<S.length();i++){
	    int sIdx = (int)S.charAt(i);
	    if(occurances[sIdx] == -1){
		occurances[sIdx] = 1;
	    }else if(occurances[sIdx] > 0){
		occurances[sIdx] += 1;
	    }
	}
	for(int i = 0; i<occurances.length;i++){
	    if(occurances[i] == -1){
		return "";
	    }
	}
	int start = 0, end = S.length()-1;
	for(; start <= end; start++, end--){
	    int sIdx = (int)S.charAt(start);
	    if(occurances[sIdx] == 1){
		break;
	    }else if(occurances[sIdx]>1){
		occurances[sIdx] -= 1;
	    }
	    int eIdx = (int)S.charAt(end);
	    if(occurances[eIdx] == 1){
		break;
	    }else if(occurances[eIdx]>1){
		occurances[eIdx] -= 1;
	    }
	}
	return S.substring(start, end+1);
    }
}
