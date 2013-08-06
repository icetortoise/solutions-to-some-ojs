package BestTimeToBuyAndSellStock3;

public class Solution {
    // two dp scan, forward and backward
    public int maxProfit(int[] prices) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(prices.length == 0) return 0;
	int[] fprofits = new int[prices.length];
	int profit = 0;
	int valley = prices[0];
	for(int i = 0; i < prices.length; i++){
	    if(prices[i] >= valley){
		if(prices[i] - valley > profit){
		    fprofits[i] = prices[i] - valley;
		    profit = prices[i] - valley;
		}else{
		    fprofits[i] = profit;
		}
	    }else{
		valley = prices[i];
		fprofits[i] = profit;
	    }
	}
	int[] bprofits = new int[prices.length];
	profit = 0;
	int peak = prices[prices.length-1];
	int result = 0;
	for(int i = prices.length-1; i>= 0; i--){
	    if(prices[i] <= peak){
		if(peak - prices[i] > profit){
		    bprofits[i] = peak - prices[i];
		    profit = peak - prices[i];
		}else{
		    bprofits[i] = profit;
		}
	    }else{
		peak = prices[i];
		bprofits[i] = profit;
	    }
	    if(i > 0){
		result = (fprofits[i-1] + bprofits[i]) > result ? fprofits[i-1] + bprofits[i] : result;
	    }else{
		result = bprofits[i] > result ? bprofits[i] : result;
	    }
	}
	return result;
    }

}
