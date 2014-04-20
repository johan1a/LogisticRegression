package classification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ClassificationDataLoader {
	private static final String dataPath = "data/classificationData";
	private List<DataPoint> dataPoints;
	private int currentLabel = 0;
	private HashMap<Integer, Double> maxValues;
	private HashMap<Integer, HashMap<Integer, List<Double>>> allValues;

	public ClassificationDataLoader() {
		maxValues = new HashMap<Integer, Double>();
		allValues = new HashMap<Integer, HashMap<Integer, List<Double>>>();
	}

	public void loadData() {
		try {
			Files.lines(Paths.get(dataPath)).forEach(this::addEntry);
		} catch (IOException e) {
			e.printStackTrace();
		}
		scaleData();
		dataPoints = makeDataPoints();
	}

	private List<DataPoint> makeDataPoints() {
		List<DataPoint> dataPoints = new ArrayList<DataPoint>();
		HashMap<Integer, List<Double>> classValues;

		List<Double> entryValues;
		for (int classLabel : allValues.keySet()) {
			classValues = allValues.get(classLabel);
			int classDataPointCount = classValues.get(1).size();
			int nbrVariables = classValues.keySet().size();

			for (int e = 0; e < classDataPointCount; e++) {
				entryValues = new ArrayList<Double>();

				for (int v = 1; v <= nbrVariables; v++) {
					entryValues.add(classValues.get(v).get(e));
				}
				dataPoints.add(new DataPoint(classLabel, entryValues));
			}
		}
		return dataPoints;
	}

	/* Assumes all attributes are set. */
	private void addEntry(String entry) {
		int label = Integer.parseInt(entry.split(" ")[0]);

		String entryWithoutLabel = entry.split(" ", 2)[1];

		String[] variables = entryWithoutLabel.split(" ");
		String[] tokens;
		int variableIndex;
		double value;

		for (String variable : variables) {
			tokens = variable.split(":");
			variableIndex = Integer.parseInt(tokens[0]);
			value = Double.parseDouble(tokens[1]);

			checkMaxValue(maxValues, variableIndex, value);
			addValue(label, variableIndex, value);
		}
	}

	private void addValue(int label, int variableIndex, double value) {
		HashMap<Integer, List<Double>> classValues = allValues.get(label);
		if (classValues == null) {
			classValues = new HashMap<Integer, List<Double>>();
			allValues.put(label, classValues);
		}
		List<Double> varValues = classValues.get(variableIndex);
		if (varValues == null) {
			varValues = new ArrayList<Double>();
			classValues.put(variableIndex, varValues);
		}
		varValues.add(value);
	}

	private void checkMaxValue(HashMap<Integer, Double> maxValues,
			int variableIndex, double value) {
		Double max;
		max = maxValues.get(variableIndex);
		if (max == null || value > max) {
			maxValues.put(variableIndex, value);
		}
	}

	private void scaleData() {
		HashMap<Integer, List<Double>> classValues;
		for (int classLabel : allValues.keySet()) {
			classValues = allValues.get(classLabel);

			for (int variableIndex : classValues.keySet()) {
				final double maxValue = maxValues.get(variableIndex);

				List<Double> scaledValues = classValues.get(variableIndex)
						.stream().map(s -> s / maxValue)
						.collect(Collectors.toList());

				classValues.put(variableIndex, scaledValues);
			}
		}
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}
}
