package TextJustification;

import java.util.ArrayList;

public class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int idx = 0;
	ArrayList<String> result = new ArrayList<String>();
	while(idx <= words.length-1){
	    int end = endIdx(words, L, idx);
	    String s = justify(words, L, idx, end);
	    result.add(s);
	    idx = end+1;
	}
	return result;
    }
    
    public int endIdx(String[] words, int L, int idx){
	int len = 0;
	int i = idx;
	for(; i < words.length; i++){
	    len += words[i].length();
	    if(len < L){
		len++;
	    }else if(len == L){
		break;
	    }else {
		i--;
		break;
	    }
	}
	return i >= words.length? words.length-1:i;
    }
    
    public String justify(String[] words, int L, int idx, int end){
	int l = 0;
	StringBuilder sb = new StringBuilder();
	for(int i = idx; i<=end;i++){
	    l+=words[i].length();
	}
	if(idx == end){
	    sb.append(words[idx]);
	    int spaces = L-l;
	    for(int i = 1; i <= spaces; i++){
		sb.append(' ');
	    }
	}else if(words.length-1 == end){//last line
	    int spaces = L-l;
	    for(int i = idx; i<= end; i++, spaces--){
		sb.append(words[i]);
		if(spaces > 0){sb.append(' ');}
	    }
	    if(spaces >= 0){
		for(int i = 1; i <= spaces; i++){
		    sb.append(' ');
		}
	    }
	}
	else{
	    int slots = end - idx;
	    int minSpaces = (L-l)/slots;
	    int minSpaceCount = (L-l)%slots;
	    for(int i = idx; i<= end; i++, minSpaceCount--){
		sb.append(words[i]);
		if(i == end){
		    break;
		}
		for(int j = 1; j <= minSpaces; j++){
		    sb.append(' ');
		}
		if(minSpaceCount > 0){
		    sb.append(' ');
		}
	    }
	}
	return sb.toString();
    }
}
