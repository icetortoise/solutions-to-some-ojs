package WordSearch;

public class Solution {
    public boolean exist(char[][] board, String word) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(board.length == 0)
	    return false;
	if(board[0].length == 0)
	    return false;
	int m = board.length, n = board[0].length;
	if(word.length() == 0) return false;
	for(int i = 0; i< m; i++){
	    for(int j =0; j<n; j++){
		if(board[i][j] == word.charAt(0)){
		    boolean[][] bk = new boolean[m][n];
		    bk[i][j] = true;
		    if(match(board, i, j, word.substring(1), bk))
		       return true;
		}
	    }
	}
	return false;
    }

    public boolean match(char[][] board, int i, int j, String newWord, boolean[][] bk){
	if(newWord.length() == 0) return true;
	char candidate = newWord.charAt(0);
	if(i-1>=0 && board[i-1][j] == candidate && !bk[i-1][j]){
	    boolean[][] bkc = bk.clone();
	    bkc[i-1][j] = true;
	    if(match(board, i-1,j,newWord.substring(1), bkc)){
		return true;
	    }
	}
	if(i+1<board.length && board[i+1][j] == candidate && !bk[i+1][j]){
	    boolean[][] bkc = bk.clone();
	    bkc[i+1][j] = true;
	    if(match(board, i+1,j,newWord.substring(1), bkc)){
		return true;
	    }
	}
	if(j-1>=0 && board[i][j-1] == candidate && !bk[i][j-1]){
	    boolean[][] bkc = bk.clone();
	    bkc[i][j-1] = true;
	    if(match(board, i,j-1,newWord.substring(1), bkc)){
		return true;
	    }
	}
	if(j+1<board[0].length && board[i][j+1] == candidate && !bk[i][j+1]){
	    boolean[][] bkc = bk.clone();
	    bkc[i][j+1] = true;
	    if(match(board, i,j+1,newWord.substring(1), bkc)){
		return true;
	    }
	}
	return false;
    }
}
