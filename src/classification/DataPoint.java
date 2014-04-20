package classification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataPoint {
	private int c; // The class/label
	private List<Double> x;

	public DataPoint(int label, List<Double> values) {
		c = label;
		x = values;
		x.add(0, 1.0);
	}

	public Double getVariable(int i) {
		return x.get(i);
	}

	public List<Double> getVariables() {
		return x;
	}

	public int getClassification() {
		return c;
	}

	public String toString() {
		String s = Integer.toString(c) + " ";

		for (int i = 0; i < x.size(); i++) {
			s += (i + 1) + ":" + x.get(i) + " ";
		}

		return s;
	}
}
