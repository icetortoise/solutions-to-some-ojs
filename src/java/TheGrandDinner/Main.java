package TheGrandDinner;

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
	while(true){
	    String[] ns = ReadLn(300).split(" ");
	    int nteam = Integer.valueOf(ns[0]);
	    int ntable = Integer.valueOf(ns[1]);
	    if(nteam == 0 && ntable == 0){
		return;
	    }else{
		int[] teams = new int[nteam];
		int[] tables = new int[ntable];
		String[] strTeams = ReadLn(3000).split(" ");
		String[] strTables = ReadLn(3000).split(" ");
		for(int i = 0; i < nteam; i++){
		    teams[i] = Integer.valueOf(strTeams[i]);
		}
		for(int i = 0; i < ntable; i++){
		    tables[i] = Integer.valueOf(strTables[i]);
		}
		process(teams, tables);
	    }
	}
    }
    
    class TeamAndIdx implements Comparable<TeamAndIdx>{
	int idx;
	int size;
	public TeamAndIdx(int idx, int size){
	    this.idx = idx;
	    this.size = size;
	}
	
	public int compareTo(TeamAndIdx other){
	    return other.size - this.size;
	}
    }

    class TableAndIdx implements Comparable<TableAndIdx>{
	int idx;
	int cap;
	public TableAndIdx(int idx, int cap){
	    this.idx = idx;
	    this.cap = cap;
	}
	
	public int compareTo(TableAndIdx other){
	    return other.cap - this.cap;
	}
    }

    void process(int[] teams, int[] tables){
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	ArrayList<TeamAndIdx> teamAndIdx = new ArrayList<TeamAndIdx>();
	for(int i = 0; i< teams.length; i++){
	    result.add(new ArrayList<Integer>());
	    teamAndIdx.add(new TeamAndIdx(i, teams[i]));
	    //	    System.out.println(teamAndIdx.get(teamAndIdx.size()-1).size);
	}
	Collections.sort(teamAndIdx);
	PriorityQueue<TableAndIdx> tablePQ = new PriorityQueue<TableAndIdx>();
	for(int i = 1; i <= tables.length; i++){
	    tablePQ.offer(new TableAndIdx(i, tables[i-1]));
	}
	for(TeamAndIdx tai : teamAndIdx){
	    ArrayList<TableAndIdx> tempOut = new ArrayList<TableAndIdx>();
	    //	    System.out.println("team size " + tai.size);
	    for(int i = 0; i < tai.size; i++){
		TableAndIdx table = tablePQ.poll();
		if(table == null){
		    System.out.println("0");
		    return;
		}else{
		    result.get(tai.idx).add(table.idx);
		    table.cap--;
		    if(table.cap != 0){
			tempOut.add(table);
		    }
		}
	    }
	    for(TableAndIdx t : tempOut){
		tablePQ.offer(t);
	    }
	}
	System.out.println("1");
	for(ArrayList<Integer> arrangement : result){
	    for(int i = 0; i < arrangement.size(); i++){
		if(i != 0){
		    System.out.printf(" ");
		}
		System.out.printf(Integer.toString(arrangement.get(i)));
	    }
	    System.out.println();
	}
    }
}
