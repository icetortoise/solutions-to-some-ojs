package Happiness;

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
	    String ns = ReadLn(200);
	    if(ns == null){
		break;
	    }else{
		String[] nm = ns.split(" ");
		int n = Integer.valueOf(nm[0]);
		int m = Integer.valueOf(nm[1]);
		double[] c = new double[n];
		double[][] A = new double[m][n];
		double[] b = new double[m];
		String[] cs = ReadLn(200).split(" ");
		for(int i = 0; i < n; i++){
		    c[i] = Double.valueOf(cs[i]);
		}
		for(int i = 0; i < m; i++){
		    String[] ab = ReadLn(200).split(" ");
		    for(int j = 0; j <= n; j++){
			if(j == n){
			    b[i] = Double.valueOf(ab[j]);
			}else{
			    A[i][j] = Double.valueOf(ab[j]);
			}
		    }
		}
		solveHappiness(A, b, c);
	    }
	}
    }
    
    void solveHappiness(double[][] A, double[] b, double[] c){
	// for(int i = 0; i < A.length; i++){
	//     for(int j = 0; j < A[0].length; j++){
	// 	System.out.printf(Double.toString(A[i][j]) + " ");
	//     }
	//     System.out.printf(b[i] + " ");
	//     System.out.println();
	// }
	// for(int i = 0; i < c.length; i++){
	//     System.out.printf(Double.toString(c[i]) + " ");
	// }
	// System.out.println();
	double val = simplex(A, b, c);
	System.out.println(Math.ceil(-val*b.length));
    }
    
    double simplex(double[][] A, double[] b, double[] c){
	// Initialize cofficient matrix for poviting process
	int m = A.length;
	int n = A[0].length;
	double[][] a = new double[m+1][m+n+1];
	for(int i = 0; i < m; i++){
	    for(int j = 0; j < n; j++){
		a[i][j] = A[i][j];
	    }
	    a[i][n+i] = 1.0;
	    a[i][m+n] = b[i];
	}
	for(int j = 0; j < n; j++){
	    a[m][j] = c[j];
	}
	int[] basic = new int[m];
	for(int i = 0; i < m; i++){
	    basic[i] = n+i;
	}
	
	
	// solving simplex
	while(true){
	    // for(int i = 0; i <= m; i++){
	    // 	for(int j = 0; j <= m+n; j++){
	    // 	    System.out.printf(Double.toString(a[i][j]) + " ");
	    // 	}
	    // 	System.out.println();
	    // }

	    int q = findEnteringVar(a, m, n);
	    //	    System.out.println("entering: " + q);
	    if(q == -1)
		break; // optimal found
	    int p = findLeavingVar(a, q, m, n);
	    //	    System.out.println("leaving: " + p);
	    if(p == -1)
		throw new RuntimeException("LP unbounded");
	    
	    pivot(a,p,q,m,n);
	    basic[p] = q;
	}
	return a[m][m+n];
    }
    int findEnteringVar(double[][] a, int m, int n){
	// return the var idx whose cost is most positive
	int q = 0;
	for(int j = 0; j < m+n; j++){
	    if(a[m][j] > a[m][q])
		q = j;
	}
	if(a[m][q] > 0){
	    return q;
	}else{
	    return -1;
	}
    }
    int findLeavingVar(double[][] a, int entering, int m, int n){
	// return the basic var idx whost corresponding constraint is satisfied first for the given entering var
	int p = -1;
	for(int i = 0; i<m; i++){
	    if(a[i][entering] < 0) continue;
	    else if(p == -1){
		p = i;
	    }
	    else if(a[i][m+n]/a[i][entering] < a[p][m+n]/a[p][entering]){
		p = i;
	    }
	}
	return p;
    }
    void pivot(double[][] a, int leaving, int entering, int m, int n){
	// leaving : basic -> nonbasic
	// entering: nonbasic -> basic
	
	double coffToDiv = a[leaving][entering];
	// handling the leaving var constraint
	for(int j = 0; j <= m+n; j++){
	    if(j != entering)
		a[leaving][j] /= coffToDiv;
	    else
		a[leaving][j] = 1.0;
	}

	// update the rest of the coff matrix
	for(int i = 0; i<=m; i++){
	    for(int j = 0; j <= m+n; j++){
		if(i != leaving && j != entering){
		    a[i][j] -= a[leaving][j] * a[i][entering];
		}
	    }
	}

	// make a[i][entering] all zeros except when i == leaving, so that entering becomes basic
	for(int i = 0; i<=m; i++){
	    if(i != leaving)
		a[i][entering] = 0;
	}
	
    }
}
