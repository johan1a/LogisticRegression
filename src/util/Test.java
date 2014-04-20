package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import linearRegression.DataLoader;

public class Test {
	public static final String dataPath = "data/";

	public static void main(String[] args) {
		DataLoader loader = new DataLoader();
		DataPlotter plotter = new DataPlotter();
		loader.loadData(dataPath + "french");

		List<Double> x1 = loader.getX();
		List<Double> y1 = loader.getY();

		loader.loadData(dataPath + "english");

		List<Double> x2 = loader.getX();
		List<Double> y2 = loader.getY();

		List<Double> allY = new ArrayList<Double>();
		List<Double> allX = new ArrayList<Double>();
		allY.addAll(y1);
		allY.addAll(y2);
		allX.addAll(x1);
		allX.addAll(x2);
		double yMax = getMax(allY);
		double xMax = getMax(allX);

		scaleData(y1, yMax);
		scaleData(y2, yMax);

		scaleData(x1, yMax);
		scaleData(x2, yMax);

		plotter.plotData(x1, y1);
		plotter.plotData(x2, y2);
	}

	public static double getMax(List<Double> list) {
		return list.stream().max(Double::compare).get();
	}

	/* Scales the data to fit in the range [0,1] */
	public static List<Double> scaleData(List<Double> list, double max) {
		return list.stream().map(x -> x / max).collect(Collectors.toList());
	}
}
