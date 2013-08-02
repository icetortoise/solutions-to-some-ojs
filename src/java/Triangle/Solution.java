package Triangle;

import java.util.ArrayList;
public class Solution {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(triangle.size() == 0){
	    return 0;
	}
	ArrayList<Integer> totals = new ArrayList<Integer>();
	totals.add(0);
	int level = 1;
	for(ArrayList<Integer> l : triangle){
	    ArrayList<Integer> cur = new ArrayList<Integer>();
	    for(int i = 0; i < level; i++){
		int a = i < totals.size() ? totals.get(i)+l.get(i) : Integer.MAX_VALUE;
		int b = i - 1 >= 0 ? totals.get(i-1)+l.get(i) : Integer.MAX_VALUE;
		cur.add(a<b?a:b);
	    }
	    totals = cur;
	    level++;
	}
	int sum = Integer.MAX_VALUE;
	for(int t : totals){
	    if(t < sum){
		sum = t;
	    }
	}
	return sum;
    }
}
