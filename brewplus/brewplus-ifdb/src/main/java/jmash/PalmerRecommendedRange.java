package jmash;

import java.util.HashMap;
import java.util.Map;

public class PalmerRecommendedRange {
	
	public enum PalmerRecommendedRangeType
	{
		CALCIUM, MAGNESIUM, SODIUM, CHLORIDE, SULFATE
	}
	
	public static Map<PalmerRecommendedRangeType, Double[]> rangesMap = new HashMap<>();
	
	
	static {
		rangesMap.put(PalmerRecommendedRangeType.CALCIUM, new Double[] {50.0, 150.0});
		rangesMap.put(PalmerRecommendedRangeType.MAGNESIUM, new Double[] {10.0, 30.0});
		rangesMap.put(PalmerRecommendedRangeType.SODIUM, new Double[] {0.0, 150.0});
		rangesMap.put(PalmerRecommendedRangeType.CHLORIDE, new Double[] {0.0, 250.0});
		rangesMap.put(PalmerRecommendedRangeType.SULFATE, new Double[] {50.0, 350.0});
	}
}

