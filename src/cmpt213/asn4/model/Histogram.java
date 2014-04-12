package cmpt213.asn4.model;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//public class Histogram implements Iterable <Histogram.Bar>{
//	private int numBars = 0;
//	private int maxBarCount = 0;
//	private int maxScore = 0;
//	private int range = 1;
//	private int[] scores;
//	private List<Bar> bars;
//	private int[] rangeCounts;
//	
//	
//	public Histogram(int[] scores, int numBars) {
//		if (numBars <= 0) {
//			return;
//		}
//		
//		if (scores.length != 0) {
//			this.scores = Arrays.copyOf(scores, scores.length);
//			this.numBars = numBars;
//			this.setData(this.scores);
//		}
//		else {
//			this.rangeCounts = new int[5];
//			this.initBars();
//		}
//	}
//	
//	public void setData(int[] scores) {
//		
//		this.getMaxScore();
//		this.printArray(scores);
//		this.getRange();
//		this.printRanges();
//		this.distributeScoresToRanges();
//		this.printArray(this.rangeCounts);
//		
//		this.initBars();
//		this.printBars();
//		
//		System.out.println("maxBarCount: " + this.getMaxBarCount());
//	}
//
//	
//	public void setNumberBars(int numBars) {
//		this.numBars = numBars;
//	}
//
//	public int getNumberBars() {
//		return this.numBars;
//	}
//	
//	public Iterator<Histogram.Bar> iterator() {
//		if (this.bars != null) {
//			return Collections.unmodifiableList(bars).iterator();
//		}
//		return null;
//	}
//	
//	public int getMaxBarCount() {
//		if (this.rangeCounts != null) {
//			for (int count : this.rangeCounts) {
//				if (this.maxBarCount < count) {
//					this.maxBarCount = count;
//				}
//			}
//			return this.maxBarCount;
//		}
//		return 0;
//	}
//	
//	private int getMaxScore() {
//		if (this.maxScore == 0) {
//			for (int score : this.scores) {
//				if (this.maxScore < score) {
//					this.maxScore = score;
//				}
//			}
//		}
//		return this.maxScore;
//	}
//	
//	private int getRange() {
//		if (this.scores.length == 0 || (this.scores.length <= this.numBars && this.getMaxScore() < this.numBars)) {
//			this.range = 1;
//		}
//		else {
//			if (this.numBars != 0) {
//				this.range = this.getMaxScore() / this.numBars + 1;
//			}
//			else {
//				throw new ArithmeticException();
//			}
//		}
//		return this.range;
//	}
//	
//	private void distributeScoresToRanges() {
//		this.rangeCounts = new int[this.numBars];
//		for (int score : this.scores) {
//			int rangeIndex = score / this.range;
//			
//			this.rangeCounts[rangeIndex] += 1;
//		}
//	}
//	
//	private void initBars() {
//		this.bars = new ArrayList<Bar>();
//		for (int i = 0; i < this.rangeCounts.length; i++) {
//			this.bars.add(new Bar(i * this.range, (i * this.range + this.range - 1), this.rangeCounts[i]));
//		}
//	}
//	
//	private void printArray(int[] array) {
//		System.out.print("scores[] = {");
//		for (int score : array) {
//			System.out.print(score + " ");
//		}
//		System.out.println("}");
//	}
//	
//	private void printRanges() {
//		System.out.println("maxScore: " + this.getMaxScore());
//		System.out.println("numBars: " + this.numBars);
//		System.out.println("range: " + this.range);
//		
//		for (int i = 0; i < this.numBars; i++) {
//			System.out.print("[" + i * this.range + ", " + (i * this.range + this.range - 1) + "], ");
//		}
//		System.out.println("");
//	}
//	
//	private void printBars() {
//		Iterator<Bar> iterator = this.bars.iterator();
//		while (iterator.hasNext()) {
//			System.out.print(iterator.next() + " ");
//		}
//		System.out.println("");
//	}
//	
//	public class Bar {
//		private int minRange;
//		private int maxRange;
//		private int count;
//		
//		public Bar(int minRange, int maxRange, int count) {
//			this.minRange = minRange;
//			this.maxRange = maxRange;
//			this.count = count;
//		}
//		
//		public int getRangeMin() {
//			return this.minRange;
//		}
//
//		public int getRangeMax() {
//			return this.maxRange;
//		}
//		
//		public int getCount() {
//			return this.count;
//		}
//		
//		@Override
//		public String toString() {
//			return "Bar[" + this.minRange + ", " + this.maxRange + "]=" + this.count;
//		}
//	}
//}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Histogram class, generated from an array of integers. 
 * Supports a configurable number of bars, and can be changed after creation (mutable).
 */
public class Histogram implements Iterable<Histogram.Bar>{
	private final int MIN_VALUE = 0;
	
	private int[] data;
	private int numDivisions;
	private List<Bar> bars;

	public Histogram(int[] data, int numBars) {
		assert numBars > 0;
		this.data = Arrays.copyOf(data,data.length);
		this.numDivisions = numBars;
		updateHistogram();
	}
	
	public void setData(int[] data) {
		this.data = Arrays.copyOf(data,data.length);
		updateHistogram();
	}
	
	public void setNumberBars(int numBars) {
		assert numBars >= 1;
		this.numDivisions = numBars;
		updateHistogram();
	}
	
	private void updateHistogram() {
		int[] counts = getCountPerBar();
		populateBars(counts);
	}
	private int[] getCountPerBar() {
		int valuesPerBar = calculateValuesPerBar(numDivisions);

		int[] counts = new int[numDivisions];
		for (int value : data) {
			int barIndex = getIndexFromValue(value, valuesPerBar);
			counts[barIndex] ++;
		}
		return counts;
	}
	
	private void populateBars(int[] counts) {
		assert counts.length == numDivisions;
		int valuesPerBar = calculateValuesPerBar(numDivisions);
		
		bars = new ArrayList<Bar>(numDivisions);
		for (int barNum = 0; barNum < numDivisions; barNum++) {
			int minRange = barNum * valuesPerBar;
			int maxRange = minRange + valuesPerBar - 1;
			int count = counts[barNum];

			bars.add(new Bar(minRange, maxRange, count));
		}
	}

	private int calculateValuesPerBar(int numDivisions) {
		int max = findMax(data);
		return (int) Math.ceil((double)(max+1) / numDivisions);
	}

	private int findMax(int[] data) {
		int max = MIN_VALUE ;
		for(int value : data) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	private int getIndexFromValue(int value, int valuesPerBar) {
		int index = value/ valuesPerBar;
		assert index >= 0;
		assert index < numDivisions;
		return index;
	}

	public int getNumberBars() {
		return bars.size();
	}
	
	public Iterator<Bar> iterator() {
		return Collections.unmodifiableList(bars).iterator();
	}
	
	public int getMaxBarCount() {
		if (bars.size() == 0) {
			return 0;
		}
		int max = bars.get(0).getCount();
		for(Bar bar : bars) {
			if (bar.getCount() > max) {
				max = bar.getCount();
			}
		}
		return max;
	}
	
	

	/**
	 * Store information about a single bar in a histogram including:
	 * - Interval (minimum and maximum): The range of values mapped to this bar, inclusive.
	 * - The number of elements that mapped to this bar (bar's height/count).
	 */
	public class Bar {
		private int rangeMin = 0;
		private int rangeMax = 0;
		private int count = 0;
		
		public Bar(int min, int max, int count) {
			rangeMin = min;
			rangeMax = max;
			this.count = count;
		}
		
		public int getRangeMin() {
			return rangeMin;
		}
		public int getRangeMax() {
			return rangeMax;
		}
		public int getCount() {
			return count;
		}
		public String toString() {
			return "Bar [" + rangeMin + ", " + rangeMax + "] = " + count;
		}
	}
}

