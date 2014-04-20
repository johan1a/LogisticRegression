package classification;

import java.util.HashMap;
import java.util.List;

import util.DataPlotter;

public class LinearClassification {

	public static void main(String[] args) {
		ClassificationDataLoader loader = new ClassificationDataLoader();
		Perceptron perceptron = new Perceptron();

		loader.loadData();
		List<DataPoint> data = loader.getDataPoints();
		HashMap<Integer, Double> weights = perceptron.classify(data);

		DataPlotter plotter = new DataPlotter();
		plotter.plotData(data);
		plotter.plotLine(weights);
	}
}
