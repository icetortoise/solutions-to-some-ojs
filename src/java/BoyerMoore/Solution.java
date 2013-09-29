package BoyerMoore;

public class Solution{
    private char[] pa = null;
    public int[] right = new int[256];
    public Solution(String p){
	pa = p.toCharArray();
	for(int i = 0; i < right.length; i++){
	    right[i] = -1;
	}
	for(int i = 0; i < pa.length; i++){
	    right[pa[i]] = i;
	}
    }

    public int search(String txt){
	int align = 0;
	while(align + pa.length <= txt.length()){
	    int tail = align + pa.length - 1;
	    int newAlign = align;
	    for(; tail >= align; tail--){
		if(pa[tail - align] != txt.charAt(tail)){
		    if(tail - align - right[txt.charAt(tail)] > 0){
			newAlign = tail - right[txt.charAt(tail)];
		    }else{
			newAlign = align+1;
		    }
		    break;
		}
	    }
	    if(tail == align - 1){
		return align;
	    }
	    align = newAlign;
	}
	return -1;
    }
}
