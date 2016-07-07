package jmash;

import java.util.HashMap;
import java.util.Map;

public class PalmerRecommendedRange {
	public static Map<String, Double[]> rangesMap = new HashMap<>();
	
	
	static {
		rangesMap.put("Calcium", new Double[] {50.0, 150.0});
		rangesMap.put("Magnesium", new Double[] {10.0, 30.0});
		rangesMap.put("Sodium", new Double[] {0.0, 150.0});
		rangesMap.put("Chloride", new Double[] {0.0, 250.0});
		rangesMap.put("Sulfate", new Double[] {50.0, 350.0});
	}
}

