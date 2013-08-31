package Necklace;

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
	// Your program here
	int ncases = Integer.valueOf(Main.ReadLn(10));
	int c = 1;
	while(c++ <= ncases){
	    System.out.println("Case #" + Integer.toString(c-1));
	    handleCase();
	    if(c-1 < ncases){
		System.out.println();
	    }
	}
    }
    
    class Node{
	public int row;
	public int col;
	public int color;
	ArrayList<Integer> adj = new ArrayList<Integer>();
	public Node(int row, int col, int color){
	    this.row = row;
	    this.col = col;
	    this.color = color;
	}
    }
    
    void handleCase(){
	ArrayList<Node> adjList = buildAdjList();
	// int k = 0;
	// for(Node n : adjList){
	//     System.out.println(k++);
	//     for(int j : n.adj){
	// 	System.out.println(" " + Integer.toString(j));
	//     }
	// }
	ArrayList<Integer> path = new ArrayList<Integer>();
	path.add(0);
	ArrayList<Integer> result = dfsFindFullLoop(adjList, 0, 0, path);
	if(result.size() == 0){
	    System.out.println("some beads may be lost");
	}else{
	    for(int i = 0; i < result.size(); i++){
		System.out.printf(Integer.toString(adjList.get(result.get(i)).color));
		if(i % 2 == 0){
		    System.out.printf(" ");
		}else{
		    System.out.printf("\n");
		}
	    }
	}
    }
    
    ArrayList<Integer> dfsFindFullLoop(ArrayList<Node> adjList, int start, int target, ArrayList<Integer> path){
	// for(int i : path){
	//     System.out.printf(Integer.toString(i) + " ");
	// }
	// System.out.println(path.size());
	HashSet<Integer> visited = new HashSet<Integer>(path);
	if(path.size() != visited.size()){
	    return new ArrayList<Integer>();
	}
	if(path.size() == adjList.size()){
	    if(adjList.get(start).adj.contains(target)){
		return path;
	    }else{
		return new ArrayList<Integer>();
	    }
	}
	// else if(start == target && path.size() < adjList.size()){
	//     return new ArrayList<Integer>();
	// }
	else{
	    int neigh = 0;
	    if(start % 2 == 0){
		neigh = start + 1;
	    }else{
		neigh = start - 1;
	    }
	    if(!visited.contains(neigh)){
		ArrayList<Integer> newPath = new ArrayList<Integer>(path);
		newPath.add(neigh);
		ArrayList<Integer> result = dfsFindFullLoop(adjList, neigh, target, newPath);
		if(result.size() != 0){
		    return result;
		}else{
		    return new ArrayList<Integer>();
		}
	    }else{
		for(int next : adjList.get(start).adj){
		    if(!visited.contains(next)){
			ArrayList<Integer> newPath = new ArrayList<Integer>(path);
			newPath.add(next);
			ArrayList<Integer> result = dfsFindFullLoop(adjList, next, target, newPath);
			if(result.size() != 0){
			    return result;
			}
		    }
		}
	    }
	}
	return new ArrayList<Integer>();
    }
    
    ArrayList<Node> buildAdjList(){
	int nbeads = Integer.valueOf(Main.ReadLn(10));
	ArrayList<Node> adjList = new ArrayList<Node>();
	HashMap<Integer, ArrayList<Integer>> colorToIdx = new HashMap<Integer, ArrayList<Integer>>();
	for(int i = 0; i < nbeads; i++){
	    String[] colors = Main.ReadLn(10).split(" ");
	    Node n1 = new Node(i, 0, Integer.valueOf(colors[0]));
	    Node n2 = new Node(i, 1, Integer.valueOf(colors[1]));
	    adjList.add(n1);adjList.add(n2);
	    n1.adj.add(2*i+1);n2.adj.add(2*i);
	    if(colorToIdx.containsKey(n1.color)){
		for(Integer idx : colorToIdx.get(n1.color)){
		    if(idx != 2*i + 1){
			adjList.get(idx).adj.add(2*i);
			n1.adj.add(idx);
		    }
		}
		colorToIdx.get(n1.color).add(2*i);
	    }else{
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(2*i);
		colorToIdx.put(n1.color, l);
	    }
	    if(colorToIdx.containsKey(n2.color)){
		for(Integer idx : colorToIdx.get(n2.color)){
		    if(idx != 2*i){
			adjList.get(idx).adj.add(2*i+1);
			n2.adj.add(idx);
		    }
		}
		colorToIdx.get(n2.color).add(2*i+1);
	    }else{
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(2*i+1);
		colorToIdx.put(n2.color, l);
	    }
	}
	return adjList;
    }
}
