package linearRegression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.DataPlotter;

public class LinearRegression {
	public static final String dataPath = "data/";
	private static List<Double> xFrench;
	private static List<Double> yFrench;
	private static List<Double> xEnglish;
	private static List<Double> yEnglish;
	private static double yMax;
	private static double xMax;

	public static void main(String[] args) {
		DataLoader loader = new DataLoader();
		DataPlotter plotter = new DataPlotter();

		initData(loader);

		LinearRegressor lr = new LinearRegressor(xFrench, yFrench);
		List<Double> weightsFrench = lr.linearRegression();

		lr = new LinearRegressor(xEnglish, yEnglish);
		List<Double> weightsEnglish = lr.linearRegression();

		printWeights(weightsFrench);
		printWeights(weightsEnglish);
		
		plotter.plotData(xFrench, yFrench);
		plotter.plotData(xEnglish, yEnglish);
		plotter.plotLine(weightsFrench);
		plotter.plotLine(weightsEnglish);
	}

	private static void printWeights(List<Double> weightsFrench) {
		weightsFrench.stream().forEach(a -> System.out.print(a + " "));
		System.out.println();
	}

	private static void initData(DataLoader loader) {
		loader.loadData(dataPath + "french");

		xFrench = loader.getX();
		yFrench = loader.getY();

		loader.loadData(dataPath + "english");

		xEnglish = loader.getX();
		yEnglish = loader.getY();

		List<Double> allY = new ArrayList<Double>();
		List<Double> allX = new ArrayList<Double>();

		allY.addAll(yFrench);
		allY.addAll(yEnglish);
		allX.addAll(xFrench);
		allX.addAll(xEnglish);

		yMax = getMax(allY);
		xMax = getMax(allX);

		yFrench = scaleData(yFrench, yMax);
		yEnglish = scaleData(yEnglish, yMax);

		xFrench = scaleData(xFrench, xMax);
		xEnglish = scaleData(xEnglish, xMax);
	}

	public static double getMax(List<Double> list) {
		return list.stream().max(Double::compare).get();
	}

	/* Scales the data to fit in the range [0,1] */
	public static List<Double> scaleData(List<Double> list, double max) {
		return list.stream().map(x -> x / max).collect(Collectors.toList());
	}
}
