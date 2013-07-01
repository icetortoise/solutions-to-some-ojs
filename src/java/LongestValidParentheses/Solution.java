package LongestValidParentheses;

public class Solution {
    public int longestValidParentheses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int runningLength = 0, runningPair = 0, maxLength = 0;
	int[] lens = new int[s.length()];
	int[] lefts = new int[s.length()];
	int leftIdx = 0;
	for(int i = 0; i< s.length(); i++){
	    if(s.charAt(i) == '('){
		runningPair++;
		lefts[leftIdx] = i;
		leftIdx++;
	    }else{
		runningPair--;
		if(runningPair >= 0){
		    runningLength = i - lefts[leftIdx-1] + 1;
		    leftIdx--;
		    lens[i] = runningLength;
		    int j = i - lens[i];
		    while(j >= 0){
			if(lens[j] != 0){
			    runningLength += lens[j];
			    j = j - lens[j];
			}else{
			    break;
			}
		    }
		    if(runningLength > maxLength){
			maxLength = runningLength;
		    }
		    runningLength = 0;
		}
		else{
		    runningLength = 0;
		    runningPair = 0;
		}
	    }
	}
	return maxLength;
    }
}
