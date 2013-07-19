package LengthOfLastWord;

public class Solution {
    public int lengthOfLastWord(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int runningL = 0;
	boolean running = false;
	for(int i = 0; i < s.length(); i++){
	    if(s.charAt(i) == ' '){
		if(running){
		    running = false;
		}
	    }else{
		if(running){
		    runningL++;
		}else{
		    running = true;
		    runningL = 1;
		}
	    }
	}
	return runningL;
    }
}
