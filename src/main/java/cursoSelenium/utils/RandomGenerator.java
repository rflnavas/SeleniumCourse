package cursoSelenium.utils;

import java.util.Random;

public class RandomGenerator {
	
	
	public static int[] getInts(Integer quantity, Integer min, Integer max){
		final int LIMIT_MIN = (min == null? Integer.MIN_VALUE : min);
		final int LIMIT_MAX = (max == null? Integer.MAX_VALUE : max);
		if(LIMIT_MIN > LIMIT_MAX){ 
			throw new IllegalArgumentException("Min is greater than max " + min + ">" + max);
		}
		
		return new Random().ints(quantity, LIMIT_MIN, LIMIT_MAX).toArray();
	}
	
	public static int getInt(Integer min, Integer max){
		return getInts(1, min, max)[0];
	}
	
	public static void main(String[] args) {
		System.out.println(getInt(null, 0));
	}
}
