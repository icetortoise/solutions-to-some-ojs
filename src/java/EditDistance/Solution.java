package EditDistance;

//wrong version. correct one uses dp by solving sub problems of (word1[0..i],word2[0..j])
public class Solution {
    public int minDistance(String word1, String word2) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(word1.length() == word2.length()){
	    int dis = 0;
	    for(int i = 0; i < word1.length(); i++){
		if(word1.charAt(i) != word2.charAt(i)){
		    dis++;
		}
	    }
	    if(word1.length() >= 3){
		for(int i = 0; i < word1.length(); i++){
		    String newWord = word1.substring(0,i) + word1.substring(i+1);
		    int newDis = minDistance(newWord, word2) + 1;
		    if(newDis < dis)
			dis = newDis;
		}
	    }
	    return dis;
	}else{
	    if(word1.length() < word2.length()){
		String tmp = word1;
		word1 = word2;
		word2 = tmp;
	    }
	    int dis = Integer.MAX_VALUE;
	    for(int i = 0; i < word1.length(); i++){
		String newWord = word1.substring(0,i) + word1.substring(i+1);
		int newDis = minDistance(newWord, word2);
		if(newDis < dis){
		    dis = newDis;
		}
	    }
	    return dis+1;
	}
    }
}
