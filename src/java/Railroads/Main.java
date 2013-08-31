package Railroads;

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
	int ncase = Integer.valueOf(ReadLn(10));
	for(int i = 1; i <= ncase; i++){
	    System.out.println("Scenario " + Integer.toString(i));
	    handleCase();
	    if(i < ncase){
		System.out.println();
	    }
	}
    }
    
    void handleCase(){
	int ncity = Integer.valueOf(ReadLn(10));
	String[] cities = new String[ncity];
	HashMap<String, Integer> cityToIdx = new HashMap<String, Integer>();
	HashMap<Integer, ArrayList<Node>> sameCityList = new HashMap<Integer, ArrayList<Node>>();
	for(int i = 0; i < ncity; i++){
	    String c = ReadLn(100);
	    cities[i] = c;
	    cityToIdx.put(c, i);
	}
	ArrayList<Node> adjList = new ArrayList<Node>();
	int nTrain = Integer.valueOf(ReadLn(10));
	int nodeIdx = -1;
	for(int t = 0; t < nTrain; t++){
	    int nStops = Integer.valueOf(ReadLn(10));
	    boolean start = true;
	    for(int s = 0; s < nStops; s++){
		String[] row = ReadLn(100).split(" ");
		int time = Integer.valueOf(row[0]);
		String city = row[1];
		Node n = new Node(cityToIdx.get(city), time, nodeIdx + 1, row[0]);
		adjList.add(n);
		if(!start){
		    adjList.get(nodeIdx).adj.add(nodeIdx+1);
		}else{
		    start = false;
		}
		updateSameCityLast(sameCityList, n);
		// if(sameCityLast >= 0){
		//     adjList.get(sameCityLast).adj.add(nodeIdx + 1);
		// }
		nodeIdx++;
	    }
	}
	// for(Node n : adjList){
	//     System.out.println(Integer.toString(n.time) + " " + cities[n.cityNo]);
	//     for(int a : n.adj){
	// 	System.out.printf(Integer.toString(a) + " ");
	//     }
	//     System.out.println();
	// }
	int startAt = Integer.valueOf(ReadLn(10));
	String from = ReadLn(100);
	String to = ReadLn(100);
	ArrayList<Node> froms = sameCityList.get(cityToIdx.get(from));
	ArrayList<Node> tos = sameCityList.get(cityToIdx.get(to));
	if(adjList.size()==0 || froms == null || tos == null){
	    System.out.println("No Connection");
	    return;
	}
	Node lastEarliest = null;
	Node lastFrom = null;
	for(Node n : froms){
	    if(n.time < startAt){
		continue;
	    }
	    Node earliest = dfsEarliest(adjList, n, tos);
	    if(earliest == null){
		continue;
	    }
	    if(lastEarliest != null && earliest.time > lastEarliest.time){
		break;
	    }else{
		lastEarliest = earliest;
	    }
	    lastFrom = n;
	}
	if(lastFrom == null || lastEarliest == null){
	    System.out.println("No Connection");
	}else{
	    System.out.println("Departure " + lastFrom.timeStr + " " + cities[lastFrom.cityNo]);
	    System.out.println("Arrival   " + lastEarliest.timeStr + " " + cities[lastEarliest.cityNo]);
	}
    }
    
    Node dfsEarliest(ArrayList<Node> adjList, Node n, ArrayList<Node> tos){
	boolean[] visited = new boolean[adjList.size()];
	dfs(adjList, n, visited);
	for(Node t : tos){
	    if(visited[t.idx]){
		return t;
	    }
	}
	return null;
    }

    void dfs(ArrayList<Node> adjList, Node n, boolean[] visited){
	visited[n.idx] = true;
	for(int i : n.adj){
	    if(!visited[i])
		dfs(adjList, adjList.get(i), visited);
	}
    }

    void updateSameCityLast(HashMap<Integer, ArrayList<Node>> sameCityList, Node n){
	ArrayList<Node> l = sameCityList.get(n.cityNo);
	if(l == null){
	    ArrayList<Node> ns = new ArrayList<Node>();
	    ns.add(n);
	    sameCityList.put(n.cityNo, ns);
	    return;
	}else{
	    for(int i = 0; i < l.size(); i++){
		if(l.get(i).time > n.time){
		    if(i > 0){
			l.get(i-1).adj.add(n.idx);
		    }
		    n.adj.add(l.get(i).idx);
		    l.add(i, n);
		    return;
		}
	    }
	    l.get(l.size()-1).adj.add(n.idx);
	    l.add(n);
	    return;
	}
    }
    
    class Node{
	public int idx;
	public int cityNo;
	public int time;
	public String timeStr;
	public ArrayList<Integer> adj = new ArrayList<Integer>();
	public Node(int c, int t, int i, String ts){
	    cityNo = c; time = t; idx = i; timeStr = ts;
	}
    }
}
