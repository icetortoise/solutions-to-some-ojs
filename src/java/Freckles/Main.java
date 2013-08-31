package Freckles;


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
	boolean start = true;
	while(ncases-- > 0){
	    if(!start){
		System.out.println();
	    }
	    start = false;
	    handleCase();
	}
    }
    public void handleCase(){
	String val = Main.ReadLn(100);
	while(val.length() == 0){
	    val = Main.ReadLn(100);
	}
	int npoints = Integer.valueOf(val);
	double[][] points = new double[npoints][2];
	for(int i = 0; i < npoints; i++){
	    String[] r = Main.ReadLn(10).split(" ");
	    points[i][0] = Double.valueOf(r[0]);
	    points[i][1] = Double.valueOf(r[1]);
	}
	PriorityQueue<FromToAndDistance> pq = new PriorityQueue<FromToAndDistance>(100, new FromToAndDistance(0,0,0));
	double length = 0;
	int start = 0;
	boolean[] visited = new boolean[npoints];
	for(int i = 0; i < npoints - 1; i++){
	    visited[start] = true;
	    for(int j = 0; j < npoints; j++){
		if(!visited[j]){
		    //System.out.println(Integer.toString(start) + " " +  Integer.toString(j));
		    pq.offer(distance(points, start, j));
		}
	    }
	    FromToAndDistance ftd = pq.poll();
	    while(visited[ftd.to] && pq.size() > 0){
		ftd = pq.poll();
	    }
	    start = ftd.to;
	    length += ftd.distance;
	}
	System.out.printf("%.2f\n", length);//two decimal
    }
    
    public FromToAndDistance distance(double[][] points, int a, int b){
	double dis = Math.sqrt(Math.pow(points[a][0]-points[b][0],2) + 
			       Math.pow(points[a][1]-points[b][1], 2));
	return new FromToAndDistance(a, b, dis);
    }
    
    class FromToAndDistance implements Comparator<FromToAndDistance>{
	public int from;
	public int to;
	public double distance;
	public FromToAndDistance(int from, int to, double distance){
	    this.from = from;
	    this.to = to;
	    this.distance = distance;
	}
	
	public int compare(FromToAndDistance o1, FromToAndDistance o2){
	    if(o1.distance - o2.distance < 0){
		return -1;
	    }else if(o1.distance - o2.distance > 0){
		return 1;
	    }else{
		return (int)(o1.distance - o2.distance);
	    }
	}
    }
}
