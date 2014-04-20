package classification;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Perceptron {
	private static final int MAX_ITER = 100000;
	private static final int TOLERANCE = 0;

	/* n is the number of weights. */
	public HashMap<Integer, Double> classify(List<DataPoint> dataPoints) {
		int nbrWeights = dataPoints.get(0).getVariables().size();
		HashMap<Integer, Double> weights = initWeights(nbrWeights);
		double learningRate; // alpha
		int iterations = 0;
		int nbrInCorrectlyClassified = TOLERANCE + 1;

		while (nbrInCorrectlyClassified > TOLERANCE && iterations < MAX_ITER) {
			learningRate = MAX_ITER / ((double) MAX_ITER + iterations);

			/* The data points are presented in a random order. */
			Collections.shuffle(dataPoints);

			nbrInCorrectlyClassified = 0;

			/* wi <- wi + alpha * (y - h_w(X)) * xi */
			for (int i = 0; i < weights.size(); i++) {
				Double weight = weights.get(i);

				double sum = 0;
				for (DataPoint dataPoint : dataPoints) {
					sum += (dataPoint.getClassification() - h(
							dataPoint.getVariables(), weights))
							* dataPoint.getVariable(i);
				}
				if (sum != 0) {
					++nbrInCorrectlyClassified;
				}

				weight = weight + learningRate * sum;
				weights.put(i, weight);
			}
			++iterations;
		}
		System.out.println("Number of incorrectly classified data points: "
				+ nbrInCorrectlyClassified);
		System.out.println("Weights found after " + iterations
				+ " iterations: ");
		for (int i = 0; i < weights.size(); i++) {
			System.out.print(weights.get(i) + " ");
		}
		return weights;
	}

	/* The predicted classification */
	private int h(final List<Double> x, HashMap<Integer, Double> weights) {
		return threshHold(x, weights);
	}

	/* Returns 1 if x*w >= 0, 0 otherwise. */
	private int threshHold(final List<Double> x,
			HashMap<Integer, Double> weights) {
		double sum = 0;
		for (int i = 0; i < x.size(); i++) {
			sum += x.get(i) * weights.get(i);
		}
		return sum >= 0 ? 1 : 0;
	}

	private HashMap<Integer, Double> initWeights(int n) {
		HashMap<Integer, Double> w = new HashMap<Integer, Double>();
		for (int i = 0; i < n; i++) {
			w.put(i, 0.0);
		}
		return w;
	}
}
