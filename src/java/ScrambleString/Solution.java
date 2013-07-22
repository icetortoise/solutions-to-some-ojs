package ScrambleString;
import java.util.*;
public class Solution {
    public boolean isScramble(String s1, String s2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        char[] ss1 = s1.toCharArray();
        char[] ss2 = s2.toCharArray();
	//	HashMap<List<Integer>,Boolean> memo = new HashMap<List<Integer>,Boolean>();
	int[][][][] memo = new int[s1.length()][s1.length()][s2.length()][s2.length()];
	return isScrambleImpl(ss1, ss2, 0, ss1.length-1, 0, ss2.length-1, memo);
    }

    public boolean isScrambleImpl(char[] ss1, char[] ss2, int start1, int end1,
				  int start2, int end2, int[][][][] memo){
	// Integer[] keyArr = new Integer[]{start1, end1, start2, end2};
	// List<Integer> key = Arrays.asList(keyArr);
	if(memo[start1][end1][start2][end2] != 0){
	    return memo[start1][end1][start2][end2] == 1? true : false;
	}else{
	    if(start1 == end1){
		memo[start1][end1][start2][end2] = ss1[start1] == ss2[start2]? 1 : -1;
		return ss1[start1] == ss2[start2];
	    }else{
		for(int i = 0; i < end1 - start1 ; i++){
		    if(isScrambleImpl(ss1, ss2, start1, start1+i, start2, start2+i, memo) &&
		       isScrambleImpl(ss1, ss2, start1+i+1, end1, start2+i+1, end2, memo)){
			memo[start1][end1][start2][end2] = 1;
			//			memo.put(key, true);
			return true;
		    }else if(isScrambleImpl(ss1, ss2, start1, start1+i, end2 - i, end2, memo) &&
			     isScrambleImpl(ss1, ss2, start1+i+1, end1, start2, end2 - i - 1, memo)){
			memo[start1][end1][start2][end2] = 1;
			// memo.put(key, true);
			return true;
		    }
		}
		memo[start1][end1][start2][end2] = -1;
		//		memo.put(key, false);
		return false;
	    }
	}
    }
}
