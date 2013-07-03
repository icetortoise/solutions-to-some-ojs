package SuffixArray;

import java.util.Comparator;
import java.util.Arrays;

// an implementation of the link: http://www.stanford.edu/class/cs97si/suffix-array.pdf
public class SuffixArray{
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
}
