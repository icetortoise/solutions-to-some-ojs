package SubstringWithAllWords;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Arrays;

// this solution does not pass OJ because i overlooked the condition that elements in L are of the same length. This is 
// actually a more general solution. 
// In particular, if L[i] are all of the same length, the operatin to judge if S[a..a+len] is a substring is constant!
// Potential improvement is around strStr, to use KMP or a suffix array!
public class Solution {

    public ArrayList<Integer> findSubstring(String S, String[] L) {
	HashMap<Integer, ArrayList<Integer>> idxToMatches = new HashMap<Integer, ArrayList<Integer>>();
	for(int i = 0; i < L.length; i++){
	    int[][] surArr = buildArray(S);
	    for(Integer idx : strStr(surArr, S, L[i])){
		if(idxToMatches.containsKey(idx)){
		    ArrayList<Integer> temp = idxToMatches.get(idx);
		    temp.add(i);
		    idxToMatches.put(idx, temp);
		}else{
		    ArrayList<Integer> n = new ArrayList<Integer>();
		    n.add(i);
		    idxToMatches.put(idx, n);
		}
	    }
	}
	ArrayList<Integer> result = new ArrayList<Integer>();
	for(Integer idx : idxToMatches.keySet()){
	    HashSet<Integer> c = new HashSet<Integer>();
	    if(validSubWithAllWords(idx, idxToMatches, L, c))
		result.add(idx);
	}
	return result;
    }

    public boolean validSubWithAllWords(int idx, HashMap<Integer, ArrayList<Integer>> idxToMatches, 
					String[] L, HashSet<Integer> c){
	if(c.size() == L.length){return true;}
	if(!idxToMatches.containsKey(idx)){return false;}
	for(Integer match : idxToMatches.get(idx)){
	    HashSet<Integer> newc = new HashSet<Integer>(c);
	    if(!newc.contains(match)){
		newc.add(match);
		if(validSubWithAllWords(idx + L[match].length(), idxToMatches, L ,newc)){
		    return true;
		}
	    }
	}
	return false;
    }

    public ArrayList<Integer> strStr(int[][] surArr, String haystack, String needle) {
	int[] surArrSorted = new int[haystack.length()];
	for(int i = 0; i < haystack.length(); i++){
	    surArrSorted[surArr[surArr.length - 1][i]] = i;
	}
	//binary search goes from here
	int start = 0, end = haystack.length() - 1;
	ArrayList<Integer> result = new ArrayList<Integer>();
	return bsearch(haystack, needle, 0, haystack.length()-1, surArrSorted, result);
    }
    
    public ArrayList<Integer> bsearch(String haystack, String needle, 
				      int start, int end, int[] sa, ArrayList<Integer> result){
	if(start > end ) return result;
	if(start == end){
	    int endOfSub = sa[start] + needle.length() > sa.length?sa.length:sa[start] + needle.length();
	    if(needle.equals(haystack.substring(sa[start], 
						endOfSub))){
		result.add(sa[start]);
		return result;
	    }else{
		return result;
	    }
	}
	if(start < end){
	    int mid = (start + end)/2;
	    int endOfSub = sa[mid] + needle.length() > sa.length?sa.length:sa[mid] + needle.length();
	    String candidate = haystack.substring(sa[mid], endOfSub);
	    int cmp = needle.compareTo(candidate);
	    if(cmp == 0){
		result.add(sa[mid]);
		result = bsearch(haystack, needle, start, mid-1, sa, result);
		return bsearch(haystack, needle, mid+1, end, sa, result);
	    }else if(cmp < 0){
		return bsearch(haystack, needle, start, mid-1, sa, result);
	    }else{
		return bsearch(haystack, needle, mid+1, end, sa, result);
	    }
	}
	return result;
    }

    // ===== suffix array impl ====
    public static int[][] buildArray(String s){
	assert s!=null;
	int n = s.length();
	int steps = (int)Math.ceil(Math.log(n)/Math.log(2));
	if(n == 0){
	    return new int[][]{};
	}else if(n == 1){
	    int[][] result = new int[1][1];
	    return result;
	}else if(n == 2){
	    int[][] result = new int[1][2];
	    if((int)s.charAt(0) - (int)s.charAt(1) <= 0){
		result[0][1] = 1;
	    }else{
		result[0][0] = 1;
	    }
	    return result;
	}
	int[][] result = new int[steps + 1][n];
	Entry[] sortOn = new Entry[n];
	for(int i = 0; i < n; i++){
	    result[0][i] = (int)s.charAt(i); // notice: this acutally tags result[0] with correct order autimatically
	}
	for(int stp = 1; stp <= steps; stp++){
	    int offset = (int)Math.pow(2, stp-1); // i+offset will give the start idx of the second half
	    for(int i = 0; i < n; i++){
		Entry e = new Entry();
		e.firstHalf = result[stp-1][i];
		e.secondHalf = i+offset < n? result[stp-1][i+offset]: -1;
		e.pos = i;
		sortOn[i] = e;
	    }
	    Arrays.sort(sortOn, new Comp());
	    int fst = -1, snd = -1;
	    int count = -1;
	    for(int i = 0; i < n; i++){
		if(sortOn[i].firstHalf != fst || sortOn[i].secondHalf != snd){
		    fst = sortOn[i].firstHalf;
		    snd = sortOn[i].secondHalf;
		    count++;
		}
		result[stp][sortOn[i].pos] = count;
	    }
	}
	return result;
    }
    
    // helper class that will hold last prefix(first half) order and current prefix(second half) order, sort on entry will provide 
    // an order of the concat of last and current prefix. Also holding the position information that tells
    // what suffix we are dealing with.
    static class Entry{
	int firstHalf;
	int secondHalf;
	int pos;
    }

    static class Comp implements Comparator<Entry>{
	public int compare(Entry e1, Entry e2){
	    int fd = e1.firstHalf - e2.firstHalf;
	    if(fd == 0){
		return e1.secondHalf - e2.secondHalf;
	    }
	    return fd;
	}
    }

    // === end of suffix array ===

    public ArrayList<Integer> strStrNaive(String haystack, String needle) {
	// Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Integer> result =  new ArrayList<Integer>();
	for(int i = 0; i<haystack.length() - needle.length() + 1; i++){
	    if(haystack.substring(i, i + needle.length()).equals(needle)){
		result.add(i);
	    }
	}
	return result;
    }

}

