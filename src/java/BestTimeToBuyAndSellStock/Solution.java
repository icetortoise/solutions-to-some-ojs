package BestTimeToBuyAndSellStock;

public class Solution {
    public int maxProfit(int[] prices) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(prices.length == 0) return 0;
        int[] mins = new int[prices.length];
	int[] maxes = new int[prices.length];
	int a = prices[0], b = prices[prices.length-1];
	for(int i = 0; i<prices.length; i++){
	    if(prices[i] < a){
		a = prices[i];
	    }
	    mins[i] = a;
	    if(prices[prices.length - i - 1] > b){
		b = prices[prices.length - i - 1];
	    }
	    maxes[prices.length - i - 1] = b;
	}
	int profit = 0;
	for(int i = 0; i<prices.length; i++){
	    if(maxes[i] - mins[i] > profit){
		profit = maxes[i] - mins[i];
	    }
	}
	return profit;
    }
}
