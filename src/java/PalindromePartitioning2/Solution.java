package PalindromePartitioning2;
import java.util.*;
public class Solution {
    public int minCut(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(s.length() == 0 ) return 0;
	int[] dp = new int[s.length()+1];
	ArrayList<ArrayList<Integer>> graph = buildPalinGraph(s);
	for(int i = s.length() - 1; i >= 0; i--){
	    ArrayList<Integer> nodes = graph.get(i);
	    int dis = Integer.MAX_VALUE;
	    for(int n : nodes){
		if(dp[n] < dis){
		    dis = dp[n];
		}
	    }
	    dp[i] = dis + 1;
	}
	return dp[0] - 1;
    }
    
    public ArrayList<ArrayList<Integer>> buildPalinGraph(String s){
	ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(s.length());
	for(int i = 0; i < s.length(); i++){
	    graph.add(null);
	}
	for(int i = 0; i <= (s.length()-1)*2; i++){
	    int m = 0, n = 0;
	    if(i % 2 == 0){
		int j = i/2;
		update(graph, j, j+1);
		m = j - 1; n = j + 1;
	    }else{
		m = (i-1)/2; n = (i+1)/2;
	    }
	    while(m >= 0 && n < s.length() && s.charAt(m) == s.charAt(n)){
		update(graph, m, n+1);
		m--; n++;
	    }
	}
	return graph;
    }
    public void update(ArrayList<ArrayList<Integer>> graph, int j, int ele){
	ArrayList<Integer> cur = graph.get(j);
	if(cur == null){
	    cur = new ArrayList<Integer>();
	    cur.add(ele);
	}else{
	    cur.add(ele);
	}
	graph.set(j, cur);
    }
}
