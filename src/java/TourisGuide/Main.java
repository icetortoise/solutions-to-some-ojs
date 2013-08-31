package TourisGuide;

import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }

    public static void main (String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
	int counter = 0;
	boolean first = true;
	while(true){
	    String str = ReadLn(100);
	    if(str == null){
		return;
	    }
	    int nloc = Integer.valueOf(str);
	    if(nloc == 0){
		break;
	    }else{
		if(!first){
		    System.out.println();
		}
		first = false;
		handleCase(nloc, ++counter);
	    }
	}
    }
    void handleCase(int nloc, int counter){
	String[] locs = new String[nloc];
	HashMap<String, Integer> locIdx = new HashMap<String, Integer>();
	ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	for(int i = 0; i < nloc; i++){
	    String loc = ReadLn(100);
	    locs[i] = loc;
	    locIdx.put(loc, i);
	    adjList.add(new ArrayList<Integer>());
	}
	int nedge = Integer.valueOf(ReadLn(100));
	if(nedge == 0){
	    System.out.println("City map #" + counter + ": " + 0 + " camera(s) found");
	    return;
	}
	for(int i = 0; i < nedge; i++){
	    String[] fromto = ReadLn(100).split(" ");
	    int from = locIdx.get(fromto[0]);
	    int to = locIdx.get(fromto[1]);
	    adjList.get(from).add(to);
	    adjList.get(to).add(from);
	}
	HashSet<Integer> cutnodes = findCutNodes(adjList);
	ArrayList<String> result = new ArrayList<String>();
	for(int i : cutnodes){
	    result.add(locs[i]);
	}
	Collections.sort(result);
	System.out.println("City map #" + counter + ": " + result.size()+ " camera(s) found");
	for(String s : result){
	    System.out.println(s);
	}
    }
    
    int orderCount = 0;
    
    HashSet<Integer> findCutNodes(ArrayList<ArrayList<Integer>> adjList){
	int[] parents = new int[adjList.size()];
	int[] pre = new int[adjList.size()];
	HashSet<Integer> result = new HashSet<Integer>();
	for(int i = 0; i < parents.length; i++){
	    parents[i] = -1;
	    pre[i] = -1;
	}
	orderCount = 0;
	for(int i = 0; i < parents.length; i++){
	    if(parents[i] == -1){
		orderCount = 0;
		findCutNodesImpl(adjList, i, orderCount, parents, pre, result);
	    }
	}
	return result;
    }

    int findCutNodesImpl(ArrayList<ArrayList<Integer>> adjList, int node, int preorder, 
			  int[] parents, int[] pre, HashSet<Integer>result){
	//	System.out.println(Integer.toString(node) + " " + Integer.toString(preorder));
	pre[node] = preorder;
	int traceBack = preorder;
	int ncomponent = 0;
	for(int adj : adjList.get(node)){
	    if(parents[adj] == -1 && pre[adj] != 0){//forward edge
		ncomponent++;
		parents[adj] = node;
		int traceBackFromAdj = findCutNodesImpl(adjList, adj, ++orderCount, parents, pre, result);
		if(traceBackFromAdj < traceBack){
		    traceBack = traceBackFromAdj;
		}
		// System.out.println(traceBack);
	    }else if(adj != parents[node]){//back edge
		if(pre[adj] < traceBack){
		    traceBack = pre[adj];
		}
	    }
	}
	// System.out.println(Integer.toString(traceBack) + " " + Integer.toString(pre[node]));
	if(traceBack == preorder){
	    if(ncomponent > 0 && parents[node] != -1){
		result.add(node);
	    }
	    if(parents[node] != -1 && parents[parents[node]] != -1){
		result.add(parents[node]);
	    }
	}else if(traceBack == parents[node] && parents[parents[node]] != -1){
	    result.add(parents[node]);
	}
	if(parents[node] == -1 && ncomponent > 1){
	    result.add(node);
	}
	return traceBack;
    }
}
