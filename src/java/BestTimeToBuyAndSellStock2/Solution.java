package BestTimeToBuyAndSellStock2;

public class Solution {
    public int maxProfit(int[] prices) {
        // Start typing your Java solution below
        // DO NOT write main() function
	
	// the key here is to realize that if price[i] and price[j] is part of the max profit sequence,
	// and i j are not adjcent. then we must have price[i] < price[k] < price[j] where i<k<j. Therefore,
	// price[j] - price[i] is same as the sum of all price deltas between i and j.
	int profit = 0;
	for(int i = 0; i < prices.length; i++){
	    if(i-1 >= 0){
		int delta = prices[i] - prices[i-1];
		if(delta >0){
		    profit += delta;
		}
	    }
	}
	return profit;
    }
}
